package dev.kapiaszczyk.bookstore.library.creditTest;

import dev.kapiaszczyk.bookstore.library.author.Author;
import dev.kapiaszczyk.bookstore.library.credit.Credit;
import dev.kapiaszczyk.bookstore.library.credit.CreditRepository;
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
public class CreditTest {

    @Autowired
    CreditRepository creditRepository;

    @Test
    public void addCredit() {
        Author author = new Author();
        author.setAuthorName("John");
        author.setAuthorSurname("Doe");

        Credit credit = new Credit();
        credit.setAuthor(author);
        author.addCredit(credit);

        creditRepository.save(credit);

        Credit savedCredit = creditRepository.save(credit);

        assertNotNull(savedCredit.getCreditId());
        assertEquals("John", savedCredit.getAuthor().getAuthorName());
        assertEquals("Doe", savedCredit.getAuthor().getAuthorSurname());

    }

    @Test
    public void updateCredit() {
        Author author = new Author();
        author.setAuthorName("John");
        author.setAuthorSurname("Doe");

        Credit credit = new Credit();
        credit.setAuthor(author);
        author.addCredit(credit);

        creditRepository.save(credit);

        Credit savedCredit = creditRepository.save(credit);

        savedCredit.getAuthor().setAuthorName("Jane");
        savedCredit.getAuthor().setAuthorSurname("Smith");
        creditRepository.save(savedCredit);

        Credit updatedCredit = creditRepository.findById(savedCredit.getCreditId()).get();

        assertEquals("Jane", updatedCredit.getAuthor().getAuthorName());
        assertEquals("Smith", updatedCredit.getAuthor().getAuthorSurname());
    }

    @Test
    public void deleteCredit() {
        Author author = new Author();
        author.setAuthorName("John");
        author.setAuthorSurname("Doe");

        Credit credit = new Credit();
        credit.setAuthor(author);
        author.addCredit(credit);

        creditRepository.save(credit);

        Credit savedCredit = creditRepository.save(credit);

        author.removeCredit(credit);
        creditRepository.delete(savedCredit);

        assertEquals(0, creditRepository.count());

    }
}
