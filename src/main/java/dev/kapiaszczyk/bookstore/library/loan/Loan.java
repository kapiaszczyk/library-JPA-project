package dev.kapiaszczyk.bookstore.library.loan;

import dev.kapiaszczyk.bookstore.library.account.Account;
import dev.kapiaszczyk.bookstore.library.book.Book;
import jakarta.persistence.*;

@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long loanId;

    // Association with Account
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // Association with Book
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Book book;

    @Column(name = "loan_status")
    private String loanStatus;

}
