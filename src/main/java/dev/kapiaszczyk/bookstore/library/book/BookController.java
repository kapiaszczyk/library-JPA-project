package dev.kapiaszczyk.bookstore.library.book;

import dev.kapiaszczyk.bookstore.library.inventory.Inventory;
import dev.kapiaszczyk.bookstore.library.loan.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<BookDTO>> getAllBooks() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/title-isbn-authors")
    public ResponseEntity<Iterable<BookInformationProjection>> findAllBooksWithTitleIsbnAuthors() {
        return new ResponseEntity<>(bookService.getAllBooksWithTitleIsbnAuthors(), HttpStatus.OK);
    }

    @GetMapping("/title/{title}/library/{name}")
    public ResponseEntity<Iterable<BookDTO>> getBooksByTitleAndInventoryLibraryName(@PathVariable String title, @PathVariable String name) {
        return new ResponseEntity<>(bookService.getBooksByTitleAndInventoryLibraryName(title, name), HttpStatus.OK);
    }

    @GetMapping("/author-name/{firstName}/author-surname/{lastName}")
    public ResponseEntity<Iterable<String>> getBooksByAuthorNameAndSurname(@PathVariable String firstName, @PathVariable String lastName) {
        return new ResponseEntity<>(bookService.getBooksByAuthorNameAndSurname(firstName, lastName), HttpStatus.OK);
    }

    @GetMapping("/loan-status/{status}")
    public ResponseEntity<Iterable<BookDTO>> getAllByLoanStatus(@PathVariable Loan.Status status) {
        return new ResponseEntity<>(bookService.getAllByLoanStatus(status), HttpStatus.OK);
    }


}
