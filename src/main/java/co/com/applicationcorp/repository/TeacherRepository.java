package co.com.applicationcorp.repository;

import co.com.applicationcorp.domain.Course;
import co.com.applicationcorp.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {}