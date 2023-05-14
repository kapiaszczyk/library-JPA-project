package dev.kapiaszczyk.bookstore.library.book;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private String bookId;

    @Column(name = "book_title")
    private String bookTitle;

    // Relation with category
    @Column(name = "category_id")
    private String categoryId;

    // Relation with isbn
    @Column(name = "isbn_id")
    private String isbnId;

    // Relation with inventory
    @Column(name = "inventory_id")
    private String inventoryId;

    public Book() {
    }

    public Book(String bookTitle, String categoryId, String isbnId, String inventoryId) {
        this.bookTitle = bookTitle;
        this.categoryId = categoryId;
        this.isbnId = isbnId;
        this.inventoryId = inventoryId;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getIsbnId() {
        return isbnId;
    }

    public void setIsbnId(String isbnId) {
        this.isbnId = isbnId;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }
}
