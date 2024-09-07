package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final String joinTemplate = "Joining All String ID's with JOIN method: ";
    private static final String makeDistinctAndSortCharactersTemplate = "-------------Get all ID characters, select distinct and sort with ID=   ";
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

    @RequestMapping("/datetime")
    public String index() {
        var myTimeClient = new SimpleTimeClient();
        var localDateTime = java.time.LocalDateTime.now();
        return """
                Current Date and Time: %s
                """.formatted(localDateTime);
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

    @RequestMapping("/topic/string/operation")
    public String showStringOperation() {
        var findAllTopics = topicService.findAllTopics();
        var joinedTopics = String.join(", ", findAllTopics);

        return """
                %s%s
                """.formatted(joinTemplate, joinedTopics);
    }
}