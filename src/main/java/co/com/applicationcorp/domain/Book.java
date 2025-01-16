package co.com.applicationcorp.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "library_id")
    private Library library;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "borrowed_by")
    private Member borrowedBy;

    public enum BookStatus {
        AVAILABLE,
        BORROWED
    }
}
