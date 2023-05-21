package dev.kapiaszczyk.bookstore.library.book.dto;


import dev.kapiaszczyk.bookstore.library.credit.dto.CreditDTO;
import dev.kapiaszczyk.bookstore.library.loan.dto.LoanDTO;

import java.util.List;

public class BookDTO {
    private Long id;
    private String title;
    private Long categoryId;
    private Long isbnId;
    private Long inventoryId;
    private List<CreditDTO> credits;
    private LoanDTO loan;

    public BookDTO() {
    }

    public BookDTO(Long id, String title, Long categoryId, Long isbnId, Long inventoryId, List<CreditDTO> credits, LoanDTO loan) {
        this.id = id;
        this.title = title;
        this.categoryId = categoryId;
        this.isbnId = isbnId;
        this.inventoryId = inventoryId;
        this.credits = credits;
        this.loan = loan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getIsbnId() {
        return isbnId;
    }

    public void setIsbnId(Long isbnId) {
        this.isbnId = isbnId;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public List<CreditDTO> getCredits() {
        return credits;
    }

    public void setCredits(List<CreditDTO> credits) {
        this.credits = credits;
    }


    public LoanDTO getLoan() {
        return loan;
    }

    public void setLoan(LoanDTO loan) {
        this.loan = loan;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", categoryId=" + categoryId +
                ", isbnId=" + isbnId +
                ", inventoryId=" + inventoryId +
                ", credits=" + credits +
                ", loan=" + loan +
                '}';
    }

}
