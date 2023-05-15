package dev.kapiaszczyk.bookstore.library.ISBNTest;

import dev.kapiaszczyk.bookstore.library.book.Book;
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
public class ISBNTest {

    @Autowired
    private ISBNRepository isbnRepository;

    @Test
    public void addISBN() {
        ISBN isbn = new ISBN();
        isbn.setIsbnNumber(123456789L);

        Book book = new Book();
        book.setBookTitle("Book Title");
        book.setIsbn(isbn);
        isbn.setBook(book);

        isbnRepository.save(isbn);

        ISBN savedISBN = isbnRepository.findById(isbn.getIsbnId()).get();

        assertNotNull(savedISBN.getIsbnId());
        assertEquals(123456789L, savedISBN.getIsbnNumber());
    }

    @Test
    public void updateISBN() {
        ISBN isbn = new ISBN();
        isbn.setIsbnNumber(123456789L);

        Book book = new Book();
        book.setBookTitle("Book Title");
        book.setIsbn(isbn);
        isbn.setBook(book);

        isbnRepository.save(isbn);

        ISBN savedISBN = isbnRepository.findById(isbn.getIsbnId()).get();

        savedISBN.setIsbnNumber(987654321L);

        isbnRepository.save(savedISBN);

        ISBN updatedISBN = isbnRepository.findById(savedISBN.getIsbnId()).get();
        assertEquals(987654321L, updatedISBN.getIsbnNumber());
    }

    @Test
    public void deleteISBN() {
        ISBN isbn = new ISBN();
        isbn.setIsbnNumber(123456789L);

        Book book = new Book();
        book.setBookTitle("Book Title");
        book.setIsbn(isbn);
        isbn.setBook(book);

        isbnRepository.save(isbn);

        ISBN savedISBN = isbnRepository.findById(isbn.getIsbnId()).get();

        book.removeIsbn();
        isbnRepository.delete(savedISBN);

        assertEquals(0, isbnRepository.count());
    }
}
