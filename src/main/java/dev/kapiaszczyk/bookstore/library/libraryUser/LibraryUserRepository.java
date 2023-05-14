package dev.kapiaszczyk.bookstore.library.libraryUser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryUserRepository extends CrudRepository<LibraryUser, String> {
    List<LibraryUser> findAll();
}
