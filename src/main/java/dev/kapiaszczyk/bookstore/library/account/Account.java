package dev.kapiaszczyk.bookstore.library.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.kapiaszczyk.bookstore.library.libraryUser.LibraryUser;
import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", unique = true, nullable = false)
    private Long accountId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "library_user_id", referencedColumnName = "library_user_id")
    @JsonIgnore
    private LibraryUser libraryUser;

    public Account() {
    }

    public Account(LibraryUser libraryUser) {
        this.libraryUser = libraryUser;
    }

    public String getAccountId() {
        return accountId;
    }

    public LibraryUser getLibraryUser() {
        return libraryUser;
    }

    public void setLibraryUser(LibraryUser libraryUser) {
        this.libraryUser = libraryUser;
    }
}
