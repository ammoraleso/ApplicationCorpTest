package co.com.applicationcorp.repository;

import co.com.applicationcorp.domain.Course;
import co.com.applicationcorp.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {}