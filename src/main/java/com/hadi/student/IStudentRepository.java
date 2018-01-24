package com.hadi.student;

import java.util.List;
import java.util.stream.Stream;

/**
 * This is the Interface that is used for customizing the StudentRepository.
 *
 * @author hadi on 5/26/17.
 */
public interface IStudentRepository<T> {

    List<T> specialSelect(final Long studentId);
}
