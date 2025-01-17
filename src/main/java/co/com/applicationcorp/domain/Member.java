package co.com.applicationcorp.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "borrowedBy")
    private List<Book> borrowedBooks;

    @ManyToMany
    @JoinTable(
            name = "member_library",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "library_id")
    )
    private List<Library> libraries;
}