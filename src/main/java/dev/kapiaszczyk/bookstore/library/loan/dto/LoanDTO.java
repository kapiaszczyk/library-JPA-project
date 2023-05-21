package dev.kapiaszczyk.bookstore.library.loan.dto;

import dev.kapiaszczyk.bookstore.library.loan.Loan;

public class LoanDTO {
    Long id;
    Long accountId;
    Loan.Status status;

    public LoanDTO() {
    }

    public LoanDTO(Long id, Long accountId, Loan.Status status) {
        this.id = id;
        this.accountId = accountId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Loan.Status getStatus() {
        return status;
    }

    public void setStatus(Loan.Status status) {
        this.status = status;
    }
}
