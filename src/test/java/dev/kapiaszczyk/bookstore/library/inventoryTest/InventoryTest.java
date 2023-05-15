package dev.kapiaszczyk.bookstore.library.inventoryTest;

import dev.kapiaszczyk.bookstore.library.inventory.Inventory;
import dev.kapiaszczyk.bookstore.library.inventory.InventoryRepository;
import dev.kapiaszczyk.bookstore.library.library.Library;
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
public class InventoryTest {

    @Autowired
    InventoryRepository inventoryRepository;

    @Test
    public void addInventory() {
        Inventory inventory = new Inventory();

        inventoryRepository.save(inventory);

        Inventory savedInventory = inventoryRepository.findById(inventory.getInventoryId()).get();

        assertNotNull(savedInventory.getInventoryId());

    }

    @Test
    public void addInventoryWithLibrary() {
        Inventory inventory = new Inventory();

        Library library = new Library();
        library.setLibraryName("Library");

        inventory.setLibrary(library);
        library.setInventory(inventory);

        inventoryRepository.save(inventory);

        Inventory savedInventory = inventoryRepository.findById(inventory.getInventoryId()).get();

        assertNotNull(savedInventory.getInventoryId());
        assertEquals("Library", savedInventory.getLibrary().getLibraryName());
    }

    @Test
    public void deleteInventory() {
        Inventory inventory = new Inventory();

        inventoryRepository.save(inventory);

        Inventory savedInventory = inventoryRepository.findById(inventory.getInventoryId()).orElse(null);

        inventoryRepository.delete(savedInventory);

        assertEquals(0, inventoryRepository.count());
    }
}
