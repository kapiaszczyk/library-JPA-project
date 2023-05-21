package dev.kapiaszczyk.bookstore.library.credit.dto;

import dev.kapiaszczyk.bookstore.library.author.dto.AuthorDTO;

public class CreditDTO {

    private Long id;
    private AuthorDTO author;

    public CreditDTO() {
    }

    public CreditDTO(Long id, AuthorDTO author) {
        this.id = id;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }
}
