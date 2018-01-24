package com.hadi.student;

import com.hadi.dataaccess.GenericRepository;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * This is the Interface that is used for customizing the StudentRepository.
 * You can access to EntityManager by extending GenericRepository
 *
 * @author hadi on 5/26/17.
 */
@Component
public class StudentRepositoryImpl
        extends GenericRepository implements IStudentRepository<Student> {

    @Override
    public List<Student> specialSelect(Long studentId) {
        String sqlQuery = "select * from student s where s.id != :studentId";
        return getEntityManager().createNativeQuery(sqlQuery, Student.class)
                .setParameter("studentId", studentId).getResultList();
    }

}