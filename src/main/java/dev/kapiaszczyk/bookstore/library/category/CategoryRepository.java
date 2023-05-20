package dev.kapiaszczyk.bookstore.library.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByNameIgnoreCase(String categoryName);
    @Query("SELECT c.id, c.name, COUNT(b.id) AS bookCount " +
            "FROM Category c LEFT JOIN c.books b " +
            "GROUP BY c.id, c.name")
    List<Object[]> getAllCategoriesStatistics();

}
