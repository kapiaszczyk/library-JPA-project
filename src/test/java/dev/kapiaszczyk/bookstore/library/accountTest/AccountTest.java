package dev.kapiaszczyk.bookstore.library.accountTest;

import dev.kapiaszczyk.bookstore.library.account.Account;
import dev.kapiaszczyk.bookstore.library.account.AccountRepository;
import dev.kapiaszczyk.bookstore.library.libraryUser.LibraryUser;
import dev.kapiaszczyk.bookstore.library.libraryUser.LibraryUserRepository;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // Rollback after each test
public class AccountTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private LibraryUserRepository libraryUserRepository;

    private Account account;
    private LibraryUser libraryUser;

    @Test
    public void createAccount() {
        // Create account
        account = new Account();
        libraryUser = new LibraryUser();
        account.setAccountNumber("123456789");

        libraryUser.setLibraryUserFirstName("John");
        libraryUser.setLibraryUserSurname("Doe");

        libraryUser.setAccount(account);
        account.setLibraryUser(libraryUser);

        libraryUserRepository.save(libraryUser);
        accountRepository.save(account);

        Account savedAccount = accountRepository.findById(account.getAccountId()).get();

        assertNotNull(savedAccount.getAccountId());
        assertEquals("123456789", savedAccount.getAccountNumber());
        assertEquals("John", savedAccount.getLibraryUser().getLibraryUserFirstName());
        assertEquals("Doe", savedAccount.getLibraryUser().getLibraryUserSurname());

    }

    @Test
    public void updateAccount() {
        // Create account
        account = new Account();
        libraryUser = new LibraryUser();
        account.setAccountNumber("123456789");
        libraryUser.setLibraryUserFirstName("John");
        libraryUser.setLibraryUserSurname("Doe");
        libraryUser.setAccount(account);
        account.setLibraryUser(libraryUser);

        libraryUserRepository.save(libraryUser);
        accountRepository.save(account);

        // Update account
        Account savedAccount = accountRepository.findById(account.getAccountId()).get();
        savedAccount.setAccountNumber("987654321");

        LibraryUser savedLibraryUser = savedAccount.getLibraryUser();
        savedLibraryUser.setLibraryUserFirstName("Jane");
        savedLibraryUser.setLibraryUserSurname("Smith");

        // Save updated account
        libraryUserRepository.save(savedLibraryUser);
        accountRepository.save(savedAccount);

        // Check if account was updated
        Account updatedAccount = accountRepository.findById(savedAccount.getAccountId()).get();
        assertEquals("987654321", updatedAccount.getAccountNumber());
        assertEquals("Jane", updatedAccount.getLibraryUser().getLibraryUserFirstName());
        assertEquals("Smith", updatedAccount.getLibraryUser().getLibraryUserSurname());

    }

    @Test
    public void deleteAccount() {
        // Create account
        account = new Account();
        libraryUser = new LibraryUser();

        account.setAccountNumber("123456789");
        libraryUser.setLibraryUserFirstName("John");
        libraryUser.setLibraryUserSurname("Doe");
        libraryUser.setAccount(account);
        account.setLibraryUser(libraryUser);

        // Save account
        libraryUserRepository.save(libraryUser);
        accountRepository.save(account);

        // Delete account
        libraryUser.removeAccount();
        accountRepository.deleteById(account.getAccountId());

        assertEquals(0, accountRepository.count());
    }

}
