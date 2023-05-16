package dev.kapiaszczyk.bookstore.library.libraryTest;

import dev.kapiaszczyk.bookstore.library.inventory.Inventory;
import dev.kapiaszczyk.bookstore.library.library.Library;
import dev.kapiaszczyk.bookstore.library.library.LibraryRepository;
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
public class LibraryTest {

    @Autowired
    private LibraryRepository libraryRepository;

    @Test
    public void addLibrary() {

        Library library = new Library();
        library.setLibraryName("Warsaw Library");


        Inventory inventory = new Inventory();
        inventory.setLibrary(library);
        library.setInventory(inventory);

        libraryRepository.save(library);

        Library savedLibrary = libraryRepository.findById(library.getLibraryId()).get();

        assertNotNull(savedLibrary.getLibraryId());
        assertEquals("Warsaw Library", savedLibrary.getLibraryName());
        assertEquals(library.getInventory().getInventoryId(), savedLibrary.getInventory().getInventoryId());

    }

    @Test
    public void updateLibrary() {
        Library library = new Library();
        library.setLibraryName("Warsaw Library");

        Inventory inventory = new Inventory();
        inventory.setLibrary(library);
        library.setInventory(inventory);

        libraryRepository.save(library);

        Library savedLibrary = libraryRepository.findById(library.getLibraryId()).get();

        savedLibrary.setLibraryName("Cracow Library");

        libraryRepository.save(savedLibrary);

        Library updatedLibrary = libraryRepository.findById(library.getLibraryId()).get();
        assertEquals("Cracow Library", updatedLibrary.getLibraryName());

    }

    @Test
    public void deleteLibrary() {
        Library library = new Library();
        library.setLibraryName("Warsaw Library");

        Inventory inventory = new Inventory();
        inventory.setLibrary(library);
        library.setInventory(inventory);

        libraryRepository.save(library);

        Library savedLibrary = libraryRepository.findById(library.getLibraryId()).get();

        libraryRepository.delete(savedLibrary);

        assertEquals(0, libraryRepository.count());
    }

}
