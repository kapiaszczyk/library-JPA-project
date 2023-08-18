package dev.kapiaszczyk.bookstore.library.category;

import dev.kapiaszczyk.bookstore.library.category.dto.CategoryStatisticsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Create category
    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return new ResponseEntity(categoryService.save(category), HttpStatus.CREATED);
    }

    // Get by id
    @GetMapping("/by_id")
    public ResponseEntity<Category> getCategoryById(@RequestParam("id") Long id) {
        return new ResponseEntity<>(categoryService.findById(id).orElse(null), HttpStatus.OK);
    }

    // Update category
    @PutMapping("/update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        return new ResponseEntity(categoryService.save(category), HttpStatus.OK);
    }

    // Delete category
    @DeleteMapping("/delete")
    public ResponseEntity deleteCategory(@RequestParam("id") Long id) {
        categoryService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find_by_name")
    public ResponseEntity<Category> getCategoryByName(@RequestParam("category_name") String categoryName) {
        return new ResponseEntity<>(categoryService.findByNameIgnoreCase(categoryName).orElse(null), HttpStatus.OK);
    }

    @GetMapping("/book_statistics/all")
    public ResponseEntity<Iterable<CategoryStatisticsDTO>> getAllCategoriesStatistics() {
        return new ResponseEntity<>(categoryService.getAllCategoriesBookCountStatistics(), HttpStatus.OK);
    }

}
