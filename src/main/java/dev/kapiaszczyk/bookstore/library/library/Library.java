package dev.kapiaszczyk.bookstore.library.library;

import dev.kapiaszczyk.bookstore.library.inventory.Inventory;
import jakarta.persistence.*;

@Entity
@Table(name = "library")
public class Library {

    @Id
    @Column(name = "library_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "library_name")
    private String libraryName;

    @OneToOne(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true)
    Inventory inventory;

    public Library() {
    }

    public Library(String libraryName, Inventory inventory) {
        this.libraryName = libraryName;
        this.inventory = inventory;
    }

    public Long getId() {
        return id;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }


    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
