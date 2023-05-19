package dev.kapiaszczyk.bookstore.library.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<Category> findByNameIgnoreCase(String categoryName) {
        return categoryRepository.findByNameIgnoreCase(categoryName);
    }

}
