package co.com.applicationcorp.service;

import co.com.applicationcorp.domain.Book;
import co.com.applicationcorp.dto.BookDTO;

import java.util.List;

public interface LibraryService {

    List<BookDTO> getAvailableBooks(Long libraryId, int page, int size);

    BookDTO addBook(Long libraryId, BookDTO book);

    BookDTO borrowBook(Long bookId, Long memberId);

    BookDTO returnBook(Long bookId);
}
