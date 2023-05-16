package dev.kapiaszczyk.bookstore.library.address;

import dev.kapiaszczyk.bookstore.library.city.City;
import dev.kapiaszczyk.bookstore.library.library.Library;
import dev.kapiaszczyk.bookstore.library.libraryUser.LibraryUser;
import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(name = "street")
    private String street;

    // Association with City
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToOne
    @JoinColumn(name = "library_user_id")
    private LibraryUser libraryUser;

    public Address() {
    }

    public Address(String street, City city, LibraryUser libraryUser)  {
        this.street = street;
        this.city = city;
        this.libraryUser = libraryUser;
    }

    public Long getAddressId() {
        return addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public LibraryUser getLibraryUser() {
        return libraryUser;
    }

    public void setLibraryUser(LibraryUser libraryUser) {
        this.libraryUser = libraryUser;
    }

}
