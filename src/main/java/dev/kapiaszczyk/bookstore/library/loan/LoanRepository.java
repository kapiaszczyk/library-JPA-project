package dev.kapiaszczyk.bookstore.library.loan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

//    // TODO: Create loan
//    public Loan createLoan(Loan loan);

//    // TODO: Delete loan
//    public void deleteLoan(Long id);

//    // TODO: Update loan
//    public Loan updateLoan(Loan loan);

    // TODO: Get loans by status
    Iterable<Loan> findByStatus(Loan.Status status);

    // TODO: Get loans by account number and status
    List<Loan> findByAccountNumberAndStatus(String accountNumber, Loan.Status status);

    // TODO: Get loans by account number
    List<Loan> findByAccountNumber(String accountNumber);
}
