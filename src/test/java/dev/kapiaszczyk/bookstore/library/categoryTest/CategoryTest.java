package dev.kapiaszczyk.bookstore.library.categoryTest;

import dev.kapiaszczyk.bookstore.library.book.Book;
import dev.kapiaszczyk.bookstore.library.category.Category;
import dev.kapiaszczyk.bookstore.library.category.CategoryRepository;
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
public class CategoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void addCategory() {
        Category category = new Category();
        category.setCategoryName("Fantasy");

        categoryRepository.save(category);

        assertNotNull(category.getCategoryId());
        assertEquals("Fantasy", category.getCategoryName());
    }

    @Test
    public void addCategoryWithBook() {
        Book book = new Book();
        book.setBookTitle("Harry Potter");

        Category category = new Category();
        category.setCategoryName("Fantasy");

        category.addBook(book);
        book.setCategory(category);

        categoryRepository.save(category);

        Category savedCategory = categoryRepository.findById(category.getCategoryId()).get();

        assertNotNull(savedCategory.getCategoryId());
        assertEquals("Fantasy", savedCategory.getCategoryName());
        assertEquals("Harry Potter", savedCategory.getBooks().get(0).getBookTitle());
    }

    @Test
    public void updateCategory() {
        Category category = new Category();
        category.setCategoryName("Fantasy");

        categoryRepository.save(category);

        Category savedCategory = categoryRepository.findById(category.getCategoryId()).get();

        savedCategory.setCategoryName("Fantasy updated");

        categoryRepository.save(savedCategory);

        Category updatedCategory = categoryRepository.findById(savedCategory.getCategoryId()).get();

        assertEquals("Fantasy updated", updatedCategory.getCategoryName());
    }

    @Test
    public void deleteCategory() {
        Category category = new Category();
        category.setCategoryName("Fantasy");

        categoryRepository.save(category);

        Category savedCategory = categoryRepository.findById(category.getCategoryId()).get();

        categoryRepository.delete(savedCategory);

        assertEquals(0, categoryRepository.count());
    }


}
