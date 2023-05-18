package dev.kapiaszczyk.bookstore.library.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<List<Book>> findByTitle(String title);
    Optional<List<Book>> findByTitleContaining(String title);
    Optional<List<Book>> findByIsbnIsbnNumber(String isbnNumber);
    Optional<List<Book>> findByCreditsAuthorFirstName(String firstName);
    Optional<List<Book>> findByCreditsAuthorLastName(String lastName);
    Optional<List<Book>> findByCreditsAuthorFirstNameAndCreditsAuthorLastName(String firstName, String lastName);
    List<Book> findAllByCategoryCategoryNameLike(String categoryName);

}
