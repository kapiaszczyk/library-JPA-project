package dev.kapiaszczyk.bookstore.library.book.projections;

import dev.kapiaszczyk.bookstore.library.author.Author;

import java.util.List;

public interface BookInformationProjection {
    String getTitle();
    String getIsbn();
    List<String> getAuthors();
}
