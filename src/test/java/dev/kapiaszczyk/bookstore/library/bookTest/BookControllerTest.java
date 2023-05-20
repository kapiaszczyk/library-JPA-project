package dev.kapiaszczyk.bookstore.library.bookTest;

import dev.kapiaszczyk.bookstore.library.author.Author;
import dev.kapiaszczyk.bookstore.library.book.*;
import dev.kapiaszczyk.bookstore.library.credit.Credit;
import dev.kapiaszczyk.bookstore.library.inventory.Inventory;
import dev.kapiaszczyk.bookstore.library.library.Library;
import dev.kapiaszczyk.bookstore.library.loan.Loan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
@ActiveProfiles("test")
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void shouldGetAllBooks() throws Exception {
        // Prepare test data
        List<BookDTO> bookList = new ArrayList<>();
        Book book = new Book();
        book.setTitle("Test");
        BookDTO bookDTO = BookMapper.INSTANCE.mapToDTO(book);
        bookList.add(bookDTO);

        // Mock service
        Mockito.when(bookService.findAll()).thenReturn(bookList);

        // Perform GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/books/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Test"));

        // Verify service method was called
        Mockito.verify(bookService, Mockito.times(1)).findAll();
    }

    @Test
    public void shouldGetAllBookInformation() throws Exception {
        // Mock BookInformationProjection implementation
        class BookInformationProjectionImpl implements BookInformationProjection {
            private String title;
            private String category;

            private List<Author> authors;

            @Override
            public String getTitle() {
                return "Title";
            }

            @Override
            public String getIsbn() {
                return "1234567890123";
            }

            @Override
            public List<Author> getAuthors() {
                return Arrays.asList(new Author("John", "Doe"), new Author("Jane", "Doe"));
            }
        }

        // Create a list of mock BookInformationProjection objects
        List<BookInformationProjection> bookInformationProjections = Arrays.asList(
                new BookInformationProjectionImpl());

        // Mock service
        Mockito.when(bookService.getAllBooksWithTitleIsbnAuthors()).thenReturn(bookInformationProjections);

        // Call the service method
        mockMvc.perform(MockMvcRequestBuilders.get("/books/title-isbn-authors"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Title"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].isbn").value("1234567890123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].authors[0].firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].authors[0].lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].authors[1].firstName").value("Jane"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].authors[1].lastName").value("Doe"));
    }

    @Test
    public void shouldGetBooksByTitleAndInventoryLibraryName() throws Exception {
        // Prepare test data
        List<BookDTO> bookList = new ArrayList<>();
        Book book = new Book();
        book.setTitle("Test book name");

        Book book2 = new Book();
        book2.setTitle("Test book name 2");

        Library library = new Library();
        library.setName("Test library name");
        library.setId(1L);

        Inventory inventory = new Inventory();
        inventory.setLibrary(library);
        inventory.setId(1L);

        book.setInventory(inventory);
        book2.setInventory(inventory);
        inventory.addBook(book);
        inventory.addBook(book2);

        BookDTO bookDTO = BookMapper.INSTANCE.mapToDTO(book);
        BookDTO bookDTO2 = BookMapper.INSTANCE.mapToDTO(book2);

        bookList.add(bookDTO);
        bookList.add(bookDTO2);

        // Mock service
        Mockito.when(bookService.getBooksByTitleAndInventoryLibraryName(book.getTitle(), library.getName())).thenReturn(bookList);

        // Perform GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/books/title/" + book.getTitle() +"/library/" + library.getName()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(book.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value(book2.getTitle()));

        // Verify service method was called
        Mockito.verify(bookService, Mockito.times(1)).getBooksByTitleAndInventoryLibraryName(book.getTitle(), library.getName());
    }

    @Test
    public void shouldGetBooksByAuthorNameAndSurname() throws Exception {
        // Prepare test data
        List<BookDTO> bookList = new ArrayList<>();
        Book book = new Book();
        book.setTitle("Test");

        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");

        Author author2 = new Author();
        author2.setFirstName("Jane");
        author2.setLastName("Doe");

        Credit credit = new Credit();
        credit.setAuthor(author);
        author.addCredit(credit);

        Credit credit2 = new Credit();
        credit2.setAuthor(author2);
        author2.addCredit(credit2);

        book.addCredit(credit);
        book.addCredit(credit2);
        credit.setBook(book);
        credit2.setBook(book);

        BookDTO bookDTO = BookMapper.INSTANCE.mapToDTO(book);
        bookList.add(bookDTO);

        // Mock service
        Mockito.when(bookService.findAll()).thenReturn(bookList);

        // [{"id":null,"title":"Test","categoryId":null,"isbnId":null,"inventoryId":null,"credits":[{"id":null,"author":{"id":null,"firstName":"John","lastName":"Doe"}}],"loanId":null}]
        // Perform GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/books/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(book.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].credits[0].author.firstName").value(author.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].credits[0].author.lastName").value(author.getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].credits[1].author.firstName").value(author2.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].credits[1].author.lastName").value(author2.getLastName()));

        // Verify service method was called
        Mockito.verify(bookService, Mockito.times(1)).findAll();
    }

    @Test
    public void shouldGetAllBooksByLoanStatus() throws Exception {
        // Prepare test data
        List<BookDTO> bookList = new ArrayList<>();
        Book book = new Book();
        book.setTitle("Test");

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setStatus(Loan.Status.ACTIVE);
        book.setLoan(loan);

        BookDTO bookDTO = BookMapper.INSTANCE.mapToDTO(book);
        bookList.add(bookDTO);

        // Mock service
        Mockito.when(bookService.findAll()).thenReturn(bookList);

        // Perform GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/books/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(book.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].loan.status").value(loan.getStatus().toString()));

        // Verify service method was called
        Mockito.verify(bookService, Mockito.times(1)).findAll();
    }
}
