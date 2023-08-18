package dev.kapiaszczyk.bookstore.library.category;

import dev.kapiaszczyk.bookstore.library.category.dto.CategoryStatisticsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Category> getCategoryByName(@RequestParam("category_name") String categoryName) {
        return new ResponseEntity<>(categoryService.findByNameIgnoreCase(categoryName).orElse(null), HttpStatus.OK);
    }

    @GetMapping("/book_statistics/all")
    public ResponseEntity<Iterable<CategoryStatisticsDTO>> getAllCategoriesStatistics() {
        return new ResponseEntity<>(categoryService.getAllCategoriesBookCountStatistics(), HttpStatus.OK);
    }

}
