package com.hadi;

import com.hadi.student.Student;
import com.hadi.student.StudentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

/**
 * Main class.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner runner(StudentService studentService) {
        System.out.println("Runner Executed.");
        return args -> {
            Stream.of("Hadi", "Peter", "Steve", "Emma")
                    .forEach(x -> studentService.createStudent(new Student(null, x)));
        };
    }
}
