package com.example.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class TopicController {

    private static final String joinTemplate = "---------Join all strings: ";
    private static final String makeDistinctAndSortCharactersTemplate = "----------Make distinct and sort characters in string: ";
    private static final String splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoinTemplate = "----------Split all id with colon, select ID with Java keyword then sort then join: ";
    private static final String findIdHavingCharacterTemplate = "----------Find ID having character 'g': ";

    private static final String findAllFilesInPathAndSortTemplate = "---------Find all files in path and sort:    ";
    private static final String findParticularFileInPathAndSortTemplate = "----------Find File in present directory which strats with \"grad\",provided maximum depth=25 and sort : ";
    private static final String findParticularFileInPathAndSortWithWalkFunctionTemplate = "----------Find File in present directory which strats with \"grad\",provided maximum depth=25 and sort with walk function: ";
    private static final String readFileWithStreamFunctionTemplate = "---------Read file with stream function: ";

    @Autowired
    private TopicService topicService;

    @RequestMapping("/topic/date/operation")
    public String showDateOperation() {
        var timeClient = new SimpleTimeClient();
        var localDateTime = LocalDateTime.now();
        return """
                Current Date and Time: %s
                """.formatted(localDateTime);
    }

    @RequestMapping("/topic/string/operation")
    public String showStringOperation() {
        var joinResult = topicService.returnJoinedString();
        var makeDistinctAndSortCharactersResult = topicService.makeDistinctAndSortCharacters();
        var splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoinResult = topicService.splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoin();
        var findIdHavingCharacterResult = topicService.findIdHavingCharacter('g');
        return """
                %s%s
                %s%s
                %s%s
                %s%s
                """.formatted(joinTemplate, joinResult,
                makeDistinctAndSortCharactersTemplate, makeDistinctAndSortCharactersResult,
                splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoinTemplate, splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoinResult,
                findIdHavingCharacterTemplate, findIdHavingCharacterResult);
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