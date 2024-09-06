package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class MyController {

    private static final String joinTemplate = "-------------Join All ID: ";
    private static final String makeDistinctAndSortCharactersTemplate = "---------Make Distinct And Sort Characters In \"Java\":  ";
    private static final String splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoinTemplate = "-------------Split All Id With Colon," +
            "Select ID With \"Java\" Keyword," +
            " Then Sort Then Join ";
    private static final String findIdHavingCharacterTemplate = "-------------Return All ID having character \'g\' in it:  ";
    private static final String findAllFilesInPathAndSortTemplate = "---------Find all files in path and sort:    ";
    private static final String findParticularFileInPathAndSortTemplate = "----------Find File in present directory which starts with \"grad\",provided maximum depth=25 and sort : ";
    private static final String findParticularFileInPathAndSortWithWalkFunctionTemplate = "----------Find File in present directory which starts with \"grad\",provided maximum depth=25 and sort :  with walk function";
    private static final String readFileWithStreamFunctionTemplate = "---------Read \"temp.txt\" file with stream functions, having \"print\" within it:  ";

    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "/datetime", method = RequestMethod.GET)
    public String index() {
        TimeClient myTimeClient = new SimpleTimeClient();
        return "Greetings from Spring Boot! ----------------------" +
                "Datetime now is " + String.valueOf(myTimeClient.toString()) + "----------------------" +
                "Datetime tomorrow will be " + String.valueOf(myTimeClient.getLocalDateTime().plusDays(1)) + "----------------------" +
                "Datetime of previous month was " + String.valueOf(myTimeClient.getLocalDateTime().minusMonths(1)) + "----------------------" +
                "Is this a leap year ?  " + String.valueOf(LocalDate.now().isLeap()) + "----------------------" +
                "Default system zone id   " + String.valueOf(ZoneId.systemDefault()) + "-------------------" +
                "Time in California: " + myTimeClient.getZonedDateTime("America/Los_Angeles").toString();
    }

    @RequestMapping(value = "/topic/string/operation", method = RequestMethod.GET)
    public String showFileOperation() {
        List<String> findAllFilesInPathAndSort = topicService.findAllFilesInPathAndSort();
        List<String> findParticularFileInPathAndSort = topicService.findParticularFileInPathAndSort();
        List<String> findParticularFileInPathAndSortWithWalkFunction = topicService.findParticularFileInPathAndSortWithWalkFunction();
        String readFileWithStreamFunction = topicService.readFileWithStreamFunction();
        return findAllFilesInPathAndSortTemplate + String.join(", ", findAllFilesInPathAndSort)
                + findParticularFileInPathAndSortTemplate + String.join(", ", findParticularFileInPathAndSort)
                + findParticularFileInPathAndSortWithWalkFunctionTemplate + String.join(", ", findParticularFileInPathAndSortWithWalkFunction)
                + readFileWithStreamFunctionTemplate + readFileWithStreamFunction;
    }

    @RequestMapping(value = "/topic/file/operation", method = RequestMethod.GET)
    public String showStringOperation() {
        String join = topicService.join();
        String makeDistinctAndSortCharacters = topicService.makeDistinctAndSortCharacters();
        String splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoin = topicService.splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoin();
        String findIdHavingCharacter = topicService.findIdHavingCharacter();
        return joinTemplate + join
                + makeDistinctAndSortCharactersTemplate + makeDistinctAndSortCharacters
                + splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoinTemplate + splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoin
                + findIdHavingCharacterTemplate + findIdHavingCharacter;
    }
}