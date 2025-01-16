package co.com.applicationcorp.config;

import co.com.applicationcorp.domain.Book;
import co.com.applicationcorp.domain.Library;
import co.com.applicationcorp.repository.BookRepository;
import co.com.applicationcorp.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private final LibraryRepository libraryRepository;

    @Autowired
    private final BookRepository bookRepository;

    public DataInitializer(LibraryRepository libraryRepository, BookRepository bookRepository) {
        this.libraryRepository = libraryRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        if (!libraryRepository.existsById(1L)) {
            Library library = new Library();
            library.setId(1L);
            library.setName("Library Test");
            library.setAddress("Address Test");

            libraryRepository.save(library);

            Book book = new Book();
            book.setTitle("Book Test");
            book.setAuthor("Author Test");
            book.setStatus(Book.BookStatus.AVAILABLE);
            book.setLibrary(library);

            bookRepository.save(book);
        }
    }
}
