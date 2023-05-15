package dev.kapiaszczyk.bookstore.library.library;

import dev.kapiaszczyk.bookstore.library.address.Address;
import dev.kapiaszczyk.bookstore.library.inventory.Inventory;
import jakarta.persistence.*;

@Entity
@Table(name = "library")
public class Library {

    @Id
    @Column(name = "library_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long libraryId;

    @Column(name = "library_name")
    private String libraryName;

    @OneToOne(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true)
    Address address;

    @OneToOne(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true)
    Inventory inventory;

    public Library() {
    }

    public Library(String libraryName, Address address, Inventory inventory) {
        this.libraryName = libraryName;
        this.address = address;
        this.inventory = inventory;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
