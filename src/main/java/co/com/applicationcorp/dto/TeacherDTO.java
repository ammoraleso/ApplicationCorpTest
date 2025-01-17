package co.com.applicationcorp.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeacherDTO {
    private Long id;
    private String name;
    private String subject;
    private List<CourseDTO> courses;
}
