package hello.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public record HelloController(TopicService topicService) {

    @RequestMapping("/topic/datetime/operation")
    public String showDateTimeOperation() {
        var timeClient = new SimpleTimeClient();
        return "Datetime now is " + timeClient.getLocalDateTime().toString()
                + "\nDatetime tomorrow will be " + timeClient.getLocalDateTime().plusDays(1).toString()
                + "\nDatetime of previous month was " + timeClient.getLocalDateTime().minusMonths(1).toString()
                + "\nIs this a leap year ?  " + LocalDate.now().isLeapYear()
                + "\nDefault system zone id   " + ZoneId.systemDefault()
                + "\nTime in California: " + timeClient.getZonedDateTime("America/Los_Angeles").toString();
    }

    @RequestMapping("/topic/string/operation")
    public String showStringOperation() {
        var join = topicService.returnAllTopicIDWithStringSlicing();
        var makeDistinctAndSortCharacters = topicService.makeDistinctAndSortCharacters(join);
        var splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoin = topicService
                .splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoin(join);
        var findIdHavingCharacter = topicService.findIdHavingCharacter();

        return "Join: " + join
                + "\nMake Distinct And Sort Characters: " + makeDistinctAndSortCharacters
                + "\nSplit All Id With Colon Select ID With Java Keyword Then Sort Then Join: " + splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoin
                + "\nFind Id Having Character: " + findIdHavingCharacter;
    }

    @RequestMapping("/topic/file/operation")
    public String showFileOperation() {
        var findAllFilesInPathAndSort = topicService.findAllFilesInPathAndSort();
        var findParticularFileInPathAndSort = topicService.findParticularFileInPathAndSort();
        var findParticularFileInPathAndSortWithWalkFunction = topicService.findParticularFileInPathAndSortWithWalkFunction();
        var readFileWithStreamFunction = topicService.readFileWithStreamFunction();

        return "Find All Files In Path And Sort: " + findAllFilesInPathAndSort
                + "\nFind Particular File In Path And Sort: " + findParticularFileInPathAndSort
                + "\nFind Particular File In Path And Sort With Walk Function: " + findParticularFileInPathAndSortWithWalkFunction
                + "\nRead File With Stream Function: " + readFileWithStreamFunction;
    }
}