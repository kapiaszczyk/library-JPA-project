package dev.kapiaszczyk.bookstore.library.bookTest;

import dev.kapiaszczyk.bookstore.library.book.Book;
import dev.kapiaszczyk.bookstore.library.book.BookDTO;
import dev.kapiaszczyk.bookstore.library.book.BookMapper;
import dev.kapiaszczyk.bookstore.library.category.Category;
import dev.kapiaszczyk.bookstore.library.credit.Credit;
import dev.kapiaszczyk.bookstore.library.inventory.Inventory;
import dev.kapiaszczyk.bookstore.library.isbn.ISBN;
import dev.kapiaszczyk.bookstore.library.loan.Loan;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookMapperTest {

    @Test
    public void shouldMapToBookDto() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Sample Book");

        Category category = new Category();
        category.setId(1L);
        book.setCategory(category);

        ISBN isbn = new ISBN();
        isbn.setId(1L);
        book.setIsbn(isbn);

        Inventory inventory = new Inventory();
        inventory.setId(1L);
        book.setInventory(inventory);

        Loan loan = new Loan();
        loan.setId(1L);
        book.setLoan(loan);

        List<Credit> credits = new ArrayList<>();
        Credit credit1 = new Credit();
        credit1.setId(1L);
        credits.add(credit1);

        Credit credit2 = new Credit();
        credit2.setId(2L);
        credits.add(credit2);

        book.setCredits(credits);

        // Perform the mapping
        BookDTO dto = BookMapper.INSTANCE.mapToDTO(book);

        // Verify the mapping
        assertEquals(book.getId(), dto.getId());
        assertEquals(book.getTitle(), dto.getTitle());
        assertEquals(book.getCategory().getId(), dto.getCategoryId());
        assertEquals(book.getIsbn().getId(), dto.getIsbnId());
        assertEquals(book.getInventory().getId(), dto.getInventoryId());
        assertEquals(book.getLoan().getLoanId(), dto.getLoanId());
        assertEquals(book.getCredits().size(), dto.getCredits().size());
    }
}
