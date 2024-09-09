package com.example.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class TopicController {

    private static final String joinTemplate = "---------Join Strings: ";
    private static final String makeDistinctAndSortCharactersTemplate = "----------Make distinct and sort characters in string: ";
    private static final String splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoinTemplate = "----------Split all id with colon, select id with java keyword then sort then join: ";
    private static final String findIdHavingCharacterTemplate = "----------Find id having character: ";

    private static final String findAllFilesInPathAndSortTemplate = "---------Find all files in path and sort:    ";
    private static final String findParticularFileInPathAndSortTemplate = "----------Find File in present directory which starts with \"grad\",provided maximum depth=25 and sort : ";
    private static final String findParticularFileInPathAndSortWithWalkFunctionTemplate = "----------Find File in present directory which starts with \"grad\",provided maximum depth=25 and sort :  with Walk Function";
    private static final String readFileWithStreamFunctionTemplate = "---------Read file using Stream API: ";

    @Autowired
    private TopicService topicService;

    @RequestMapping("/topic/datetime/operation")
    public String showDateTimeOperation() {
        var timeClient = new SimpleTimeClient();
        var localDate = LocalDate.now();
        var localTime = LocalTime.now();
        var localDateTime = LocalDateTime.now();
        var zoneId = ZoneId.systemDefault();

        return "Current Date: " + localDate +
                "\nCurrent Time: " + localTime +
                "\nCurrent Date and Time: " + localDateTime +
                "\nSystem Default Time Zone ID: " + zoneId;
    }

    @RequestMapping("/topic/string/operation")
    public String showStringOperation() {
        var joinResult = topicService.joinStrings();
        var distinctAndSortResult = topicService.getDistinctAndSortedCharacters();
        var splitAndJoinResult = topicService.splitAndJoinStrings();
        var findIdHavingCharacterResult = topicService.findIdHavingCharacter();

        return joinTemplate + joinResult +
                "\n" + makeDistinctAndSortCharactersTemplate + distinctAndSortResult +
                "\n" + splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoinTemplate + splitAndJoinResult +
                "\n" + findIdHavingCharacterTemplate + findIdHavingCharacterResult;
    }

    @RequestMapping("/topic/file/operation")
    public String showFileOperation() {
        var findAllFilesInPathAndSort = topicService.findAllFilesInPathAndSort();
        var findParticularFileInPathAndSort = topicService.findParticularFileInPathAndSort();
        var findParticularFileInPathAndSortWithWalkFunction = topicService.findParticularFileInPathAndSortWithWalkFunction();
        var readFileWithStreamFunction = topicService.readFileWithStreamFunction();

        return findAllFilesInPathAndSortTemplate + findAllFilesInPathAndSort +
                "\n" + findParticularFileInPathAndSortTemplate + findParticularFileInPathAndSort +
                "\n" + findParticularFileInPathAndSortWithWalkFunctionTemplate + findParticularFileInPathAndSortWithWalkFunction +
                "\n" + readFileWithStreamFunctionTemplate + readFileWithStreamFunction;
    }
}