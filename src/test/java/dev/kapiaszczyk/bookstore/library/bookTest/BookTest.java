package dev.kapiaszczyk.bookstore.library.bookTest;

import dev.kapiaszczyk.bookstore.library.author.Author;
import dev.kapiaszczyk.bookstore.library.author.AuthorRepository;
import dev.kapiaszczyk.bookstore.library.book.Book;
import dev.kapiaszczyk.bookstore.library.book.BookRepository;
import dev.kapiaszczyk.bookstore.library.credit.Credit;
import dev.kapiaszczyk.bookstore.library.credit.CreditRepository;
import dev.kapiaszczyk.bookstore.library.isbn.ISBN;
import dev.kapiaszczyk.bookstore.library.isbn.ISBNRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class BookTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private ISBNRepository isbnRepository;

    private Book book;

    @BeforeEach
    private void setUp() {
        book = new Book();
        book.setBookTitle("Test title");

        ISBN isbn = new ISBN();
        isbn.setIsbnNumber("123456789L");
        isbn.setBook(book);
        book.setIsbn(isbn);

        Credit credit = new Credit();
        credit.setBook(book);

        Author author = new Author();
        author.setAuthorName("Test author");
        author.setAuthorSurname("Test surname");

        credit.setAuthor(author);
        author.addCredit(credit);

        isbnRepository.save(isbn);
        authorRepository.save(author);
        creditRepository.save(credit);

        book.addCredit(credit);

        bookRepository.save(book);
    }

    @Test
    public void saveBook() {
        Book savedBook = bookRepository.findById(book.getBookId()).get();

        assertNotNull(book);
        assertThat(savedBook.getBookId(), equalTo(book.getBookId()));
        assertThat(savedBook.getBookTitle(), equalTo(book.getBookTitle()));
        assertThat(savedBook.getIsbn().getIsbnNumber(), equalTo(book.getIsbn().getIsbnNumber()));
        assertThat(savedBook.getCredits().get(0).getAuthor().getAuthorName(), equalTo(book.getCredits().get(0).getAuthor().getAuthorName()));
        assertThat(savedBook.getCredits().get(0).getAuthor().getAuthorSurname(), equalTo(book.getCredits().get(0).getAuthor().getAuthorSurname()));
    }

    @Test
    public void updateBook() {
        Book savedBook = bookRepository.findById(book.getBookId()).get();
        savedBook.setBookTitle("Updated title");
        bookRepository.save(savedBook);

        Book updatedBook = bookRepository.findById(book.getBookId()).get();
        assertThat(updatedBook.getBookTitle(), equalTo(savedBook.getBookTitle()));
    }

    @Test
    public void deleteBook() {
        Book savedBook = bookRepository.findById(book.getBookId()).get();
        isbnRepository.delete(savedBook.getIsbn());
        authorRepository.delete(savedBook.getCredits().get(0).getAuthor());
        creditRepository.delete(savedBook.getCredits().get(0));
        bookRepository.delete(savedBook);

        assertEquals(0, bookRepository.count());
    }

}
