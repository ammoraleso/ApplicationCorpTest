package co.com.applicationcorp.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Book> books;

    @ManyToMany(mappedBy = "libraries")
    private List<Member> members;
}
