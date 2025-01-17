package co.com.applicationcorp.web;

import co.com.applicationcorp.dto.CourseDTO;
import co.com.applicationcorp.dto.StudentDTO;
import co.com.applicationcorp.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<CourseDTO> getAllCourses(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        return courseService.getAllCourses(page, size);
    }

    @GetMapping("/{courseId}/students")
    public List<StudentDTO> getStudentsInCourse(@PathVariable Long courseId) {
        return courseService.getStudentsInCourse(courseId);
    }

    @PostMapping("/students")
    public StudentDTO createStudent(@RequestBody StudentDTO studentDTO) {
        return courseService.createStudent(studentDTO);
    }

    @PostMapping("/{courseId}/students/{studentId}")
    public void assignStudentToCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        courseService.assignStudentToCourse(studentId, courseId);
    }

    @GetMapping("/students/{studentId}/report")
    public String generateStudentReport(@PathVariable Long studentId) {
        return courseService.generateStudentReport(studentId);
    }
}
