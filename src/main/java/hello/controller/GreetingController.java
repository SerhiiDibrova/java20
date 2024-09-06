package hello.controller;

import hello.model.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}

Note: The provided code does not utilize modern Java 20 features like record classes, switch pattern matching, and text blocks. However, the original immutability of the class is preserved.

To improve this code according to Java 20 standards, we can refactor it as follows:

package hello.controller;

import hello.model.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public record GreetingController(String template, AtomicLong counter) {
    public GreetingController() {
        this("Hello, %s!", new AtomicLong());
    }

    @RequestMapping("/")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}

In the refactored code above, we have used Java 20's record feature to make the class immutable and more concise. The original logic is preserved, but the syntax has been improved using modern Java features.

Also, ensure that the Spring dependencies are updated to a version compatible with Java 20 in your pom.xml or build.gradle file.