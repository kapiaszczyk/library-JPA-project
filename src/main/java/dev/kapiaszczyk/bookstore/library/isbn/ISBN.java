package dev.kapiaszczyk.bookstore.library.isbn;

import jakarta.persistence.*;

@Entity
@Table(name = "isbn")
public class ISBN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "isbn_id")
    private Long isbnId;

    @Column(name = "isbn_number")
    private String isbnNumber;

    public ISBN() {
    }

    public ISBN(Long isbnNumber) {
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

}
