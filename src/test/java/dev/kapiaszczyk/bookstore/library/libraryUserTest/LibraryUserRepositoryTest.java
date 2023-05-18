package dev.kapiaszczyk.bookstore.library.libraryUserTest;

import dev.kapiaszczyk.bookstore.library.account.Account;
import dev.kapiaszczyk.bookstore.library.account.AccountRepository;
import dev.kapiaszczyk.bookstore.library.libraryUser.LibraryUser;
import dev.kapiaszczyk.bookstore.library.libraryUser.LibraryUserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class LibraryUserRepositoryTest {

    @Autowired
    private LibraryUserRepository libraryUserRepository;

    @Autowired
    private AccountRepository accountRepository;

    private LibraryUser libraryUser;
    private Account account;

    @BeforeEach
    public void setUp() {
        libraryUser = new LibraryUser();
        libraryUser.setLibraryUserFirstName("John");
        libraryUser.setLibraryUserSurname("Smith");

        account = new Account();
        account.setAccountNumber("1234567890");

        libraryUser.setAccount(account);
        account.setLibraryUser(libraryUser);

        accountRepository.save(account);
        libraryUserRepository.save(libraryUser);
    }

    @Test
    public void shouldFindByLibraryUserSurname() {

        List<LibraryUser> foundLibraryUsers = libraryUserRepository.findByLibraryUserSurname(libraryUser.getLibraryUserSurname());

        assertNotNull(foundLibraryUsers.get(0).getLibraryUserId());
        assertThat(foundLibraryUsers.get(0).getLibraryUserSurname(), equalTo(libraryUser.getLibraryUserSurname()));
    }


}
