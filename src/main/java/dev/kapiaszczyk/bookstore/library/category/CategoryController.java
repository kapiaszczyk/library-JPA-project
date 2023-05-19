package dev.kapiaszczyk.bookstore.library.category;

import dev.kapiaszczyk.bookstore.library.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find_by_name/{categoryName}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String categoryName) {
        return new ResponseEntity<>(categoryService.findByNameIgnoreCase(categoryName).orElse(null), HttpStatus.OK);
    }

    @GetMapping("/book_statistics/all")
    public ResponseEntity<Iterable<CategoryStatisticsDTO>> getAllCategoriesStatistics() {
        return new ResponseEntity<>(categoryService.getAllCategoriesBookCountStatistics(), HttpStatus.OK);
    }

}
