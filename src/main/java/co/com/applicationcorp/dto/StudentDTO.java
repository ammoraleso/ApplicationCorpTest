package co.com.applicationcorp.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {
    private Long id;
    private String name;
    private int age;
    private List<CourseDTO> courses;
}