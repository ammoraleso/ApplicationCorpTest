package co.com.applicationcorp.repository;

import co.com.applicationcorp.domain.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {

}