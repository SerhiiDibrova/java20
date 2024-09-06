package hello.controller;

import hello.declaration.TimeClient;
import hello.model.SimpleTimeClient;
import hello.model.Topic;
import hello.service.TopicService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    private final TopicService topicService;

    @Autowired
    public HelloController(TopicService topicService) {
        this.topicService = topicService;
    }

    @RequestMapping("/topic/string/operation")
    public String showStringOperation() {
        var join = topicService.returnAllTopicIDWithStringSlicing();
        var makeDistinctAndSortCharacters = topicService.makeDistinctAndSortCharacters(join);
        var splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoin = topicService
                .splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoin(join);
        var findIdHavingCharacter = topicService.findIdHavingCharacter();

        return """
                Join: %s
                Make Distinct And Sort Characters: %s
                Split All Id With Colon Select ID With Java Keyword Then Sort Then Join: %s
                Find Id Having Character: %s
                """.formatted(join, makeDistinctAndSortCharacters,
                splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoin, findIdHavingCharacter);
    }

    @RequestMapping("/topic/file/operation")
    public String showFileOperation() {
        var findAllFilesInPathAndSort = topicService.findAllFilesInPathAndSort();
        var findParticularFileInPathAndSort = topicService.findParticularFileInPathAndSort();
        var findParticularFileInPathAndSortWithWalkFunction = topicService.findParticularFileInPathAndSortWithWalkFunction();
        var readFileWithStreamFunction = topicService.readFileWithStreamFunction();

        return """
                Find All Files In Path And Sort: %s
                Find Particular File In Path And Sort: %s
                Find Particular File In Path And Sort With Walk Function: %s
                Read File With Stream Function: %s
                """.formatted(findAllFilesInPathAndSort, findParticularFileInPathAndSort,
                findParticularFileInPathAndSortWithWalkFunction, readFileWithStreamFunction);
    }

    @RequestMapping("/")
    public String index() {
        var timeClient = new SimpleTimeClient();
        var tomorrow = timeClient.getLocalDateTime().plusDays(1);
        var previousMonth = timeClient.getLocalDateTime().minusMonths(1);

        return """
                Datetime now is %s
                Datetime tomorrow will be %s
                Datetime of previous month was %s
                Is this a leap year ?  %b
                Default system zone id   %s
                Time in California: %s
                """.formatted(timeClient.getLocalDateTime(), tomorrow, previousMonth,
                LocalDate.now().isLeapYear(), ZoneId.systemDefault(),
                timeClient.getZonedDateTime("Canada/Central"));
    }
}