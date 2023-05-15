package dev.kapiaszczyk.bookstore.library.loanTest;

import dev.kapiaszczyk.bookstore.library.account.Account;
import dev.kapiaszczyk.bookstore.library.book.Book;
import dev.kapiaszczyk.bookstore.library.libraryUser.LibraryUser;
import dev.kapiaszczyk.bookstore.library.loan.Loan;
import dev.kapiaszczyk.bookstore.library.loan.LoanRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional

public class LoanTest {

    @Autowired
    private LoanRepository loanRepository;

    @Test
    public void addLoan() {
        LibraryUser libraryUser = new LibraryUser();
        libraryUser.setLibraryUserFirstName("John");
        libraryUser.setLibraryUserSurname("Doe");

        Account account = new Account();
        account.setAccountNumber("123456789");
        account.setLibraryUser(libraryUser);
        libraryUser.setAccount(account);

        Book book = new Book();
        book.setBookTitle("Book Title");

        Loan loan = new Loan();
        loan.setAccount(account);
        account.addLoan(loan);
        loan.setBook(book);
        book.setLoan(loan);

        loanRepository.save(loan);

        Loan savedLoan = loanRepository.findById(loan.getLoanId()).get();
        assertEquals("Book Title", savedLoan.getBook().getBookTitle());
        assertEquals("John", savedLoan.getAccount().getLibraryUser().getLibraryUserFirstName());
        assertEquals("Doe", savedLoan.getAccount().getLibraryUser().getLibraryUserSurname());
        assertEquals("123456789", savedLoan.getAccount().getAccountNumber());
    }

    @Test
    public void updateLoan() {
        LibraryUser libraryUser = new LibraryUser();
        libraryUser.setLibraryUserFirstName("John");
        libraryUser.setLibraryUserSurname("Doe");

        Account account = new Account();
        account.setAccountNumber("123456789");
        account.setLibraryUser(libraryUser);
        libraryUser.setAccount(account);

        Book book = new Book();
        book.setBookTitle("Book Title");

        Loan loan = new Loan();
        loan.setAccount(account);
        account.addLoan(loan);
        loan.setBook(book);
        book.setLoan(loan);

        loanRepository.save(loan);

        Loan savedLoan = loanRepository.findById(loan.getLoanId()).get();

        savedLoan.getBook().setBookTitle("New Book Title");
        savedLoan.getAccount().setAccountNumber("987654321");

        loanRepository.save(savedLoan);

        Loan updatedLoan = loanRepository.findById(loan.getLoanId()).get();
        assertEquals("New Book Title", updatedLoan.getBook().getBookTitle());
        assertEquals("987654321", updatedLoan.getAccount().getAccountNumber());
    }

    @Test
    public void removeLoan() {
        LibraryUser libraryUser = new LibraryUser();
        libraryUser.setLibraryUserFirstName("John");
        libraryUser.setLibraryUserSurname("Doe");

        Account account = new Account();
        account.setAccountNumber("123456789");
        account.setLibraryUser(libraryUser);
        libraryUser.setAccount(account);

        Book book = new Book();
        book.setBookTitle("Book Title");

        Loan loan = new Loan();
        loan.setAccount(account);
        account.addLoan(loan);
        loan.setBook(book);
        book.setLoan(loan);

        loanRepository.save(loan);

        Loan savedLoan = loanRepository.findById(loan.getLoanId()).get();

        book.removeLoan();
        loanRepository.delete(savedLoan);

        assertEquals(0, loanRepository.count());
    }
}
