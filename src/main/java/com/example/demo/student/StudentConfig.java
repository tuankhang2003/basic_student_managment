package com.example.demo.student;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student mariam= new Student( "Maria", LocalDate.of(2000, Month.FEBRUARY, 5),
                    "maria1@gmail.com");
            Student Luis= new Student( "Luis", LocalDate.of(2001, Month.FEBRUARY, 3),
                    "Luis@gmail.com");
            Student Alex= new Student( "Alex", LocalDate.of(2002, Month.FEBRUARY, 5),
                    "Alex@gmail.com");
            Student Bush= new Student( "Bush", LocalDate.of(2003, Month.FEBRUARY, 5),
                    "bush1@gmail.com");
            studentRepository.saveAll(List.of(mariam,Alex, Bush, Luis));
        };
    }
}
