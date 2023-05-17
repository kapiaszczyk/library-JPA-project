package dev.kapiaszczyk.bookstore.library.creditTest;

import dev.kapiaszczyk.bookstore.library.author.Author;
import dev.kapiaszczyk.bookstore.library.credit.Credit;
import dev.kapiaszczyk.bookstore.library.credit.CreditRepository;
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
public class CreditTest {

    @Autowired
    CreditRepository creditRepository;

    private Credit credit;
    private Author author;

    @BeforeEach
    public void setUp() {
        author = new Author();
        author.setAuthorName("John");
        author.setAuthorSurname("Doe");

        credit = new Credit();
        credit.setAuthor(author);
        author.addCredit(credit);

        creditRepository.save(credit);
    }

    @Test
    public void creditCanBeAdded() {
        Credit savedCredit = creditRepository.save(credit);

        assertNotNull(savedCredit.getCreditId());
        assertThat(savedCredit.getAuthor().getAuthorName(), equalTo("John"));
        assertThat(savedCredit.getAuthor().getAuthorSurname(), equalTo("Doe"));

    }

    @Test
    public void creditCanBeUpdated() {
        Credit savedCredit = creditRepository.save(credit);

        savedCredit.getAuthor().setAuthorName("Jane");
        savedCredit.getAuthor().setAuthorSurname("Smith");
        creditRepository.save(savedCredit);

        Credit updatedCredit = creditRepository.findById(savedCredit.getCreditId()).get();

        assertThat(updatedCredit.getAuthor().getAuthorName(), equalTo("Jane"));
        assertThat(updatedCredit.getAuthor().getAuthorSurname(), equalTo("Smith"));
    }

    @Test
    public void creditCanBeDeleted() {
        Credit savedCredit = creditRepository.save(credit);

        author.removeCredit(credit);
        creditRepository.delete(savedCredit);

        assertEquals(0, creditRepository.count());

    }
}
