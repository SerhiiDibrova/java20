package hello.controller;

import hello.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;
import java.util.Objects;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        Objects.requireNonNull(name, "Name cannot be null");
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}