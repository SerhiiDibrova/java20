package com.example.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Controller
public class TopicController {

    private static final String joinTemplate = "----------Join Strings: ";
    private static final String makeDistinctAndSortCharactersTemplate = "----------Make distinct and sort characters: ";
    private static final String splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoinTemplate = "----------Split all id with colon, select id with java keyword then sort then join: ";
    private static final String findIdHavingCharacterTemplate = "----------Find id having character: ";
    private static final String findAllFilesInPathAndSortTemplate = "----------Find All Files in present directory and sort : ";
    private static final String findParticularFileInPathAndSortTemplate = "----------Find File in present directory which strats with \"grad\",provided maximum depth=25 and sort : ";
    private static final String findParticularFileInPathAndSortWithWalkFunctionTemplate = "----------Find File in present directory which strats with \"grad\",provided maximum depth=25 and sort with walk function: ";
    private static final String readFileWithStreamFunctionTemplate = "---------Read file with stream function: ";

    @Autowired
    private TopicService topicService;

    @RequestMapping("/topic/datetime/operation")
    public String showDateTimeOperation() {
        var timeClient = new SimpleTimeClient();
        var localDate = LocalDate.now();
        var localDateTime = LocalDateTime.now();
        var localTime = LocalTime.now();
        var zoneId = ZoneId.systemDefault();

        return """
                Current Date: %s
                Current DateTime: %s
                Current Time: %s
                System Default Zone ID: %s
                """.formatted(localDate, localDateTime, localTime, zoneId);
    }

    @RequestMapping("/topic/string/operation")
    public String showStringOperation() {
        var joinResult = topicService.joinStrings();
        var distinctAndSortResult = topicService.getDistinctAndSortedCharacters();
        var splitAndJoinResult = topicService.splitAndJoinStrings();
        var findIdHavingCharacterResult = topicService.findIdHavingCharacter();

        return """
                %s%s
                %s%s
                %s%s
                %s%s
                """.formatted(joinTemplate, joinResult,
                        makeDistinctAndSortCharactersTemplate, distinctAndSortResult,
                        splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoinTemplate, splitAndJoinResult,
                        findIdHavingCharacterTemplate, findIdHavingCharacterResult);
    }

    @RequestMapping("/topic/file/operation")
    public String showFileOperation() {
        var findAllFilesInPathAndSort = topicService.findAllFilesInPathAndSort();
        var findParticularFileInPathAndSort = topicService.findParticularFileInPathAndSort();
        var findParticularFileInPathAndSortWithWalkFunction = topicService.findParticularFileInPathAndSortWithWalkFunction();
        var readFileWithStreamFunction = topicService.readFileWithStreamFunction();

        return """
                %s%s
                %s%s
                %s%s
                %s%s
                """.formatted(findAllFilesInPathAndSortTemplate, findAllFilesInPathAndSort,
                        findParticularFileInPathAndSortTemplate, findParticularFileInPathAndSort,
                        findParticularFileInPathAndSortWithWalkFunctionTemplate, findParticularFileInPathAndSortWithWalkFunction,
                        readFileWithStreamFunctionTemplate, readFileWithStreamFunction);
    }
}