package co.com.applicationcorp.service;

import co.com.applicationcorp.domain.Book;
import co.com.applicationcorp.domain.Library;
import co.com.applicationcorp.dto.BookDTO;
import co.com.applicationcorp.repository.*;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<BookDTO> getAvailableBooks(Long libraryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> pageResult = bookRepository.findByLibraryIdAndStatus(libraryId,Book.BookStatus.AVAILABLE, pageable);
        List<Book> books = pageResult.getContent();
        return books.stream().map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList());
    }

    @Override
    public BookDTO addBook(Long libraryId, BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new RuntimeException("Library not found"));
        book.setLibrary(library);
        book.setStatus(Book.BookStatus.AVAILABLE);
        bookRepository.save(book);
        return  modelMapper.map(book, BookDTO.class);
    }

    @Override
    public BookDTO borrowBook(Long bookId, Long memberId) {
        return null;
    }

    @Override
    public BookDTO returnBook(Long bookId) {
        return null;
    }
}
