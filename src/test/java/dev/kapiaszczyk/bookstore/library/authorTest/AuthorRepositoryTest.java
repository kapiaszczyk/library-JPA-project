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

    @Test
    public void shouldFindAuthorByFirstName() {
        List<Author> authors = authorRepository.findByFirstName(author.getFirstName()).get();
        assertThat(authors.size(), equalTo(1));
    }

    @Test
    public void shouldFindAuthorByLastName() {
        List<Author> authors = authorRepository.findByLastName(author.getLastName()).get();
        assertThat(authors.size(), equalTo(1));
    }

    @Test
    public void shouldFindAuthorByAFirstNameAndLastName() {
        List<Author> authors = authorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName()).get();
        assertThat(authors.size(), equalTo(1));
    }

    // TODO - refactor (shorten) this test
    @Test
    public void shouldFindAuthorByCreditsBookBookTitle() {
        Book book = new Book();
        book.setBookTitle("Hamlet");

        ISBN isbn = new ISBN();
        isbn.setIsbnNumber("123456789L");
        isbn.setBook(book);
        book.setIsbn(isbn);

        Credit credit = new Credit();
        book.addCredit(credit);
        credit.setBook(book);

        credit.setAuthor(author);
        author.addCredit(credit);

        isbnRepository.save(isbn);
        authorRepository.save(author);
        creditRepository.save(credit);
        bookRepository.save(book);

        List<Author> authors = authorRepository.findByCreditsBookBookTitle(book.getBookTitle()).get();
        assertThat(authors.size(), equalTo(1));
    }


}
