package dev.kapiaszczyk.bookstore.library.category;

import dev.kapiaszczyk.bookstore.library.category.dto.CategoryMapper;
import dev.kapiaszczyk.bookstore.library.category.dto.CategoryStatisticsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findByNameIgnoreCase(String categoryName) {
        return categoryRepository.findByNameIgnoreCase(categoryName);
    }

    public List<CategoryStatisticsDTO> getAllCategoriesBookCountStatistics() {
        List<Object[]> results = categoryRepository.getAllCategoriesStatistics();
        return CategoryMapper.mapToDTOList(results);
    }

    public Object save(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Object deleteById(Long id) {
        categoryRepository.deleteById(id);
        return HttpStatus.OK;
    }
}
