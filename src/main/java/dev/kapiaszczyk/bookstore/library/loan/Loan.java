package dev.kapiaszczyk.bookstore.library.loan;

import dev.kapiaszczyk.bookstore.library.account.Account;
import dev.kapiaszczyk.bookstore.library.book.Book;
import jakarta.persistence.*;

@Entity
@Table(name = "loan")
public class Loan {

    public enum Status {
        PENDING,
        ACTIVE,
        RETURNED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;


    @Enumerated(EnumType.STRING)
    @Column(name = "loan_status")
    private Status status;

    public Loan() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long loanId) {
        this.id = loanId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
