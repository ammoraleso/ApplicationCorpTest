package co.com.applicationcorp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class BookDTO {

    private Long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Author is mandatory")
    private String author;
    @NotBlank(message = "Status is mandatory")
    @Pattern(
            regexp = "AVAILABLE|BORROWED",
            message = "Status must be either AVAILABLE or BORROWED"
    )
    private String status;
    private LibraryDTO library;
    private Long borrowedById;
}
