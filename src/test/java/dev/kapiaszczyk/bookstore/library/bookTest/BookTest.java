package dev.kapiaszczyk.bookstore.library.bookTest;

import dev.kapiaszczyk.bookstore.library.author.Author;
import dev.kapiaszczyk.bookstore.library.author.AuthorRepository;
import dev.kapiaszczyk.bookstore.library.book.Book;
import dev.kapiaszczyk.bookstore.library.book.BookRepository;
import dev.kapiaszczyk.bookstore.library.credit.Credit;
import dev.kapiaszczyk.bookstore.library.credit.CreditRepository;
import dev.kapiaszczyk.bookstore.library.isbn.ISBN;
import dev.kapiaszczyk.bookstore.library.isbn.ISBNRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
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

    @Test
    public void saveBook() {
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

        Book savedBook = bookRepository.findById(book.getBookId()).get();

        assertNotNull(book);
        assertEquals("Test title", savedBook.getBookTitle());
        assertEquals("123456789L", savedBook.getIsbn().getIsbnNumber());
        assertEquals("Test author", savedBook.getCredits().get(0).getAuthor().getAuthorName());
        assertEquals("Test surname", savedBook.getCredits().get(0).getAuthor().getAuthorSurname());
    }

    @Test
    public void updateBook() {
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

        Book savedBook = bookRepository.findById(book.getBookId()).get();

        savedBook.setBookTitle("Updated title");
        bookRepository.save(savedBook);

        Book updatedBook = bookRepository.findById(book.getBookId()).get();
        assertEquals("Updated title", updatedBook.getBookTitle());
    }

    @Test
    public void deleteBook() {
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

        Book savedBook = bookRepository.findById(book.getBookId()).get();
        isbnRepository.delete(savedBook.getIsbn());
        authorRepository.delete(savedBook.getCredits().get(0).getAuthor());
        creditRepository.delete(savedBook.getCredits().get(0));
        bookRepository.delete(savedBook);

        assertEquals(0, bookRepository.count());


    }

}
