package dev.kapiaszczyk.bookstore.library.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;

//    // TODO: Add loan
//    @PostMapping("/loans/add")
//    public Loan addLoan(Loan loan) {
//        return loanService.addLoan(loan);
//    }

//    // TODO: Delete loan
//    @DeleteMapping("/loans/delete")
//    public void deleteLoan(@RequestParam("id") Long id) {
//        loanService.deleteLoan(id);
//    }

//    // TODO: Update loan
//    @PutMapping("/loans/update")
//    public Loan updateLoan(Loan loan) {
//        return loanService.updateLoan(loan);
//    }

    // TODO: View all loans with account number
    @GetMapping("/loans/account")
    public Iterable<Loan> viewAllLoansWithAccountNumber(@RequestParam("accountNumber") String accountNumber) {
        return loanService.viewAllLoansWithAccountNumber(accountNumber);
    }

    // TODO: View all loans with status
    @GetMapping("/loans/status")
    public Iterable<Loan> viewAllLoansWithStatus(@RequestParam("status") Loan.Status status) {
        return loanService.viewAllLoansWithStatus(status);
    }

    // TODO: View all loans with account and status
    @GetMapping("/loans/account/status")
    public Iterable<Loan> viewAllLoansWithAccountNumberAndStatus(@RequestParam("accountNumber") String accountNumber, @RequestParam("status") Loan.Status status) {
        return loanService.viewAllLoansWithAccountNumberAndStatus(accountNumber, status);
    }

}
