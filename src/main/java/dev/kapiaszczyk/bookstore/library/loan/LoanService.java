package dev.kapiaszczyk.bookstore.library.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

//    // TODO: Add loan
//    public Loan addLoan(Loan loan) {
//        return loanRepository.save(loan);
//    }

//    // TODO: Delete loan
//    public void deleteLoan(Long id) {
//        loanRepository.deleteById(id);
//    }

//    // TODO: Update loan
//    public Loan updateLoan(Loan loan) {
//        return loanRepository.save(loan);
//    }

    // TODO: View all loans with account number
    public List<Loan> viewAllLoansWithAccountNumber(String accountNumber) {
        return loanRepository.findByAccountNumber(accountNumber);
    }

    // TODO: View all loans with status
    public Iterable<Loan> viewAllLoansWithStatus(Loan.Status status) { return loanRepository.findByStatus(status);
    }

    // TODO: View all loans with account number and status
    public List<Loan> viewAllLoansWithAccountNumberAndStatus(String accountNumber, Loan.Status status) {
        return loanRepository.findByAccountNumberAndStatus(accountNumber, status);
    }
}
