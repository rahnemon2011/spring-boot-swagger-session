package com.hadi.student;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

/**
 * Student Class
 *
 * @author hadi
 */
@Entity
@Table(name = "STUDENT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Student implements Serializable, Comparable<Student> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement
    @ApiModelProperty(notes = "The database generated student ID")
    private Long id;

    @Version
    @ApiModelProperty(notes = "The auto-generated version of the student")
    private Integer version;

    @XmlElement
    @Size(min = 2)
    @ApiModelProperty(notes = "student name")
    private String name;

    @ApiModelProperty(notes = "hold date and time")
    private LocalDateTime birthDate;

    @ApiModelProperty(notes = "study period")
    private Period studyPeriod;

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public Period getStudyPeriod() {
        return studyPeriod;
    }

    public void setStudyPeriod(Period studyPeriod) {
        this.studyPeriod = studyPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return id.equals(student.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public int compareTo(Student o) {
        return this.getId().compareTo(o.getId());
    }
}
