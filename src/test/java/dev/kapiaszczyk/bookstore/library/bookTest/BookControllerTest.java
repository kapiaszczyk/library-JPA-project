package dev.kapiaszczyk.bookstore.library.bookTest;

import dev.kapiaszczyk.bookstore.library.book.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
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

}
