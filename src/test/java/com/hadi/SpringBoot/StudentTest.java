package com.hadi.SpringBoot;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hadi.student.Student;
import com.hadi.student.StudentRepository;
import com.hadi.student.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StudentTest {

    private static final String STUDENT_RESOURCE_URL = "/students";

    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    @Before
    public void before() {
        objectMapper = new ObjectMapper();
    }

    // GET ALL
    @Test
    public void getAllStudentsTest() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get(STUDENT_RESOURCE_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Emma")))
                .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();

        List<Student> myObjects = objectMapper.readValue(responseJson, new TypeReference<List<Student>>() {
        });
        myObjects.forEach(System.out::println);
    }

    @Test
    public void getAllStudentsAsStreamTest() {
        List<Student> allStudentsAsStream = studentService.getAllStudentsAsStream();
        allStudentsAsStream.forEach(System.out::println);
    }

    @Test
    public void getStudents_isNotEqualId() {
        List students = studentRepository.specialSelect(1L);
        Assert.notNull(students, "students list must have one element!");
        students.stream().forEach(System.out::println);
    }

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        Student stu = new Student();
        stu.setName("Hadi");
        stu.setBirthDate(LocalDateTime.now());
        stu.setStudyPeriod(Period.ZERO);

        // entityManager.persist(cu); entityManager.flush();
        studentService.createStudent(stu);

        // when
        // Customer found = customerRepository.findByName(cu.getName());
        Student found = studentService.findByBirthDate(stu.getBirthDate());

        System.out.println(found);
        // then
        Assertions.assertThat(found.getName()).isEqualTo(stu.getName());
        Assertions.assertThat(found.getBirthDate()).isEqualTo(LocalDateTime.now());
    }
} 