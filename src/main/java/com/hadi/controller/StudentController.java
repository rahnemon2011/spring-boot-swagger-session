package com.hadi.controller;

import com.hadi.student.Student;
import com.hadi.student.StudentService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Student Controller
 *
 * @author hadi
 */
@RestController
@RequestMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin()
@Api(value = "Spring Boot Sample", description = "Student Operations")
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ApiOperation(value = "View a list of available students", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping()
    public Collection<Student> getAllStudents() {
        System.err.println("GET");
        return studentService.getAllStudents();
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Student> getAllStudentsInXML() {
        return studentService.getAllStudents();
    }

    @ApiOperation(value = "Search a student with a name")
    @GetMapping(value = "/{name}")
    public ResponseEntity<Student> findByName(@PathVariable String name, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String success = (String) session.getAttribute("studentName");
        LOGGER.info("studentName in findByName= " + success);

        session.setAttribute("studentName", name);
        Student student = studentService.findByName(name);
        return new ResponseEntity<Student>(student, HttpStatus.FOUND);
    }

    @ApiOperation(value = "Search a student with an ID", response = Student.class)
    @GetMapping(value = "/id/{id:\\d+}")
    public Student findById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @ApiOperation(value = "Delete a student")
    @DeleteMapping(value = "/{id}")
    public void deleteStudentById(@PathVariable("id") Long id) {
        studentService.deleteStudentById(id);
    }

    @ApiOperation(value = "Add a student")
    @PostMapping
    public Student createStudent(@RequestBody @Valid Student student, BindingResult bindingResult, HttpSession httpSession) {
        System.err.println("POST");
        studentService.createStudent(student);
        return studentService.findByName(student.getName());
    }

    @ApiOperation(value = "Update a student")
    @PutMapping
    public Student updateStudent(@RequestBody @Valid Student student, BindingResult bindingResult) {
        System.err.println("PUT");
        return studentService.updateStudent(student);
    }

}



