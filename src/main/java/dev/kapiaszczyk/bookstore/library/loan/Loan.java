package dev.kapiaszczyk.bookstore.library.loan;

import jakarta.persistence.*;

@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long loanId;

    // Relation with Account
    @Column(name = "account_id")
    private String accountId;

    // Relation with Book
    @Column(name = "book_id")
    private String bookId;

    @Column(name = "loan_status")
    private String loanStatus;

}
