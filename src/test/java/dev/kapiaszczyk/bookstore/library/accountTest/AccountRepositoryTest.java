package dev.kapiaszczyk.bookstore.library.accountTest;

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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private LibraryUserRepository libraryUserRepository;

    private Account account;
    private LibraryUser libraryUser;

    @BeforeEach
    public void setUp() {
        account = new Account();
        account.setAccountNumber("123456789");

        libraryUser = new LibraryUser();
        libraryUser.setLibraryUserFirstName("John");
        libraryUser.setLibraryUserSurname("Doe");

        account.setLibraryUser(libraryUser);
        libraryUser.setAccount(account);

        libraryUserRepository.save(libraryUser);
        accountRepository.save(account);

    }

    @Test
    public void shouldFindAccountByLibraryUserLibraryUserId() {
        // Retrieve account
        Account savedAccount = accountRepository.findByLibraryUserLibraryUserId(libraryUser.getLibraryUserId());

        // Check if account can be found by libraryUserId
        assertNotNull(savedAccount.getId());
        assertThat(savedAccount.getAccountNumber(), equalTo(account.getAccountNumber()));
        assertThat(savedAccount.getLibraryUser().getLibraryUserId(), equalTo(libraryUser.getLibraryUserId()));
    }

}


