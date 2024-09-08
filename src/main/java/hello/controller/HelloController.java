package com.example.datetime;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DateTimeController {

    private static final String joinTemplate = "-------------Join all ID:  ";
    private static final String makeDistinctAndSortCharactersInIDTemplate = "-------------Make distinct and sort characters in ID:  ";
    private static final String splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoinTemplate = "-------------Split All Id With Colon," +
            "Select ID With \"Java\" Keyword," +
            " Then Sort Then Join ";
    private static final String findIdHavingCharacterTemplate = "-------------Return All ID having character \'g\' in it:  ";
    private static final String findAllFilesInPathAndSortTemplate = "---------Find all files in path and sort:    ";
    private static final String findParticularFileInPathAndSortTemplate = "----------Find File in present directory which strats with \"grad\",provided maximum depth=25 and sort : ";
    private static final String findParticularFileInPathAndSortWithWalkFunctionTemplate = "----------Find File in present directory which strats with \"grad\",provided maximum depth=25 and sort :  with walk function";
    private static final String readFileWithStreamFunctionTemplate = "---------Read \"temp.txt\" file with stream functions, having \"print\" witin it:  ";

    @Autowired
    private TopicService topicService;

    @RequestMapping("/datetime")
    public String index() {
        TimeClient myTimeClient = new SimpleTimeClient();
        LocalDateTime localDateTime = LocalDateTime.now();
        return "Greetings from Spring Boot! " +
                joinTemplate + "\n" +
                makeDistinctAndSortCharactersInIDTemplate + "\n" +
                splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoinTemplate + "\n" +
                findIdHavingCharacterTemplate + "\n" +
                findAllFilesInPathAndSortTemplate + "\n" +
                findParticularFileInPathAndSortTemplate + "\n" +
                findParticularFileInPathAndSortWithWalkFunctionTemplate + "\n" +
                readFileWithStreamFunctionTemplate + "\n" +
                "Local Date Time: " + localDateTime;
    }

    @RequestMapping("/file")
    public String file() {
        var findAllFilesInPathAndSort = topicService.findAllFilesInPathAndSort();
        var findParticularFileInPathAndSort = topicService.findParticularFileInPathAndSort();
        var findParticularFileInPathAndSortWithWalkFunction = topicService.findParticularFileInPathAndSortWithWalkFunction();
        var readFileWithStreamFunction = topicService.readFileWithStreamFunction();

        return findAllFilesInPathAndSortTemplate + findAllFilesInPathAndSort +
                findParticularFileInPathAndSortTemplate + findParticularFileInPathAndSort +
                findParticularFileInPathAndSortWithWalkFunctionTemplate + findParticularFileInPathAndSortWithWalkFunction +
                readFileWithStreamFunctionTemplate + readFileWithStreamFunction;
    }
}