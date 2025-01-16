package co.com.applicationcorp.dto;

import lombok.Data;

import java.util.List;

@Data
public class MemberDTO {

    private Long id;
    private String name;
    private List<BookDTO> borrowedBooks;
}