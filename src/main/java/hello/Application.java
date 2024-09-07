package hello;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import hello.model.Customer;
import hello.model.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        var ctx = SpringApplication.run(Application.class, args);
        
        System.out.println("Let's inspect the beans provided by Spring Boot:");
        
        var beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (var beanName : beanNames) {
            System.out.println(beanName);
        }

        var restTemplate = new RestTemplate();
        var quote = restTemplate.getForObject("""
                http://gturnquist-quoters.cfapps.io/api/random
                """, Quote.class);
        log.info(quote.toString());
    }


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            var quote = restTemplate.getForObject("""
                    http://gturnquist-quoters.cfapps.io/api/random
                    """, Quote.class);
            log.info(quote.toString());
        };
    }


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        log.info("Creating tables");

        jdbcTemplate.execute("""
                DROP TABLE customers IF EXISTS;
                CREATE TABLE customers(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))
                """);

        // Split up the array of whole names into an array of first/last names
        var splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long")
                .stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // Use a Java 8 stream to print out each tuple of the list
        splitUpNames.forEach(name -> log.info(String.format("""
                        Inserting customer record for %s %s
                        """, name[0], name[1])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

        log.info("""
                Querying for customer records where first_name = 'Josh':
                """);
        jdbcTemplate.query("""
                SELECT id, first_name, last_name FROM customers WHERE first_name = ?
                """, new Object[]{"Josh"},
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        ).forEach(customer -> log.info(customer.toString()));

    }
}