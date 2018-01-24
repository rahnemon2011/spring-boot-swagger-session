package com.hadi.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Student Service
 *
 * @author hadi
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getAllStudentsAsStream() {
        LOGGER.error("Get All Students As Stream!");
        Stream<Student> allStudentsAsStream = studentRepository.getAllStudentsAsStream();
        return allStudentsAsStream.collect(Collectors.toList());
    }

    public Student findByName(String name) {
        return studentRepository.findByName(name);
    }

    public void deleteStudentById(Long id) {
        studentRepository.delete(id);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findById(Long id) {
        return studentRepository.findOne(id);
    }

    public Student findByBirthDate(LocalDateTime date) {
        return studentRepository.findByBirthDate(date);

    }
}
