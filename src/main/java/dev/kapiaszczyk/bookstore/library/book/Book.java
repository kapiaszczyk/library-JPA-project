package dev.kapiaszczyk.bookstore.library.book;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.kapiaszczyk.bookstore.library.category.Category;
import dev.kapiaszczyk.bookstore.library.credit.Credit;
import dev.kapiaszczyk.bookstore.library.inventory.Inventory;
import dev.kapiaszczyk.bookstore.library.isbn.ISBN;
import dev.kapiaszczyk.bookstore.library.loan.Loan;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "book_title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonManagedReference
    private Category category;

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private ISBN isbn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Credit> credits = new ArrayList<>();

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Loan loan;


    public Book() {
    }

    public Book(String title, Category category, ISBN isbn, Inventory inventory, List<Credit> credits, Loan loan) {
        this.title = title;
        this.category = category;
        this.isbn = isbn;
        this.inventory = inventory;
        this.credits = credits;
        this.loan = loan;
    }


    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        if (category == null) {
            if (this.category != null) {
                this.category.addBook(null);
            }
        } else {
            category.addBook(this);
        }
        this.category = category;
    }

    public ISBN getIsbn() {
        return isbn;
    }

    public void setIsbn(ISBN isbn) {
        if (isbn == null) {
            if (this.isbn != null) {
                this.isbn.setBook(null);
            }
        } else {
            isbn.setBook(this);
        }
        this.isbn = isbn;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        if (loan == null) {
            if (this.loan != null) {
                this.loan.setBook(null);
            }
        } else {
            loan.setBook(this);
        }
        this.loan = loan;
    }

    public void addCredit(Credit credit) {
        credits.add(credit);
        credit.setBook(this);
    }

    public void removeCredit(Credit credit) {
        credits.remove(credit);
        credit.setBook(null);
    }

    public void removeIsbn() {
        if (isbn != null) {
            isbn.setBook(null);
        }
        this.isbn = null;
    }

    public void removeLoan() {
        if (loan != null) {
            loan.setBook(null);
        }
        this.loan = null;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title +
                ", category=" + category +
                ", isbn=" + isbn +
                ", inventory=" + inventory +
                ", credits=" + credits +
                ", loan=" + loan +
                '}';
    }
}
