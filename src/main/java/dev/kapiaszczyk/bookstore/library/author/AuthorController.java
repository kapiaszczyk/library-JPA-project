package dev.kapiaszczyk.bookstore.library.author;

import dev.kapiaszczyk.bookstore.library.author.dto.AuthorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // Create
    @RequestMapping("/add")
    public ResponseEntity<AuthorDTO> addAuthor(@RequestParam("first_name") String firstName, @RequestParam("last_name") String lastName) {
        return new ResponseEntity(authorService.addAuthor(firstName, lastName), HttpStatus.OK);
    }

    // Get by id
    @RequestMapping("/by-id")
    public ResponseEntity<AuthorDTO> getAuthorById(@RequestParam("id") Long id) {
        return new ResponseEntity(authorService.findById(id), HttpStatus.OK);
    }

    // Update
    @PutMapping("/update")
    public ResponseEntity<AuthorDTO> updateAuthor(@RequestParam("id") Long id, @RequestParam("first_name") String firstName, @RequestParam("last_name") String lastName) {
        return new ResponseEntity(authorService.updateAuthor(id, firstName, lastName), HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<AuthorDTO> deleteAuthor(@RequestParam("id") Long id) {
        return new ResponseEntity(authorService.deleteAuthor(id), HttpStatus.OK);
    }

    // Get all
    @RequestMapping("/all")
    public ResponseEntity<Iterable<AuthorDTO>> getAllAuthors() {
        return new ResponseEntity(authorService.findAll(), HttpStatus.OK);
    }

    // Get by first or last name
    @RequestMapping("/by-name")
    public ResponseEntity<Iterable<AuthorDTO>> getAuthorsByFirstOrLastName(@RequestParam("first_name") String firstName, @RequestParam("last_name") String lastName) {
        return new ResponseEntity(authorService.findByFirstNameOrLastName(firstName, lastName), HttpStatus.OK);
    }

}
