package dev.kapiaszczyk.bookstore.library.authorTest;

import dev.kapiaszczyk.bookstore.library.author.Author;
import dev.kapiaszczyk.bookstore.library.author.AuthorRepository;
import dev.kapiaszczyk.bookstore.library.book.Book;
import dev.kapiaszczyk.bookstore.library.book.BookRepository;
import dev.kapiaszczyk.bookstore.library.credit.Credit;
import dev.kapiaszczyk.bookstore.library.credit.CreditRepository;
import dev.kapiaszczyk.bookstore.library.isbn.ISBN;
import dev.kapiaszczyk.bookstore.library.isbn.ISBNRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ISBNRepository isbnRepository;

    private Author author;

    @BeforeEach
    public void setUp() {
        author = new Author();
        author.setFirstName("William");
        author.setLastName("Shakespeare");
        authorRepository.save(author);
    }

}
