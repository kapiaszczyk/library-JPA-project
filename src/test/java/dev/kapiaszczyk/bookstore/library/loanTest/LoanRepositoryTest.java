package dev.kapiaszczyk.bookstore.library.loanTest;

import dev.kapiaszczyk.bookstore.library.account.Account;
import dev.kapiaszczyk.bookstore.library.account.AccountRepository;
import dev.kapiaszczyk.bookstore.library.book.Book;
import dev.kapiaszczyk.bookstore.library.libraryUser.LibraryUser;
import dev.kapiaszczyk.bookstore.library.libraryUser.LibraryUserRepository;
import dev.kapiaszczyk.bookstore.library.loan.Loan;
import dev.kapiaszczyk.bookstore.library.loan.LoanRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class LoanRepositoryTest {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LibraryUserRepository libraryUserRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Loan loan;
    private LibraryUser libraryUser;
    private Account account;
    private Book book;

    @BeforeEach
    public void setUp() {
        libraryUser = new LibraryUser();
        libraryUser.setLibraryUserFirstName("John");
        libraryUser.setLibraryUserSurname("Doe");

        account = new Account();
        account.setAccountNumber("123456789");
        account.setLibraryUser(libraryUser);
        libraryUser.setAccount(account);

        accountRepository.save(account);

        book = new Book();
        book.setBookTitle("Book Title");

        loan = new Loan();
        loan.setLoanStatus("active");
        loan.setAccount(account);
        account.addLoan(loan);
        loan.setBook(book);
        book.setLoan(loan);

        loanRepository.save(loan);
    }

    @Test
    public void shouldFindLoanByAccountAccountNumber() {

        List<Loan> foundLoans = loanRepository.findByAccountAccountNumber(account.getAccountNumber());

        assertNotNull(foundLoans);
        assertThat(foundLoans.size(), equalTo(1));
        assertThat(foundLoans.get(0).getBook().getBookTitle(), equalTo(book.getBookTitle()));
    }

    @Test
    public void shouldFindLoanByAccountNumberAndLoanStatus() {
        List <Loan> foundLoans = loanRepository.findByAccountAccountNumberAndLoanStatus(account.getAccountNumber(), loan.getLoanStatus());

        assertNotNull(foundLoans);
        assertThat(foundLoans.size(), equalTo(1));
        assertThat(foundLoans.get(0).getBook().getBookTitle(), equalTo(book.getBookTitle()));
    }

}
