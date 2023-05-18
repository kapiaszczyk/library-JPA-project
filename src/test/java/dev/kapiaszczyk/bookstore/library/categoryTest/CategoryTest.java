package dev.kapiaszczyk.bookstore.library.categoryTest;

import dev.kapiaszczyk.bookstore.library.book.Book;
import dev.kapiaszczyk.bookstore.library.book.BookRepository;
import dev.kapiaszczyk.bookstore.library.category.Category;
import dev.kapiaszczyk.bookstore.library.category.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class CategoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
        category.setCategoryName("Fantasy");
        categoryRepository.save(category);
    }

    @Test
    public void categoryCanBeAdded() {
        Category savedCategory = categoryRepository.findById(category.getCategoryId()).get();

        assertNotNull(savedCategory.getCategoryId());
        assertThat(savedCategory.getCategoryName(), equalTo(category.getCategoryName()));
    }

    @Test
    public void categoryCanBeUpdated() {
        Category savedCategory = categoryRepository.findById(category.getCategoryId()).get();
        savedCategory.setCategoryName("Romance");
        categoryRepository.save(savedCategory);

        Category updatedCategory = categoryRepository.findById(savedCategory.getCategoryId()).get();

        assertThat(updatedCategory.getCategoryName(), equalTo(savedCategory.getCategoryName()));
    }

    @Test
    public void categoryCanBeDeleted() {
        Category savedCategory = categoryRepository.findById(category.getCategoryId()).get();

        categoryRepository.delete(savedCategory);

        assertEquals(0, categoryRepository.count());
    }

    @Test
    public void categoryDeletedWhenAssociatedWithBook() {
        Book book = new Book();
        book.setTitle("Test title");
        book.setCategory(category);

        bookRepository.save(book);

        Category savedCategory = categoryRepository.findById(category.getCategoryId()).get();

        categoryRepository.delete(savedCategory);
        assertEquals(0, categoryRepository.count());

    }


}
