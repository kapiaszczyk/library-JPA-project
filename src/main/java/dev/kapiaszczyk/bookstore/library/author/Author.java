package dev.kapiaszczyk.bookstore.library.author;

import dev.kapiaszczyk.bookstore.library.credit.Credit;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id;

    @Column(name = "author_name")
    private String firstName;

    @Column(name = "author_surname")
    private String authorSurname;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Credit> credits = new ArrayList<>();

    public Author() {
    }

    public Author(String firstName, String authorSurname) {
        this.firstName = firstName;
        this.authorSurname = authorSurname;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public void addCredit(Credit credit) {
        credits.add(credit);
        credit.setAuthor(this);
    }

    public void removeCredit(Credit credit) {
        credits.remove(credit);
        credit.setAuthor(null);
    }
}