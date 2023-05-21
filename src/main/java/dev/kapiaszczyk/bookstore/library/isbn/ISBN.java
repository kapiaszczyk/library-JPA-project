package dev.kapiaszczyk.bookstore.library.isbn;

import dev.kapiaszczyk.bookstore.library.book.Book;
import jakarta.persistence.*;

@Entity
@Table(name = "isbn")
public class ISBN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "isbn_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    @MapsId
    private Book book;

    @Column(name = "isbn_number")
    private String number;

    public ISBN() {
    }

    public ISBN(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
