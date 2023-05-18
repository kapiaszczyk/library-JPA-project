package dev.kapiaszczyk.bookstore.library.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<List<Book>> findByBookTitle(String bookTitle);
    Optional<List<Book>> findByBookTitleContaining(String bookTitle);
    Optional<List<Book>> findByIsbnIsbnNumber(String isbnNumber);
    Optional<List<Book>> findByCreditsAuthorAuthorName(String authorName);
}
