package hello.controller;

import hello.declaration.TopicService;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public record HelloController(TopicService topicService) {

    public String index() {
        var myTimeClient = new SimpleTimeClient();
        var localDateTime = LocalDateTime.now();
        return "Greetings from Spring Boot! ----------------------" +
                "Datetime now is " + myTimeClient.toString() + "----------------------" +
                "Datetime tomorrow will be " + myTimeClient.getLocalDateTime().plusDays(1) + "----------------------" +
                "Datetime of previous month was " + myTimeClient.getLocalDateTime().minusMonths(1) + "----------------------" +
                "Is this a leap year ?  " + localDateTime.toLocalDate().isLeapYear() + "----------------------" +
                "Default system zone id   " + ZoneId.systemDefault() + "-------------------" +
                "Time in California: " + myTimeClient.getZonedDateTime("Canada/Central").toString();
    }

    public String showStringOperation() {
        var join = topicService.returnAllTopicIDWithStringSlicing();
        var makeDistinctAndSortCharacters = topicService.makeDistinctAndSortCharacters(join);
        var splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoin = topicService
                .splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoin(join);
        var findIdHavingCharacter = topicService.findIdHavingCharacter();

        return "Joined string: " + join +
                "\nMake distinct and sort characters: " + makeDistinctAndSortCharacters +
                "\nSplit all id with colon select id with java keyword then sort then join: " + splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoin +
                "\nFind id having character: " + findIdHavingCharacter;
    }

    public String showFileOperation() {
        var findAllFilesInPathAndSort = topicService.findAllFilesInPathAndSort();
        var findParticularFileInPathAndSort = topicService.findParticularFileInPathAndSort();
        var findParticularFileInPathAndSortWithWalkFunction = topicService.findParticularFileInPathAndSortWithWalkFunction();
        var readFileWithStreamFunction = topicService.readFileWithStreamFunction();

        return "Find all files in path and sort: " + findAllFilesInPathAndSort +
                "\nFind particular file in path and sort: " + findParticularFileInPathAndSort +
                "\nFind particular file in path and sort with walk function: " + findParticularFileInPathAndSortWithWalkFunction +
                "\nRead file with stream function: " + readFileWithStreamFunction;
    }
}