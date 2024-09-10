package hello.controller;

import hello.declaration.TimeClient;
import hello.model.SimpleTimeClient;
import hello.service.TopicService;
import jakarta.annotation.Autowired;
import jakarta.web.bind.annotation.GetMapping;
import jakarta.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@RestController
public class HelloController {

    String joinTemplate = "Joining All String ID's with JOIN method: ";
    String makeDistinctAndSortCharactersTemplate = "-------------Get all ID characters, select distict and sort with ID=   ";
    String splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoinTemplate = "-------------Split All Id With Colon," +
            "Select ID With \"Java\" Keyword," +
            " Then Sort Then Join ";
    String findIdHavingCharacterTemplate = "-------------Return All ID having character \'g\' in it:  ";
    String findAllFilesInPathAndSortTemplate = "---------Find all files in path and sort:    ";
    String findParticularFileInPathAndSortTemplate = "----------Find File in present directory which strats with \"grad\",provided maximum depth=25 and sort : ";
    String findParticularFileInPathAndSortWithWalkFunctionTemplate = "----------Find File in present directory which strats with \"grad\",provided maximum depth=25 and sort :  with walk function";
    String readFileWithStreamFunctionTemplate = "---------Read \"temp.txt\" file with stream functions, having \"print\" witin it:  ";

    @Autowired
    private TopicService topicService;

    @GetMapping("/datetime")
    public String index() {
        TimeClient myTimeClient = new SimpleTimeClient();
        LocalDateTime localDateTime = LocalDateTime.now();
        return "Greetings from Spring Boot! ----------------------" +
                "Datetime now is " + myTimeClient.toString() + "----------------------" +
                "Datetime tomorrow will be " + myTimeClient.getLocalDateTime().plusDays(1) + "----------------------" +
                "Datetime of previous month was " + myTimeClient.getLocalDateTime().minus(1, ChronoUnit.MONTHS) + "----------------------" +
                "Is this a leap year ?  " + LocalDate.now().isLeapYear() + "----------------------" +
                "Default system zone id   " + ZoneId.systemDefault() + "-------------------" +
                "Time in California: " + myTimeClient.getZonedDateTime("Canada/Central").toString();
    }

    @GetMapping("/topic/string/operation")
    public String showStringOperation() {
        String join = topicService.returnAllTopicIDWithStringSlicing();
        String makeDistinctAndSortCharacters = topicService.makeDistinctAndSortCharacters(join);
        String splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoin = topicService
                .splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoin(join);
        String findIdHavingCharacter = topicService.findIdHavingCharacter();

        return joinTemplate + join
                + makeDistinctAndSortCharactersTemplate + makeDistinctAndSortCharacters
                + splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoinTemplate + splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoin
                + findIdHavingCharacterTemplate + findIdHavingCharacter;
    }

    @GetMapping("/topic/file/operation")
    public String showFileOperation() {
        String findAllFilesInPathAndSort = topicService.findAllFilesInPathAndSort();
        String findParticularFileInPathAndSort = topicService.findParticularFileInPathAndSort();
        String findParticularFileInPathAndSortWithWalkFunction = topicService.findParticularFileInPathAndSortWithWalkFunction();
        String readFileWithStreamFunction = topicService.readFileWithStreamFunction();
        return findAllFilesInPathAndSortTemplate + findAllFilesInPathAndSort
                + findParticularFileInPathAndSortTemplate + findParticularFileInPathAndSort
                + findParticularFileInPathAndSortWithWalkFunctionTemplate + findParticularFileInPathAndSortWithWalkFunction
                + readFileWithStreamFunctionTemplate + readFileWithStreamFunction;
    }
}