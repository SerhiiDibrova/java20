package hello.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/")
    public hello.model.Greeting greeting(@RequestParam(value="name", defaultValue="World") var name) {
        return new hello.model.Greeting(counter.incrementAndGet(), 
                String.format(template, name));
    }
}

record hello.model.Greeting(long id, String content);