package com.onlyfullstack.stream;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.onlyfullstack.bean.Student;
import com.onlyfullstack.util.JavaInputFixture;

public class AdvanceCollectors {

    private static final Logger LOGGER = Logger.getLogger(AdvanceCollectors.class.getName());

    public static void main(String[] args) {
        List<Student> students = JavaInputFixture.createList();
        if (students != null && !students.isEmpty()) {
            joiningCollector(students);
            summaryStatisticsCollector(students);
            partitioningByCollector(students);
            groupingByCollector(students);
            mappingByCollector(students);
        } else {
            LOGGER.info("No students found.");
        }
    }

    private static void joiningCollector(List<Student> students) {
        if (students != null && !students.isEmpty()) {
            try {
                String allStudents = students.stream()
                        .map(Student::getName)
                        .collect(Collectors.joining(" | "));
                LOGGER.info("Collectors.joining() : " + allStudents);
            } catch (NullPointerException e) {
                LOGGER.severe("Error occurred while joining student names: " + e.getMessage());
            }
        }
    }

    private static void summaryStatisticsCollector(List<Student> students) {
        if (students != null && !students.isEmpty()) {
            try {
                DoubleSummaryStatistics statistics = students.stream()
                        .mapToDouble(Student::getAge)
                        .summaryStatistics();

                LOGGER.info("summaryStatistics() : " + statistics);
                LOGGER.info("Total Count : " + statistics.getCount());
                LOGGER.info("Total Sum : " + statistics.getSum());
                LOGGER.info("Minimum Age : " + statistics.getMin());
                LOGGER.info("Maximum Age : " + statistics.getMax());
                LOGGER.info("Average Age : " + statistics.getAverage());
            } catch (NullPointerException e) {
                LOGGER.severe("Error occurred while calculating summary statistics: " + e.getMessage());
            }
        }
    }

    private static void partitioningByCollector(List<Student> students) {
        if (students != null && !students.isEmpty()) {
            try {
                Map<Boolean, List<Student>> partition = students.stream()
                        .collect(Collectors.partitioningBy(stud -> stud.getCity().equals("Pune")));

                LOGGER.info("Students living in Pune : " + partition.get(true));
                LOGGER.info("Students not living in Pune : " + partition.get(false));
            } catch (NullPointerException e) {
                LOGGER.severe("Error occurred while partitioning students: " + e.getMessage());
            }
        }
    }

    private static void groupingByCollector(List<Student> students) {
        if (students != null && !students.isEmpty()) {
            try {
                Map<String, List<Student>> groupBy = students.stream()
                        .collect(Collectors.groupingBy(Student::getCity));

                LOGGER.info("groupingByCollector : " + groupBy);
            } catch (NullPointerException e) {
                LOGGER.severe("Error occurred while grouping students: " + e.getMessage());
            }
        }
    }

    private static void mappingByCollector(List<Student> students) {
        if (students != null && !students.isEmpty()) {
            try {
                Map<String, Set<String>> mappingBy = students.stream()
                        .collect(Collectors.groupingBy(Student::getCity,
                                Collectors.mapping(Student::getName, Collectors.toSet())));

                LOGGER.info("mappingByCollector : " + mappingBy);
            } catch (NullPointerException e) {
                LOGGER.severe("Error occurred while mapping students: " + e.getMessage());
            }
        }
    }
}