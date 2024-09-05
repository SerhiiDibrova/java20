package com.onlyfullstack.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.onlyfullstack.bean.Student;
import com.onlyfullstack.util.JavaInputFixture;

public class Stream {
	
	private static final String SAURABH = "Saurabh";

	public static void main(String[] args) {
		List<Student> students = JavaInputFixture.createList();
		
		filter(students);
		mapAndCollect(students);
		peek(students);
	}

	private static void mapAndCollect(List<Student> students) {
		System.out.println("######## Executing mapAndCollect() : ######## ");
		int age = students.stream()
						  .filter(student -> SAURABH.equals(student.getName()))
						  .mapToInt(Student::getAge)
						  .findFirst().orElse(0);
		System.out.printf("*** Age of %s is %d\n",SAURABH, age);
		
		Set<String> names = students.stream()
									.map(Student::getName) 
									.collect(Collectors.toSet());
		
		System.out.printf("*** All the names from the list is %s\n",names);
		
		Set<String> courses = students.stream()
									  .flatMap(student -> Arrays.stream(student.getCourses()))
									  .collect(Collectors.toSet());
		
		System.out.printf("*** All the courses from the list is %s\n",courses);
		System.out.println("######## Ending the execution of mapAndCollect() ######## ");
		
	}

	private static void filter(List<Student> students) {
		System.out.println("######## Executing filter() :######## ");
		System.out.println("*** Students who lives in Pune *** ");
		students.stream()
				.filter(student -> "Pune".equals(student.getCity()))
				.forEach(System.out::println);
		
		System.out.println("*** Students whos name is Saurabh *** ");
		Student stud = students.stream()
				.filter(student -> SAURABH.equals(student.getName()))
				.findAny().orElse(null); 
		
		System.out.println(stud);
		
		System.out.println("######## Ending the execution of filter() ######## ");
	}
	
	private static void peek(List<Student> students) {
		System.out.println("######## Executing peek() :######## ");
		students.stream()
				.peek(stud -> System.out.println("Processing Student Name : "+ stud.getName()))
				.filter(student -> "Mumbai".equals(student.getCity()))
				.peek(stud ->  System.out.println("Filtered Student Name :" + stud.getName()))
				.forEach(System.out::println);
		System.out.println("######## Ending the execution of peek() ######## ");
	}
}