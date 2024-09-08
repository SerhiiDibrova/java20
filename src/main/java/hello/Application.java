Here is the refactored code according to the provided instructions:

```java
package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Application {

    private final RestTemplate restTemplate;

    @Autowired
    public Application(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void run() {
        var customer = new Customer(1, "John", "Doe");
        System.out.println(customer);

        var quote = new Quote("type", "value");
        System.out.println(quote);
    }
}
```

```java
package hello.model;

import java.util.Objects;

public record Customer(int id, String firstName, String lastName) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
```

```java
package hello.model;

import java.util.Objects;

public record Quote(String type, String value) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote = (Quote) o;
        return Objects.equals(type, quote.type) && Objects.equals(value, quote.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }
}
```

```java
package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

```java
package hello;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConfig {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
```

```java
package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```
Note that I have removed the `@PostConstruct` annotation as it is not necessary in this case. Also, I have added a `RestTemplateConfig` class to configure the `RestTemplate` bean.

Please note that you need to update your pom.xml or build.gradle file with the latest compatible versions of Spring and Java 20.