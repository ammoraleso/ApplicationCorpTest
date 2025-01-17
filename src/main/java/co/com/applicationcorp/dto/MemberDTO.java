package co.com.applicationcorp.dto;

import co.com.applicationcorp.domain.Member;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberDTO {

    private Long id;
    private String name;
    private List<BookDTO> borrowedBooks;
    private List<LibraryDTO> libraries;

    public static MemberDTO convertToDTO(Member member, ModelMapper modelMapper) {
        MemberDTO memberDTO = modelMapper.map(member, MemberDTO.class);

        memberDTO.setBorrowedBooks(member.getBorrowedBooks()
                .stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .toList());

        memberDTO.setLibraries(member.getLibraries()
                .stream()
                .map(library -> modelMapper.map(library, LibraryDTO.class))
                .toList());

        return memberDTO;
    }
}