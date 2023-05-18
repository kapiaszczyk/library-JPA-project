package dev.kapiaszczyk.bookstore.library.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByFirstName(String firstName);
    List<Author> findByLastName(String lastName);
    List<Author> findByFirstNameAndLastName(String firstName, String lastName);
    List<Author> findByCreditsBookTitle(String title);
}
