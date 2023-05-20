package dev.kapiaszczyk.bookstore.library.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Override
    @Query("SELECT b FROM Book b " +
            "JOIN FETCH b.isbn " +
            "JOIN FETCH b.category " +
            "JOIN FETCH b.credits " +
            "JOIN FETCH b.loan")
    List<Book> findAll();

    @Query("SELECT b.title AS title, i.number AS isbn, " +
            "STRING_AGG(CONCAT(a.firstName, ' ', a.lastName), ', ') AS authors " +
            "FROM Book b " +
            "JOIN b.isbn i " +
            "JOIN b.credits c " +
            "JOIN c.author a " +
            "GROUP BY b.title, i.number")
    List<BookInformationProjection> findAllBooksWithTitleIsbnAuthors();
    List<Book> findBooksByTitleAndInventoryLibraryName(String title, String name);
    List<Book> findByTitle(String title);
    List<Book> findByTitleContaining(String title);
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByIsbnNumber(String number);
    List<Book> findByCreditsAuthorFirstName(String firstName);
    List<Book> findByCreditsAuthorLastName(String lastName);
    List<BookDTO> findByCreditsAuthorFirstNameAndCreditsAuthorLastName(String firstName, String lastName);
    List<Book> findAllByCategoryNameLike(String name);
    List<Book> findAllByOrderByTitleAsc();

}
