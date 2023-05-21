package dev.kapiaszczyk.bookstore.library.categoryTest;

import dev.kapiaszczyk.bookstore.library.category.Category;
import dev.kapiaszczyk.bookstore.library.category.CategoryController;
import dev.kapiaszczyk.bookstore.library.category.CategoryService;
import dev.kapiaszczyk.bookstore.library.category.dto.CategoryStatisticsDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void shouldGetAllCategories() throws Exception {
        // Prepare test data
        List<Category> expectedCategories = new ArrayList<>();
        expectedCategories.add(new Category("Romance"));
        expectedCategories.add(new Category("Fantasy"));

        // Mock the service method
        Mockito.when(categoryService.findAll()).thenReturn(expectedCategories);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/categories/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Romance"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Fantasy"));

        // Verify the service method was called
        Mockito.verify(categoryService, Mockito.times(1)).findAll();
    }

    @Test
    public void shouldGetCategoryByNameIgnoreCase() throws Exception {
        //Prepare test data
        Category expectedCategory = new Category("Romance");

        // Mock the service method
        Mockito.when(categoryService.findByNameIgnoreCase(expectedCategory.getName())).thenReturn(java.util.Optional.of(expectedCategory));

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/categories/find_by_name/" + expectedCategory.getName()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Romance"));

        // Verify the service method was called
        Mockito.verify(categoryService, Mockito.times(1)).findByNameIgnoreCase(expectedCategory.getName());
    }

    @Test
    public void shouldGetAllCategoriesStatistics() throws Exception {
        // Prepare test data
        List<CategoryStatisticsDTO> expectedStatistics = new ArrayList<>();
        expectedStatistics.add(new CategoryStatisticsDTO( "Romance", 4L));
        expectedStatistics.add(new CategoryStatisticsDTO( "Fantasy", 9L));

        // Mock the service method
        Mockito.when(categoryService.getAllCategoriesBookCountStatistics()).thenReturn(expectedStatistics);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/categories/book_statistics/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Romance"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookCount").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Fantasy"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookCount").value(9));

        // Verify that the service method was called
        Mockito.verify(categoryService, Mockito.times(1)).getAllCategoriesBookCountStatistics();
    }
}
