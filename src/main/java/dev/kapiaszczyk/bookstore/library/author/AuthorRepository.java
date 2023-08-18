package dev.kapiaszczyk.bookstore.library.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAll();
    List<Author> findByFirstNameOrLastName(String firstName, String lastName);
}
