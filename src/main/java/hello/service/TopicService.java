package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public String userFunctionWithTry() {
        Path start = Paths.get("");
        int maxDepth = 5;
        try (var stream = Files.walk(start, maxDepth)) {
            return stream
                    .map(String::valueOf)
                    .filter(path -> path.startsWith("grad"))
                    .sorted()
                    .collect(Collectors.joining("; "));
        } catch (IOException e) {
            return " IO exception ";
        }
    }

    public String readFileWithStreamFunctionWithTry() {
        Path path = Paths.get("temp.txt");
        try (var reader = Files.newBufferedReader(path)) {
            return reader
                    .lines()
                    .filter(line -> line.contains("print"))
                    .map(line -> line.substring("print".length()))
                    .collect(Collectors.joining(","));
        } catch (IOException e) {
            return " IO exception ";
        }
    }

    public String findParticularFileInPathAndSortWithFindFunctionWithTry() {
        Path start = Paths.get("");
        int maxDepth = 25;
        try (var stream = Files.find(start, maxDepth, (path, attr) ->
                String.valueOf(path).startsWith("grad"))) {
            return stream
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining("; "));
        } catch (IOException e) {
            return " IO exception ";
        }
    }

    public String findParticularFileInPathAndSortWithWalkFunctionWithTryWithVar() {
        Path start = Paths.get("");
        int maxDepth = 5;
        try (var stream = Files.walk(start, maxDepth)) {
            return stream
                    .map(String::valueOf)
                    .filter(path -> path.startsWith("grad"))
                    .sorted()
                    .collect(Collectors.joining("; "));
        } catch (IOException e) {
            return " IO exception ";
        }
    }

    public String readFileWithStreamFunctionWithTryWithVar() {
        Path path = Paths.get("temp.txt");
        try (var reader = Files.newBufferedReader(path)) {
            return reader
                    .lines()
                    .filter(line -> line.contains("print"))
                    .map(line -> line.substring("print".length()))
                    .collect(Collectors.joining(","));
        } catch (IOException e) {
            return " IO exception ";
        }
    }

    public String findParticularFileInPathAndSortWithFindFunctionWithTryWithVar() {
        Path start = Paths.get("");
        int maxDepth = 25;
        try (var stream = Files.find(start, maxDepth, (path, attr) ->
                String.valueOf(path).startsWith("grad"))) {
            return stream
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining("; "));
        } catch (IOException e) {
            return " IO exception ";
        }
    }

}