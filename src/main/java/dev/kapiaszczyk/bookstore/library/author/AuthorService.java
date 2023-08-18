package dev.kapiaszczyk.bookstore.library.author;

import dev.kapiaszczyk.bookstore.library.author.dto.AuthorDTO;
import dev.kapiaszczyk.bookstore.library.author.dto.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Iterable<AuthorDTO> findAll() {
        List<Author> results = authorRepository.findAll();
        return AuthorMapper.INSTANCE.mapToAuthorDTOList(results);
    }

    public Iterable<AuthorDTO> findByFirstNameOrLastName(String firstName, String lastName) {
        List<Author> results = authorRepository.findByFirstNameOrLastName(firstName, lastName);
        return AuthorMapper.INSTANCE.mapToAuthorDTOList(results);
    }

    public Object addAuthor(String firstName, String lastName) {
        authorRepository.save(new Author(firstName, lastName));
        return HttpStatus.OK;
    }

    public Object updateAuthor(Long id, String firstName, String lastName) {
        Author author = authorRepository.findById(id).get();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorRepository.save(author);
        return HttpStatus.OK;
    }

    public Object deleteAuthor(Long id) {
        authorRepository.deleteById(id);
        return HttpStatus.OK;
    }

    public Object findById(Long id) {
        Author author = authorRepository.findById(id).get();
        return AuthorMapper.INSTANCE.mapToAuthorDTO(author);
    }
}
