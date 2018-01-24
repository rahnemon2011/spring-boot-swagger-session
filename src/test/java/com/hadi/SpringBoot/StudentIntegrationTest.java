package com.hadi.SpringBoot;

import com.hadi.student.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.fail;
import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentIntegrationTest {

    private static final String STUDENT_RESOURCE_URL = "/students";

    @Autowired
    private TestRestTemplate restTemplate;

    // GET ALL
    @Test
    public void getAllStudentsTest() {
        ResponseEntity<List<Student>> studentResponse =
                restTemplate.exchange(STUDENT_RESOURCE_URL,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
                        });
        List<Student> rates = studentResponse.getBody();
        rates.forEach(System.out::println);
    }

    // GET BY NAME
    @Test
    public void getByRestTemplateTest() {
        ResponseEntity<Student> response = restTemplate.getForEntity(STUDENT_RESOURCE_URL + "/Emma", Student.class);
        Student student = response.getBody();
        final HttpHeaders httpHeaders = response.getHeaders();

        assertThat(student.getName(), notNullValue());
        assertThat(student.getId(), is(4L));

        assertThat(response.getStatusCode(), equalTo(HttpStatus.FOUND));
        assertTrue(httpHeaders.getContentType().includes(MediaType.APPLICATION_JSON));
        assertFalse(httpHeaders.getContentType().includes(MediaType.APPLICATION_XML));

        System.out.println("student = " + student);
    }

    // POST
    @Test
    public void givenStudentService_whenPostForObject_thenCreatedObjectIsReturned() {
        final HttpEntity<Student> request = new HttpEntity<>(new Student(null, "Jafar"));
        final ResponseEntity<Student> response =
                restTemplate.postForEntity(STUDENT_RESOURCE_URL, request, Student.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        Student student = response.getBody();
        assertThat(student, notNullValue());
        assertThat(student.getName(), is("Jafar"));
    }

    // PUT
    @Test
    public void givenStudentService_whenPutExistingEntity_thenItIsUpdated() {

        final Student updatedInstance = new Student(4L, "Mina");
        final HttpEntity<Student> requestUpdate = new HttpEntity<>(updatedInstance);
        restTemplate.exchange(STUDENT_RESOURCE_URL, HttpMethod.PUT, requestUpdate, Void.class);
    }

    // DELETE
    @Test
    public void deleteByRestTemplateTest() {
        final String entityUrl = STUDENT_RESOURCE_URL + "/2";
        restTemplate.delete(entityUrl);
    }

}
