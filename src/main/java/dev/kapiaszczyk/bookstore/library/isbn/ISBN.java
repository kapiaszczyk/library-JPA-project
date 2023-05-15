package dev.kapiaszczyk.bookstore.library.isbn;

import dev.kapiaszczyk.bookstore.library.book.Book;
import jakarta.persistence.*;

@Entity
@Table(name = "isbn")
public class ISBN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "isbn_id")
    private Long isbnId;


    // Association with book
    @OneToOne
    @MapsId
    private Book book;

    @Column(name = "isbn_number")
    private String isbnNumber;

    public ISBN() {
    }

    public ISBN(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public Long getIsbnId() {
        return isbnId;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
