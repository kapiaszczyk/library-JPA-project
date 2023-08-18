package dev.kapiaszczyk.bookstore.library.book;

import dev.kapiaszczyk.bookstore.library.book.dto.BookDTO;
import dev.kapiaszczyk.bookstore.library.book.projections.BookInformationProjection;
import dev.kapiaszczyk.bookstore.library.loan.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    // Create
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return new ResponseEntity(bookService.addBook(book), HttpStatus.OK);
    }

    // Get by id
    @GetMapping("/by-id")
    public ResponseEntity<BookDTO> getBookById(@RequestParam("id") Long id) {
        return new ResponseEntity(bookService.findById(id), HttpStatus.OK);
    }

    // Update
    @PutMapping("/update")
    public ResponseEntity<BookDTO> updateBook(@RequestParam("id") Long id, @RequestBody Book book) {
        return new ResponseEntity(bookService.updateBook(id, book), HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<BookDTO> deleteBook(@RequestParam("id") Long id) {
        return new ResponseEntity(bookService.deleteBook(id), HttpStatus.OK);
    }

    // Get all
    @GetMapping("/all")
    public ResponseEntity<Iterable<BookDTO>> getAllBooks() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/title-isbn-authors")
    public ResponseEntity<Iterable<BookInformationProjection>> findAllBooksWithTitleIsbnAuthors() {
        return new ResponseEntity<>(bookService.getAllBooksWithTitleIsbnAuthors(), HttpStatus.OK);
    }

    @GetMapping("/by-title")
    public ResponseEntity<Iterable<BookDTO>> getBooksByTitleAndInventoryLibraryName(@RequestParam("title") String title, @RequestParam("library_name") String name) {
        return new ResponseEntity<>(bookService.getBooksByTitleAndInventoryLibraryName(title, name), HttpStatus.OK);
    }

    @GetMapping("/by-author")
        public ResponseEntity<Iterable<String>> getBooksByAuthorNameAndSurname(@RequestParam("first_name") String firstName, @RequestParam("last_name") String lastName) {
        return new ResponseEntity<>(bookService.getBooksByAuthorNameAndSurname(firstName, lastName), HttpStatus.OK);
    }

    @GetMapping("/loan-status")
    public ResponseEntity<Iterable<BookDTO>> getAllByLoanStatus(@RequestParam("status") Loan.Status status) {
        return new ResponseEntity<>(bookService.getAllByLoanStatus(status), HttpStatus.OK);
    }

}
