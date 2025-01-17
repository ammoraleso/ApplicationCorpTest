package co.com.applicationcorp.config;

import co.com.applicationcorp.domain.Book;
import co.com.applicationcorp.domain.Library;
import co.com.applicationcorp.domain.Member;
import co.com.applicationcorp.repository.BookRepository;
import co.com.applicationcorp.repository.LibraryRepository;
import co.com.applicationcorp.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private final LibraryRepository libraryRepository;

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final MemberRepository memberRepository;

    public DataInitializer(LibraryRepository libraryRepository, BookRepository bookRepository, MemberRepository memberRepository) {
        this.libraryRepository = libraryRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(String... args) {
        if (!libraryRepository.existsById(1L)) {
            List<Library> libraries = new ArrayList<>();
            Library library = new Library();
            library.setId(1L);
            library.setName("Library Test");
            library.setAddress("Address Test");

            libraryRepository.save(library);

            Book book1 = new Book();
            book1.setTitle("Book Test 1");
            book1.setAuthor("Author Test 1");
            book1.setStatus(Book.BookStatus.AVAILABLE);
            book1.setLibrary(library);

            Book book2 = new Book();
            book2.setTitle("Book Test 2");
            book2.setAuthor("Author Test 2");
            book2.setStatus(Book.BookStatus.BORROWED);
            book2.setLibrary(library);

            bookRepository.saveAll(List.of(book1, book2));

            libraries.add(library);
            // Initialize 10 members
            List<Member> members = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                Member member = new Member();
                member.setName("Member " + i);
                member.setLibraries(libraries);
                members.add(member);
            }

            memberRepository.saveAll(members);
        }
    }
}
