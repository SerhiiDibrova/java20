package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @RequestMapping(value = "/time", method = RequestMethod.GET)
    public String time() {
        var time = topicService.getTime();
        return """
                Time: %s""".formatted(time);
    }

    @RequestMapping(value = "/topics", method = RequestMethod.GET)
    public String topics() {
        var topics = topicService.getTopics();
        return topics.stream()
                .map(Topic::toString)
                .collect(Collectors.joining("\n"));
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join() {
        var joinedString = topicService.joinStrings();
        return """
                Joined string: %s""".formatted(joinedString);
    }

    @RequestMapping(value = "/distinctAndSort", method = RequestMethod.GET)
    public String distinctAndSort() {
        var distinctAndSortedString = topicService.getDistinctAndSortedString();
        return """
                Distinct and sorted characters: %s""".formatted(distinctAndSortedString);
    }

    @RequestMapping(value = "/splitAndJoin", method = RequestMethod.GET)
    public String splitAndJoin() {
        var splitAndJoinedString = topicService.splitAndJoinStrings();
        return """
                Split and joined string: %s""".formatted(splitAndJoinedString);
    }

    @RequestMapping(value = "/findIdHavingCharacter", method = RequestMethod.GET)
    public String findIdHavingCharacter() {
        var idHavingCharacter = topicService.findIdHavingCharacter('g');
        return """
                Id having character 'g': %s""".formatted(idHavingCharacter);
    }

    @RequestMapping(value = "/findAllFilesInPathAndSort", method = RequestMethod.GET)
    public String findAllFilesInPathAndSort() {
        var allFilesInPathAndSorted = topicService.findAllFilesInPathAndSort();
        return """
                All files in path and sorted: %s""".formatted(allFilesInPathAndSorted);
    }

    @RequestMapping(value = "/findParticularFileInPathAndSort", method = RequestMethod.GET)
    public String findParticularFileInPathAndSort() {
        var particularFileInPathAndSorted = topicService.findParticularFileInPathAndSort();
        return """
                Particular file in path and sorted: %s""".formatted(particularFileInPathAndSorted);
    }

    @RequestMapping(value = "/findParticularFileInPathAndSortWithWalkFunction", method = RequestMethod.GET)
    public String findParticularFileInPathAndSortWithWalkFunction() {
        var particularFileInPathAndSortedWithWalkFunction = topicService.findParticularFileInPathAndSortWithWalkFunction();
        return """
                Particular file in path and sorted with walk function: %s""".formatted(particularFileInPathAndSortedWithWalkFunction);
    }

    @RequestMapping(value = "/readFileWithStreamFunction", method = RequestMethod.GET)
    public String readFileWithStreamFunction() {
        var fileContent = topicService.readFileWithStreamFunction();
        return """
                File content read with stream function: %s""".formatted(fileContent);
    }
}