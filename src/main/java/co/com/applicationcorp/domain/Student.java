package co.com.applicationcorp.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL)
    private List<Course> courses;
}
