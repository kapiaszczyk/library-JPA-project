package dev.kapiaszczyk.bookstore.library.ISBNTest;

import dev.kapiaszczyk.bookstore.library.book.Book;
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
public class ISBNTest {

    @Autowired
    private ISBNRepository isbnRepository;

    private ISBN isbn;
    private Book book;

    @BeforeEach
    public void setUp() {
        isbn = new ISBN();
        isbn.setIsbnNumber("123456789L");

        book = new Book();
        book.setBookTitle("Book Title");

        isbn.setBook(book);
        book.setIsbn(isbn);

        isbnRepository.save(isbn);

    }

    @Test
    public void isbnCanBeAdded() {
        ISBN savedISBN = isbnRepository.findById(isbn.getIsbnId()).get();

        assertNotNull(savedISBN.getIsbnId());
        assertThat(savedISBN.getIsbnNumber(), equalTo(isbn.getIsbnNumber()));
    }

    @Test
    public void isbnCanBeUpdated() {
        ISBN savedISBN = isbnRepository.findById(isbn.getIsbnId()).get();

        savedISBN.setIsbnNumber("987654321L");
        isbnRepository.save(savedISBN);

        ISBN updatedISBN = isbnRepository.findById(savedISBN.getIsbnId()).get();

        assertThat(updatedISBN.getIsbnNumber(), equalTo(savedISBN.getIsbnNumber()));
    }

    @Test
    public void isbnCanBeDeleted() {
        ISBN savedISBN = isbnRepository.findById(isbn.getIsbnId()).get();

        book.removeIsbn();
        isbnRepository.delete(savedISBN);

        assertEquals(0, isbnRepository.count());
    }
}
