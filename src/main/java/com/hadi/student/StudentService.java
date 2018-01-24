package com.hadi.student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Student Service
 *
 * @author hadi
 */
public interface StudentService {

    public List<Student> getAllStudents();

    public List<Student> getAllStudentsAsStream();

    public Student findByName(String name);

    public void deleteStudentById(Long id);

    public Student createStudent(Student student);

    public Student updateStudent(Student student);

    public Student findById(Long id);

    public Student findByBirthDate(LocalDateTime date);
}
