package dev.kapiaszczyk.bookstore.library.bookTest;

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
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private ISBNRepository isbnRepository;

    private Book book;
    private ISBN isbn;
    private Credit credit;
    private Author author;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setBookTitle("Hamlet");

        isbn = new ISBN();
        isbn.setIsbnNumber("123456789L");
        isbn.setBook(book);
        book.setIsbn(isbn);

        credit = new Credit();
        credit.setBook(book);

        author = new Author();
        author.setAuthorName("William");
        author.setAuthorSurname("Shakespeare");

        credit.setAuthor(author);
        author.addCredit(credit);

        isbnRepository.save(isbn);
        authorRepository.save(author);
        creditRepository.save(credit);

        book.addCredit(credit);

        bookRepository.save(book);
    }

    @Test
    public void shouldFindBookByTitle() {
        String title = "Hamlet";
        Optional<List<Book>> optionalBooks = bookRepository.findByBookTitle(title);

        assertTrue(optionalBooks.isPresent());

        List<Book> books = optionalBooks.get();

        assertThat(books.size(), equalTo(1));
        assertThat(books.get(0).getBookTitle(), equalTo(book.getBookTitle()));
    }

    @Test
    public void shouldFindByBookTitleContaining() {
        String title = "Ham";
        Optional<List<Book>> optionalBooks = bookRepository.findByBookTitleContaining(title);

        assertTrue(optionalBooks.isPresent());

        List<Book> books = optionalBooks.get();

        assertThat(books.size(), equalTo(1));
        assertThat(books.get(0).getBookTitle(), equalTo(book.getBookTitle()));
    }

    @Test
    public void shouldFindBookByIsbnNumber() {
        String isbnNumber = "123456789L";
        Optional<List<Book>> optionalBooks = bookRepository.findByIsbnIsbnNumber(isbnNumber);

        assertTrue(optionalBooks.isPresent());

        List<Book> books = optionalBooks.get();

        assertThat(books.size(), equalTo(1));
        assertThat(books.get(0).getBookTitle(), equalTo(book.getBookTitle()));
    }

    @Test
    public void shouldFindBookByAuthorName() {
        String authorName = "William";
        Optional<List<Book>> foundBooks = bookRepository.findByCreditsAuthorAuthorName(authorName);

        assertTrue(foundBooks.isPresent());

        List<Book> books = foundBooks.get();

        assertThat(books.size(), equalTo(1));
        assertThat(books.get(0).getBookTitle(), equalTo(book.getBookTitle()));

    }

    @Test
    public void shouldFindBookByAuthorSurname() {
        String authorSurname = "Shakespeare";
        Optional<List<Book>> foundBooks = bookRepository.findByCreditsAuthorAuthorSurname(authorSurname);

        assertTrue(foundBooks.isPresent());

        List<Book> books = foundBooks.get();

        assertThat(books.size(), equalTo(1));
        assertThat(books.get(0).getBookTitle(), equalTo(book.getBookTitle()));
    }

    @Test
    public void shouldFindBookByAuthorNameAndSurname() {
        String authorName = "William";
        String authorSurname = "Shakespeare";
        Optional<List<Book>> foundBooks = bookRepository.findByCreditsAuthorAuthorNameAndCreditsAuthorAuthorSurname(authorName, authorSurname);

        assertTrue(foundBooks.isPresent());

        List<Book> books = foundBooks.get();

        assertThat(books.size(), equalTo(1));
        assertThat(books.get(0).getBookTitle(), equalTo(book.getBookTitle()));
    }


}
