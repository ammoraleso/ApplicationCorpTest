package co.com.applicationcorp.repository;

import co.com.applicationcorp.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT DISTINCT b FROM Book b WHERE b.library.id = :libraryId AND b.status = :status")
    Page<Book> findByLibraryIdAndStatus(Long libraryId, Book.BookStatus status, Pageable pageable);
}