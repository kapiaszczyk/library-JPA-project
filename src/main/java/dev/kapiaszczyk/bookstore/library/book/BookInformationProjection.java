package dev.kapiaszczyk.bookstore.library.book;

import java.util.List;

public interface BookInformationProjection {
    String getTitle();
    String getIsbn();
    List<String> getAuthors();
}
