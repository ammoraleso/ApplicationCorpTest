package co.com.applicationcorp.web;

import co.com.applicationcorp.dto.BookDTO;
import co.com.applicationcorp.dto.LibraryDTO;
import co.com.applicationcorp.dto.MemberDTO;
import co.com.applicationcorp.service.LibraryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/libraries/v1")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("")
    public List<LibraryDTO> getAvailableBooks(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        return libraryService.getAll(page, size);
    }

    @GetMapping("/{libraryId}/members")
    public List<MemberDTO> getAllMembersByLibrary(@PathVariable Long libraryId,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        return libraryService.getAllMembersByLibrary(libraryId, page, size);
    }

    @GetMapping("/{libraryId}/books")
    public List<BookDTO> getAvailableBooks(@PathVariable Long libraryId,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        return libraryService.getAvailableBooks(libraryId, page, size);
    }

    @PostMapping("/{libraryId}/books")
    public BookDTO addBook(@PathVariable Long libraryId, @Valid @RequestBody BookDTO book) {
        return libraryService.addBook(libraryId, book);
    }

    @PostMapping("/{memberId}/borrow/{bookId}")
    public BookDTO borrowBook(@PathVariable Long memberId, @PathVariable Long bookId) {
        return libraryService.borrowBook(bookId, memberId);
    }

    @PostMapping("/return/{bookId}")
    public BookDTO returnBook(@PathVariable Long bookId) {
        return libraryService.returnBook(bookId);
    }
}
