package co.com.applicationcorp.service;

import co.com.applicationcorp.domain.Book;
import co.com.applicationcorp.domain.Library;
import co.com.applicationcorp.dto.BookDTO;
import co.com.applicationcorp.dto.LibraryDTO;
import co.com.applicationcorp.dto.MemberDTO;

import java.util.List;

public interface LibraryService {

    List<BookDTO> getAvailableBooks(Long libraryId, int page, int size);

    BookDTO addBook(Long libraryId, BookDTO book);

    BookDTO borrowBook(Long bookId, Long memberId);

    BookDTO returnBook(Long bookId);

    List<LibraryDTO> getAll(int page, int size);

    List<MemberDTO> getAllMembersByLibrary(Long libraryId, int page, int size);
}
