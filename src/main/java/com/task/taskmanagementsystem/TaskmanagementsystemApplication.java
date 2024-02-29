package com.task.taskmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.hibernate.internal.build.AllowSysOut;

@SpringBootApplication
public class TaskmanagementsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagementsystemApplication.class, args);
		System.out.println("Task Management System running....");
	}

}
