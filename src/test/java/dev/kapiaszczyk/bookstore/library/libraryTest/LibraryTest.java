package dev.kapiaszczyk.bookstore.library.libraryTest;

import dev.kapiaszczyk.bookstore.library.inventory.Inventory;
import dev.kapiaszczyk.bookstore.library.library.Library;
import dev.kapiaszczyk.bookstore.library.library.LibraryRepository;
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
public class LibraryTest {

    @Autowired
    private LibraryRepository libraryRepository;

    private Library library;
    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        library = new Library();
        library.setLibraryName("Warsaw Library");

        inventory = new Inventory();
        inventory.setLibrary(library);
        library.setInventory(inventory);

        libraryRepository.save(library);
    }

    @Test
    public void libraryCanBeAdded() {
        Library savedLibrary = libraryRepository.findById(library.getId()).get();

        assertNotNull(savedLibrary.getId());
        assertThat(savedLibrary.getLibraryName(), equalTo(library.getLibraryName()));
        assertThat(savedLibrary.getInventory().getId(), equalTo(inventory.getId()));

    }

    @Test
    public void libraryCanBeUpdated() {
        Library savedLibrary = libraryRepository.findById(library.getId()).get();

        savedLibrary.setLibraryName("Cracow Library");
        libraryRepository.save(savedLibrary);

        Library updatedLibrary = libraryRepository.findById(library.getId()).get();
        assertThat(updatedLibrary.getLibraryName(), equalTo(savedLibrary.getLibraryName()));

    }

    @Test
    public void libraryCanBeDeleted() {
        Library savedLibrary = libraryRepository.findById(library.getId()).get();

        libraryRepository.delete(savedLibrary);

        assertEquals(0, libraryRepository.count());
    }

}
