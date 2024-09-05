package com.onlyfullstack.util;

import java.util.List;
import com.onlyfullstack.bean.Student;

public class JavaInputFixture {
	
	public static List<Student> createList() {

		var student1 = new Student("Saurabh", "Pune", 26, new String[] {"Java","Spring","Hibernate"});
		var student2 = new Student("Robert", "Pune", 25, new String[] {".Net","HTML","CSS"});
		var student3 = new Student("John", "Mumbai", 21, new String[] {"Typescript","Angular 4"});
		var student4 = new Student("Roman", "Pune", 18, new String[] {"ReactJS","JavaScript","Fidler"});
		var student5 = new Student("Randy", "Mumbai", 17, new String[] {"Java","Spring Cloud","Spring Data"});
		
		return List.of(student1, student2, student3, student4, student5);
	}

}