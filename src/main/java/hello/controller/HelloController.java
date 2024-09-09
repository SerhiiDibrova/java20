package com.example.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TopicController {

    private static final String findAllFilesInPathAndSortTemplate = "----------Find All Files in present directory and sort : ";
    private static final String findParticularFileInPathAndSortTemplate = "----------Find File in present directory which starts with \"grad\",provided maximum depth=25 and sort : ";
    private static final String findParticularFileInPathAndSortWithWalkFunctionTemplate = "----------Find File in present directory which starts with \"grad\",provided maximum depth=25 and sort :  with walk function";
    private static final String readFileWithStreamFunctionTemplate = "---------Read file with stream function: ";

    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "/topic/datetime/operation", method = RequestMethod.GET)
    public String showDateTimeOperation() {
        var timeClient = new SimpleTimeClient();
        var currentDateTime = LocalDateTime.now();

        return """
                Current Date and Time: %s
                """.formatted(currentDateTime);
    }

    @RequestMapping(value = "/topic/string/operation", method = RequestMethod.GET)
    public String showStringOperation() {
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

    @RequestMapping(value = "/topic/file/operation", method = RequestMethod.GET)
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