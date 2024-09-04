package com.onlyfullstack.stream;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.onlyfullstack.bean.Student;
import com.onlyfullstack.util.JavaInputFixture;

public class AdvanceCollectors {

    public static void main(String[] args) {
        List<Student> students = JavaInputFixture.createList();
        joiningCollector(students);
        summaryStatisticsCollector(students);
        partitioningByCollector(students);
        groupingByCollector(students);
        mappingByCollector(students);
    }

    private static void joiningCollector(List<Student> students) {
        String allStudents = students.stream()
                .map(Student::getName)
                .collect(Collectors.joining(" | "));
        System.out.println(allStudents);
    }

    private static void summaryStatisticsCollector(List<Student> students) {
        DoubleSummaryStatistics statistics = students.stream()
                .mapToDouble(Student::getAge)
                .summaryStatistics();
        System.out.println(statistics);
        System.out.println("Total Count : " + statistics.getCount());
        System.out.println("Total Sum : " + statistics.getSum());
        System.out.println("Minimum Age : " + statistics.getMin());
        System.out.println("Maximum Age : " + statistics.getMax());
        System.out.println("Average Age : " + statistics.getAverage());
    }

    private static void partitioningByCollector(List<Student> students) {
        Map<Boolean, List<Student>> partition = students.stream()
                .collect(Collectors.partitioningBy(stud -> stud.getCity().equals("Pune")));
        System.out.println(partition);
    }

    private static void groupingByCollector(List<Student> students) {
        Map<String, List<Student>> groupBy = students.stream()
                .collect(Collectors.groupingBy(Student::getCity));
        System.out.println(groupBy);
    }

    private static void mappingByCollector(List<Student> students) {
        Map<String, Set<String>> mappingBy = students.stream()
                .collect(Collectors.groupingBy(Student::getCity,
                        Collectors.mapping(Student::getName, Collectors.toSet())));
        System.out.println(mappingBy);
    }
}