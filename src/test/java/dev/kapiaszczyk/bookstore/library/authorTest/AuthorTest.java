package dev.kapiaszczyk.bookstore.library.authorTest;

import dev.kapiaszczyk.bookstore.library.author.Author;
import dev.kapiaszczyk.bookstore.library.author.AuthorRepository;
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
public class AuthorTest {

    @Autowired
    private AuthorRepository authorRepository;

    private Author author;

    @Test
    public void saveAuthor() {
        author = new Author();
        author.setAuthorName("Test name");
        author.setAuthorSurname("Test surname");
        authorRepository.save(author);

        Author savedAuthor = authorRepository.findById(author.getAuthorId()).get();

        assertNotNull(savedAuthor);
        assertEquals(author.getAuthorName(), savedAuthor.getAuthorName());
        assertEquals(author.getAuthorSurname(), savedAuthor.getAuthorSurname());
    }

    @Test
    public void updateAuthor() {
        author = new Author();
        author.setAuthorName("Test name");
        author.setAuthorSurname("Test surname");
        authorRepository.save(author);

        Author savedAuthor = authorRepository.findById(author.getAuthorId()).get();

        savedAuthor.setAuthorName("Updated name");
        savedAuthor.setAuthorSurname("Updated surname");
        authorRepository.save(savedAuthor);

        Author updatedAuthor = authorRepository.findById(savedAuthor.getAuthorId()).get();
        assertEquals(savedAuthor.getAuthorName(), updatedAuthor.getAuthorName());
        assertEquals(savedAuthor.getAuthorSurname(), updatedAuthor.getAuthorSurname());
    }

    @Test
    public void deleteAuthor() {
        author = new Author();
        author.setAuthorName("Test name");
        author.setAuthorSurname("Test surname");
        authorRepository.save(author);

        Author savedAuthor = authorRepository.findById(author.getAuthorId()).get();
        authorRepository.delete(savedAuthor);

        assertEquals(0, authorRepository.count());
    }
}
