package dev.kapiaszczyk.bookstore.library.libraryUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryUserService {

    @Autowired
    private LibraryUserRepository libraryUserRepository;

    public List<LibraryUser> getAllLibraryUsers() {
        return libraryUserRepository.findAll();
    }
}
