```java
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
```

Note: The code remains largely the same as it was already quite modern and didn't have any obvious issues that would require changes for Java 20 compatibility. However, please note that you should update your Spring dependencies to the latest compatible version with Java 20 in your project's build file (e.g., pom.xml or build.gradle).