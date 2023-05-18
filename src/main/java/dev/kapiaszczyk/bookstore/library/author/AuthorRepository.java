package dev.kapiaszczyk.bookstore.library.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<List<Author>> findByFirstName(String firstName);
    Optional<List<Author>> findByLastName(String lastName);
    Optional<List<Author>> findByFirstNameAndLastName(String firstName, String lastName);
    Optional<List<Author>> findByCreditsBookTitle(String title);
}
