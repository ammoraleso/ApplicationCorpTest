package co.com.applicationcorp.service;

import co.com.applicationcorp.domain.Book;
import co.com.applicationcorp.domain.Library;
import co.com.applicationcorp.domain.Member;
import co.com.applicationcorp.dto.BookDTO;
import co.com.applicationcorp.dto.LibraryDTO;
import co.com.applicationcorp.dto.MemberDTO;
import co.com.applicationcorp.repository.*;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
    @Transactional(readOnly = true)
    public List<BookDTO> getAvailableBooks(Long libraryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> pageResult = bookRepository.findByLibraryIdAndStatus(libraryId,Book.BookStatus.AVAILABLE, pageable);
        List<Book> books = pageResult.getContent();
        return books.stream().map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
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
    @Transactional
    public BookDTO borrowBook(Long bookId, Long memberId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getStatus() == Book.BookStatus.BORROWED) {
            throw new RuntimeException("Book is already borrowed");
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        book.setStatus(Book.BookStatus.BORROWED);
        book.setBorrowedBy(member);
        bookRepository.save(book);
        return  modelMapper.map(book, BookDTO.class);
    }

    @Override
    @Transactional
    public BookDTO returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setStatus(Book.BookStatus.AVAILABLE);
        book.setBorrowedBy(null);
        bookRepository.save(book);
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LibraryDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Library> pageResult = libraryRepository.findAll(pageable);
        List<Library> libraries = pageResult.getContent();
        return libraries.stream().map(library -> modelMapper.map(library, LibraryDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberDTO> getAllMembersByLibrary(Long libraryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Member> pageResult = memberRepository.findByLibrariesId(libraryId, pageable);
        List<Member> members = pageResult.getContent();

        return members.stream()
                .map(member -> MemberDTO.convertToDTO(member, modelMapper))
                .toList();
    }

}
