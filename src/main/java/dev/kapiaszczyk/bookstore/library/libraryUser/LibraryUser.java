package dev.kapiaszczyk.bookstore.library.libraryUser;

import dev.kapiaszczyk.bookstore.library.account.Account;
import jakarta.persistence.*;

@Entity
@Table(name = "library_user")
public class LibraryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "library_user_id")
    private Long libraryUserId;

    @Column(name = "library_user_name", unique = false, nullable = false, length = 100)
    private String libraryUserFirstName;

    @Column(name = "library_user_surname", unique = false, nullable = false, length = 100)
    private String libraryUserSurname;

    @OneToOne(mappedBy = "libraryUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Account account;

    public LibraryUser() {
    }

    public LibraryUser(String libraryUserName, String libraryUserSurname) {
        this.libraryUserFirstName = libraryUserName;
        this.libraryUserSurname = libraryUserSurname;
    }

    public Long getLibraryUserId() {
        return libraryUserId;
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
}
