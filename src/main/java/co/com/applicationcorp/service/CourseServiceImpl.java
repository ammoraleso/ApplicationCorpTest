package co.com.applicationcorp.service;

import co.com.applicationcorp.domain.*;
import co.com.applicationcorp.dto.*;
import co.com.applicationcorp.repository.*;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> getAllCourses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> pageResult = courseRepository.findAll(pageable);
        List<Course> courses = pageResult.getContent();
        return courses.stream().map(course -> modelMapper.map(course, CourseDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> getStudentsInCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        return course.getStudents().stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = modelMapper.map(studentDTO, Student.class);
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDTO.class);
    }

    @Override
    @Transactional
    public void assignStudentToCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        course.getStudents().add(student);
        courseRepository.save(course);
    }

    @Override
    @Transactional
    public String generateStudentReport(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        StringBuilder report = new StringBuilder();
        report.append("Student Report\n");
        report.append("Name: ").append(student.getName()).append("\n");
        report.append("Age: ").append(student.getAge()).append("\n");
        report.append("Courses: \n");
        for (Course course : student.getCourses()) {
            report.append("- ").append(course.getName()).append(": ").append(course.getDescription()).append("\n");
        }
        return report.toString();
    }
}