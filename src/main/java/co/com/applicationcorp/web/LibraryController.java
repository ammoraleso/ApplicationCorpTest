package co.com.applicationcorp.web;

import co.com.applicationcorp.dto.BookDTO;
import co.com.applicationcorp.service.LibraryService;
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

    @GetMapping("/{libraryId}/books")
    public List<BookDTO> getAvailableBooks(@PathVariable Long libraryId,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "1") int size) {
        return libraryService.getAvailableBooks(libraryId, page, size);
    }

    @PostMapping("/{libraryId}/books")
    public BookDTO addBook(@PathVariable Long libraryId, @RequestBody BookDTO book) {
        return libraryService.addBook(libraryId, book);
    }
}
