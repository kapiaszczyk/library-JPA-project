package dev.kapiaszczyk.bookstore.library.credit;

import jakarta.persistence.*;

@Entity
@Table(name = "credit")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_id")
    private String creditId;

    // Relation with book
    @Column(name = "book_id")
    private String bookId;

    // Relation with author
    @Column(name = "author_id")
    private String authorId;

    public Credit() {
    }

    public Credit(String bookId, String authorId) {
        this.bookId = bookId;
        this.authorId = authorId;
    }

    public String getCreditId() {
        return creditId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }


}
