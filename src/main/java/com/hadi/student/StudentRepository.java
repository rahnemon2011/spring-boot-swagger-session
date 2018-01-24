package com.hadi.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

/**
 * Student Repository
 *
 * @author hadi
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, IStudentRepository {

    Student findByName(String name);

    Student findByBirthDate(LocalDateTime date);

    @Query("select s from Student s")
    Stream<Student> getAllStudentsAsStream();
}
