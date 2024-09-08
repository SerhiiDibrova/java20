package hello;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import hello.model.Customer;
import hello.model.Quote;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Creating tables");

        jdbcTemplate.execute("""
                DROP TABLE customers IF EXISTS;
                CREATE TABLE customers(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))
                """);

        var splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long")
                .stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        splitUpNames.forEach(name -> LOGGER.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

        LOGGER.info("Querying for customer records where first_name = 'Josh':");
        jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[]{"Josh"},
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        ).forEach(customer -> LOGGER.info(customer.toString()));
    }
}

@Component
class RestTemplateBean {

    private final RestTemplate restTemplate;

    public RestTemplateBean(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @PostConstruct
    public void init() {
        var quote = restTemplate.getForObject("http://gturnquist-quoters.cfapis.io/api/random", Quote.class);
        LOGGER.info(quote.toString());
    }
}