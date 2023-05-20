package dev.kapiaszczyk.bookstore.library.authorTest;

import dev.kapiaszczyk.bookstore.library.author.Author;
import dev.kapiaszczyk.bookstore.library.author.AuthorDTO;
import dev.kapiaszczyk.bookstore.library.author.AuthorMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class AuthorMapperTest {

    @Test
    public void shouldMapToAuthorDto() {
        Author author = new Author();
        author.setId(1L);
        author.setFirstName("Sample Author");
        author.setLastName("Sample Author");

        // Perform the mapping
        AuthorDTO dto = AuthorMapper.INSTANCE.mapToAuthorDTO(author);

        // Verify the mapping
        assertEquals(author.getId(), dto.getId());
        assertEquals(author.getFirstName(), dto.getFirstName());
        assertEquals(author.getLastName(), dto.getLastName());
    }
}
