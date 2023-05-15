package dev.kapiaszczyk.bookstore.library.inventory;

import dev.kapiaszczyk.bookstore.library.book.Book;
import dev.kapiaszczyk.bookstore.library.library.Library;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long inventoryId;

    // Association with book
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Book> book = new ArrayList<>();

    // Association with library
    @OneToOne
    private Library library;

    public Inventory() {
    }

    public Inventory(List<Book> book, Library library) {
        this.book = book;
        this.library = library;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
