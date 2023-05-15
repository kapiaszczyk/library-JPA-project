package dev.kapiaszczyk.bookstore.library.libraryUserTest;

import dev.kapiaszczyk.bookstore.library.account.Account;
import dev.kapiaszczyk.bookstore.library.libraryUser.LibraryUser;
import dev.kapiaszczyk.bookstore.library.libraryUser.LibraryUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LibraryUserTest {

    @Autowired
    private LibraryUserRepository libraryUserRepository;

    @Test
    public void saveLibraryUser() {
        LibraryUser libraryUser = new LibraryUser();
        libraryUser.setLibraryUserFirstName("John");
        libraryUser.setLibraryUserSurname("Doe");

        Account account = new Account();
        account.setAccountNumber("123456789");

        libraryUser.setAccount(account);
        account.setLibraryUser(libraryUser);
        libraryUserRepository.save(libraryUser);

        LibraryUser savedLibraryUser = libraryUserRepository.findById(libraryUser.getLibraryUserId()).get();

        assertNotNull(savedLibraryUser.getLibraryUserId());
        assertEquals("John", savedLibraryUser.getLibraryUserFirstName());
        assertEquals("Doe", savedLibraryUser.getLibraryUserSurname());
        assertEquals("123456789", savedLibraryUser.getAccount().getAccountNumber());
        assertNotNull(savedLibraryUser.getAccount().getAccountId());
    }

    @Test
    public void updateLibraryUser() {
        LibraryUser libraryUser = new LibraryUser();
        libraryUser.setLibraryUserFirstName("John");
        libraryUser.setLibraryUserSurname("Doe");

        Account account = new Account();
        account.setAccountNumber("123456789");
        libraryUser.setAccount(account);
        account.setLibraryUser(libraryUser);

        libraryUserRepository.save(libraryUser);

        LibraryUser savedLibraryUser = libraryUserRepository.findById(libraryUser.getLibraryUserId()).get();

        savedLibraryUser.setLibraryUserFirstName("Jane");
        savedLibraryUser.setLibraryUserSurname("Smith");

        libraryUserRepository.save(savedLibraryUser);

        LibraryUser updatedLibraryUser = libraryUserRepository.findById(savedLibraryUser.getLibraryUserId()).get();

        assertEquals("Jane", updatedLibraryUser.getLibraryUserFirstName());
        assertEquals("Smith", updatedLibraryUser.getLibraryUserSurname());
    }

}
