package dev.kapiaszczyk.bookstore.library.inventoryTest;

import dev.kapiaszczyk.bookstore.library.inventory.Inventory;
import dev.kapiaszczyk.bookstore.library.inventory.InventoryRepository;
import dev.kapiaszczyk.bookstore.library.library.Library;
import dev.kapiaszczyk.bookstore.library.library.LibraryRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class InventoryRepositoryTest {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    private Inventory inventory;
    private Library library;

    @BeforeEach
    public void setUp() {
        library = new Library();
        library.setLibraryName("London Library");

        inventory = new Inventory();
        inventory.setLibrary(library);
        library.setInventory(inventory);

        libraryRepository.save(library);
    }

    @Test
    public void shouldFindByLibraryName() {
        Inventory savedInventory = inventoryRepository.findByLibraryLibraryName(library.getLibraryName());

        assertNotNull(savedInventory.getInventoryId());
        assertThat(savedInventory.getLibrary().getLibraryName(), equalTo(library.getLibraryName()));
    }

}
