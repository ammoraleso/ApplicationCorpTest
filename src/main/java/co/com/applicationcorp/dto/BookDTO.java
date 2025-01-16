package co.com.applicationcorp.dto;

import lombok.Data;

@Data
public class BookDTO {

    private Long id;
    private String title;
    private String author;
    private String status; // AVAILABLE o BORROWED
    private LibraryDTO library;
    private Long borrowedById;
}
