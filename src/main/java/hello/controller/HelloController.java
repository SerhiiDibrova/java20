package com.example.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TopicController {

    private static final String joinTemplate = "---------Join strings: ";
    private static final String makeDistinctAndSortCharactersTemplate = "Make distinct and sort characters: ";
    private static final String splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoinTemplate = """
            Split all id with colon select ID with java keyword then sort then join : ";
    private static final String findAllFilesInPathAndSortTemplate = "---------Find all files in path and sort:    ";
    private static final String findParticularFileInPathAndSortTemplate = "----------Find File in present directory which starts with \"grad\",provided maximum depth=25 and sort : ";
    private static final String findParticularFileInPathAndSortWithWalkFunctionTemplate = "----------Find File in present directory which starts with \"grad\",provided maximum depth=25 and sort :  with walk function";
    private static final String readFileWithStreamFunctionTemplate = "Read file with stream function: ";

    @Autowired
    private TopicService topicService;

    @RequestMapping("/topic/datetime/operation")
    public String showDateTimeOperation() {
        var timeClient = new SimpleTimeClient();
        var currentDateTime = LocalDateTime.now();

        return """
                Current Date and Time: %s
                """.formatted(currentDateTime);
    }

    @RequestMapping("/topic/string/operation")
    public String showStringOperation() {
        var joinResult = topicService.joinStrings();
        var makeDistinctAndSortCharactersResult = topicService.makeDistinctAndSortCharacters();
        var splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoinResult = topicService.splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoin();

        return """
                %s%s
                %s%s
                %s%s
                """.formatted(joinTemplate, joinResult,
                        makeDistinctAndSortCharactersTemplate, makeDistinctAndSortCharactersResult,
                        splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoinTemplate, splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoinResult);
    }

    @RequestMapping("/topic/file/operation")
    public String showFileOperation() {
        var findAllFilesInPathAndSortResult = topicService.findAllFilesInPathAndSort();
        var findParticularFileInPathAndSortResult = topicService.findParticularFileInPathAndSort();
        var findParticularFileInPathAndSortWithWalkFunctionResult = topicService.findParticularFileInPathAndSortWithWalkFunction();
        var readFileWithStreamFunctionResult = topicService.readFileWithStreamFunction();

        return """
                %s%s
                %s%s
                %s%s
                %s%s
                """.formatted(findAllFilesInPathAndSortTemplate, findAllFilesInPathAndSortResult,
                        findParticularFileInPathAndSortTemplate, findParticularFileInPathAndSortResult,
                        findParticularFileInPathAndSortWithWalkFunctionTemplate, findParticularFileInPathAndSortWithWalkFunctionResult,
                        readFileWithStreamFunctionTemplate, readFileWithStreamFunctionResult);
    }
}