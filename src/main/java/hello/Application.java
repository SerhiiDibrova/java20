package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(Application.class.getName());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        var ctx = SpringApplication.run(Application.class, args);
        var beanNames = ctx.getBeanDefinitionNames();
        java.util.Arrays.sort(beanNames);
        for (var beanName : beanNames) {
            System.out.println(beanName);
        }

        var restTemplate = new RestTemplate();
        var quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        log.info(quote.toString());
    }

    @jakarta.annotation.Bean
    public RestTemplate restTemplate(jakarta.enterprise.inject.spi.RestTemplateBuilder builder) {
        return builder.build();
    }

    @jakarta.annotation.Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            var quote = restTemplate.getForObject(
                    "http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
            log.info(quote.toString());
        };
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Creating tables");

        jdbcTemplate.execute("""
                DROP TABLE customers IF EXISTS;
                CREATE TABLE customers(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))
                """);

        var splitUpNames = java.util.List.of(
                "John Woo", "Jeff Dean", "Josh Bloch", "Josh Long")
                .stream()
                .map(name -> name.split(" "))
                .toList();

        splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

        log.info("Querying for customer records where first_name = 'Josh':");
        jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[]{"Josh"},
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        ).forEach(customer -> log.info(customer.toString()));
    }
}

record Quote(String type, Value value) {
    record Value(String id, String quote) {}
}

class Customer {
    private long id;
    private String firstName;
    private String lastName;

    public Customer(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}