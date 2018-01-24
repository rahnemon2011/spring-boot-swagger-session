package com.hadi.SpringBoot;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hadi.controller.StudentController;
import com.hadi.student.Student;
import com.hadi.student.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest({StudentService.class, StudentController.class})
public class StudentMockMVCTest {

    private static final String STUDENT_RESOURCE_URL = "/students";

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Before
    public void setup() {
        System.out.println("Setup Executed.");
//        objectMapper = new ObjectMapper();
        List<Student> students = new ArrayList<>();
        Arrays.asList("Emma,Hadi,Mahdi,Fardi".split(","))
                .forEach(name -> students.add(new Student(null, name)));
        when(studentService.getAllStudents()).thenReturn(students);
        when(studentService.createStudent(new Student(5L, null)))
                .thenReturn(new Student(5L, "Kazem"));
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

}