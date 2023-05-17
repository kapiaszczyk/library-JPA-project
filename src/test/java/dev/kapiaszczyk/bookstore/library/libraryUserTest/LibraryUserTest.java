package dev.kapiaszczyk.bookstore.library.libraryUserTest;

import dev.kapiaszczyk.bookstore.library.account.Account;
import dev.kapiaszczyk.bookstore.library.libraryUser.LibraryUser;
import dev.kapiaszczyk.bookstore.library.libraryUser.LibraryUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class LibraryUserTest {

    @Autowired
    private LibraryUserRepository libraryUserRepository;

    private LibraryUser libraryUser;
    private Account account;

    @BeforeEach
    public void setUp() {
        libraryUser = new LibraryUser();
        libraryUser.setLibraryUserFirstName("John");
        libraryUser.setLibraryUserSurname("Doe");

        account = new Account();
        account.setAccountNumber("123456789");

        libraryUser.setAccount(account);
        account.setLibraryUser(libraryUser);
        libraryUserRepository.save(libraryUser);
    }

    @Test
    public void libraryUserCanBeAdded() {
        LibraryUser savedLibraryUser = libraryUserRepository.findById(libraryUser.getLibraryUserId()).get();

        assertNotNull(savedLibraryUser.getLibraryUserId());
        assertThat(savedLibraryUser.getLibraryUserFirstName(), equalTo(libraryUser.getLibraryUserFirstName()));
        assertThat(savedLibraryUser.getLibraryUserSurname(), equalTo(libraryUser.getLibraryUserSurname()));
        assertThat(savedLibraryUser.getAccount().getAccountNumber(), equalTo(libraryUser.getAccount().getAccountNumber()));

    }

    @Test
    public void libraryUserCanBeUpdated() {
        LibraryUser savedLibraryUser = libraryUserRepository.findById(libraryUser.getLibraryUserId()).get();

        savedLibraryUser.setLibraryUserFirstName("Jane");
        savedLibraryUser.setLibraryUserSurname("Smith");

        libraryUserRepository.save(savedLibraryUser);

        LibraryUser updatedLibraryUser = libraryUserRepository.findById(savedLibraryUser.getLibraryUserId()).get();

        assertThat(updatedLibraryUser.getLibraryUserFirstName(), equalTo(savedLibraryUser.getLibraryUserFirstName()));
        assertThat(updatedLibraryUser.getLibraryUserSurname(), equalTo(savedLibraryUser.getLibraryUserSurname()));
    }

    @Test
    public void libraryUserCanBeDeleted() {
        LibraryUser savedLibraryUser = libraryUserRepository.findById(libraryUser.getLibraryUserId()).get();
        libraryUserRepository.delete(savedLibraryUser);
        assertEquals(0, libraryUserRepository.count());
    }

}
