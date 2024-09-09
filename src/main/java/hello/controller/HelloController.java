package com.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyController {

    private static final String JOIN_TEMPLATE = "---------Join topics: ";
    private static final String MAKE_DISTINCT_AND_SORT_CHARACTERS_TEMPLATE = "-----Make distinct and sort characters in topic names: ";
    private static final String SPLIT_ALL_ID_WITH_COLON_SELECT_ID_WITH_JAVA_KEYWORD_THEN_SORT_THEN_JOIN_TEMPLATE = """
            user with java keyword then sort then join : """;
    private static final String FIND_ALL_FILES_IN_PATH_AND_SORT_TEMPLATE = "---------Find all files in path and sort:    ";
    private static final String FIND_PARTICULAR_FILE_IN_PATH_AND_SORT_TEMPLATE = "----------Find File in present directory which strats with \"grad\",provided maximum depth=25 and sort : ";
    private static final String FIND_PARTICULAR_FILE_IN_PATH_AND_SORT_WITH_WALK_FUNCTION_TEMPLATE = "----------Find File in present directory which strats with \"grad\",provided maximum depth=25 and sort :  with walk function";
    private static final String READ_FILE_WITH_STREAM_FUNCTION_TEMPLATE = "---------Read \"temp.txt\" file with stream functions, having \"print\" witin it:  ";

    @Autowired
    private TopicService topicService;

    @RequestMapping("/datetime")
    public String index() {
        var myTimeClient = new SimpleTimeClient();
        return """
                Date: %s
                Time: %s
                ZoneId: %s
                """.formatted(LocalDate.now(), LocalTime.now(), ZoneId.systemDefault());
    }

    @RequestMapping("/topics")
    public String topics() {
        var joinResult = topicService.joinTopics();
        var distinctAndSortCharactersResult = topicService.getDistinctAndSortedCharacters();
        var splitAndJoinResult = topicService.splitAndJoinTopics();
        return """
                %s%s
                %s%s
                %s%s
                """.formatted(JOIN_TEMPLATE, joinResult,
                        MAKE_DISTINCT_AND_SORT_CHARACTERS_TEMPLATE, distinctAndSortCharactersResult,
                        SPLIT_ALL_ID_WITH_COLON_SELECT_ID_WITH_JAVA_KEYWORD_THEN_SORT_THEN_JOIN_TEMPLATE, splitAndJoinResult);
    }

    @RequestMapping("/files")
    public String files() {
        var findAllFilesInPathAndSortResult = topicService.findAllFilesInPathAndSort();
        var findParticularFileInPathAndSortResult = topicService.findParticularFileInPathAndSort();
        var findParticularFileInPathAndSortWithWalkFunctionResult = topicService.findParticularFileInPathAndSortWithWalkFunction();
        var readFileWithStreamFunctionResult = topicService.readFileWithStreamFunction();
        return """
                %s%s
                %s%s
                %s%s
                %s%s
                """.formatted(FIND_ALL_FILES_IN_PATH_AND_SORT_TEMPLATE, findAllFilesInPathAndSortResult,
                        FIND_PARTICULAR_FILE_IN_PATH_AND_SORT_TEMPLATE, findParticularFileInPathAndSortResult,
                        FIND_PARTICULAR_FILE_IN_PATH_AND_SORT_WITH_WALK_FUNCTION_TEMPLATE, findParticularFileInPathAndSortWithWalkFunctionResult,
                        READ_FILE_WITH_STREAM_FUNCTION_TEMPLATE, readFileWithStreamFunctionResult);
    }
}