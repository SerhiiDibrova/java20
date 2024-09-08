package hello.model;

public record Greeting(long id, String content) {}
```

Note: Since the original code did not use `record` correctly and had mutable fields, I have used a regular class in the following alternative solution:

```java
package hello.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Greeting {
    private final long id;
    @NotNull
    private final String content;
}
```

However, if you want to use `record` for this specific case where immutability is desired and validation is required, here's how you can do it:

```java
package hello.model;

import jakarta.validation.constraints.NotNull;

public record Greeting(long id, @NotNull String content) {}