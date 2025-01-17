package co.com.applicationcorp.service;

import co.com.applicationcorp.dto.*;

import java.util.List;

public interface CourseService {
    List<CourseDTO> getAllCourses(int page, int size);

    List<StudentDTO> getStudentsInCourse(Long courseId);

    StudentDTO createStudent(StudentDTO studentDTO);

    void assignStudentToCourse(Long studentId, Long courseId);

    String generateStudentReport(Long studentId);
}
