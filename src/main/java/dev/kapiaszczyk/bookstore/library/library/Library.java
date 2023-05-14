package dev.kapiaszczyk.bookstore.library.library;

import jakarta.persistence.*;

@Entity
@Table(name = "library")
public class Library {

    @Id
    @Column(name = "library_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String libraryId;

    @Column(name = "library_name")
    private String libraryName;

    @Column(name = "library_address")
    private String libraryAddress;

    @Column(name = "address_id")
    String address_id;

    @Column(name = "inventory_id")
    String inventory_id;

    public Library() {
    }

    public Library(String libraryName, String libraryAddress, String address_id, String inventory_id) {
        this.libraryName = libraryName;
        this.libraryAddress = libraryAddress;
        this.address_id = address_id;
        this.inventory_id = inventory_id;
    }

    public String getLibraryId() {
        return libraryId;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getLibraryAddress() {
        return libraryAddress;
    }

    public void setLibraryAddress(String libraryAddress) {
        this.libraryAddress = libraryAddress;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(String inventory_id) {
        this.inventory_id = inventory_id;
    }
}
