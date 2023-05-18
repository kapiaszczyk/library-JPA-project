package dev.kapiaszczyk.bookstore.library.libraryUser;

import dev.kapiaszczyk.bookstore.library.account.Account;
import dev.kapiaszczyk.bookstore.library.address.Address;
import jakarta.persistence.*;

@Entity
@Table(name = "library_user")
public class LibraryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "library_user_id")
    private Long id;

    @Column(name = "library_user_name", unique = false, nullable = false, length = 100)
    private String libraryUserFirstName;

    @Column(name = "library_user_surname", unique = false, nullable = false, length = 100)
    private String libraryUserSurname;

    @OneToOne(mappedBy = "libraryUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Account account;

    @OneToOne(mappedBy = "libraryUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Address address;

    public LibraryUser() {
    }

    public LibraryUser(String libraryUserName, String libraryUserSurname) {
        this.libraryUserFirstName = libraryUserName;
        this.libraryUserSurname = libraryUserSurname;
    }

    public Long getId() {
        return id;
    }

    public String getLibraryUserFirstName() {
        return libraryUserFirstName;
    }

    public void setLibraryUserFirstName(String libraryUserFirstName) {
        this.libraryUserFirstName = libraryUserFirstName;
    }

    public String getLibraryUserSurname() {
        return libraryUserSurname;
    }

    public void setLibraryUserSurname(String libraryUserSurname) {
        this.libraryUserSurname = libraryUserSurname;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void removeAccount() {
        if (account != null) {
            account.setLibraryUser(null);
            this.account = null;
        }
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void removeAddress() {
        if (address != null) {
            address.setLibraryUser(null);
            this.address = null;
        }
    }

    @Override
    public String toString() {
        return "LibraryUser{" +
                "id=" + id +
                ", libraryUserFirstName='" + libraryUserFirstName + '\'' +
                ", libraryUserSurname='" + libraryUserSurname + '\'' +
                ", accountId=" + account.getId() +
                '}';
    }
}
