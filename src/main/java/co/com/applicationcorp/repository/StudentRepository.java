package co.com.applicationcorp.repository;

import co.com.applicationcorp.domain.Book;
import co.com.applicationcorp.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {}