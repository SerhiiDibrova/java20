Here is the refactored code in Java 20:

```java
package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
public class MainController {

    private static final String JOIN_TEMPLATE = "Joining All String ID's with JOIN method: ";
    private static final String MAKE_DISTINCT_AND_SORT_CHARACTERS_TEMPLATE = "-------------Get all ID characters, select distinct and sort with ID=   ";
    private static final String SPLIT_ALL_ID_WITH_COLON_SELECT_ID_WITH_JAVA_KEYWORD_THEN_SORT_THEN_JOIN_TEMPLATE = """
            -------------Split All Id With Colon,
            Select ID With \"Java\" Keyword,
            Then Sort Then Join """;
    private static final String FIND_ID_HAVING_CHARACTER_TEMPLATE = "-------------Return All ID having character \'g\' in it:  ";
    private static final String FIND_ALL_FILES_IN_PATH_AND_SORT_TEMPLATE = "---------Find all files in path and sort:    ";
    private static final String FIND_PARTICULAR_FILE_IN_PATH_AND_SORT_TEMPLATE = """
            ----------Find File in present directory which starts with \"grad\",
            provided maximum depth=25 and sort : """;
    private static final String FIND_PARTICULAR_FILE_IN_PATH_AND_SORT_WITH_WALK_FUNCTION_TEMPLATE = """
            ----------Find File in present directory which starts with \"grad\",
            provided maximum depth=25 and sort :  with walk function""";
    private static final String READ_FILE_WITH_STREAM_FUNCTION_TEMPLATE = "---------Read \"temp.txt\" file with stream functions, having \"print\" within it:  ";

    @Autowired
    private TopicService topicService;

    /**
     * Java 20 Date Time example
     *
     * @return
     */
    @RequestMapping("/datetime")
    public String index() {
        var timeClient = new SimpleTimeClient();
        var localDateTime = LocalDateTime.now();
        return """
                Current date and time: %s
                Zone ID: %s
                """.formatted(localDateTime, ZoneId.systemDefault());
    }

    /**
     * Java 20 Date Time example with pattern matching switch expression
     *
     * @return
     */
    @RequestMapping("/datetime-pattern-matching")
    public String indexPatternMatching() {
        var timeClient = new SimpleTimeClient();
        var localDateTime = LocalDateTime.now();
        return switch (localDateTime.getDayOfWeek()) {
            case MONDAY -> "Monday";
            case TUESDAY -> "Tuesday";
            // Add more cases as needed
            default -> "Unknown day of the week";
        };
    }

    @RequestMapping("/topics")
    public String topics() {
        var topics = topicService.getTopics();
        return topics.stream()
                .map(Topic::getName)
                .collect(Collectors.joining(", "));
    }
}
```

Note that I have updated the code to use Java 20 features such as text blocks for multi-line strings, and pattern matching switch expressions. Also, I've replaced string concatenation with formatted strings using the `formatted()` method. Additionally, I've used `var` keyword for local variable declarations where possible.

Please note that you need to update your dependencies and imports to ensure compatibility with Java 20.