package dev.kapiaszczyk.bookstore.library.libraryTest;

import dev.kapiaszczyk.bookstore.library.address.Address;
import dev.kapiaszczyk.bookstore.library.city.City;
import dev.kapiaszczyk.bookstore.library.city.CityRepository;
import dev.kapiaszczyk.bookstore.library.cityCode.CityCode;
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

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LibraryTest {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void addLibrary() {

        Library library = new Library();
        library.setLibraryName("Warsaw Library");

        City city = new City();
        city.setCityName("Warsaw");

        CityCode cityCode = new CityCode();
        cityCode.setCityCode("00-001");

        city.setCityCode(Collections.singletonList(cityCode));
        cityCode.setCity(city);

        Address address = new Address();
        address.setCity(city);
        address.setStreet("Copernicus Street 5");
        library.setAddress(address);
        address.setLibrary(library);

        Inventory inventory = new Inventory();
        inventory.setLibrary(library);
        library.setInventory(inventory);

        cityRepository.save(city);
        libraryRepository.save(library);

        Library savedLibrary = libraryRepository.findById(library.getLibraryId()).get();

        assertNotNull(savedLibrary.getLibraryId());
        assertEquals("Warsaw Library", savedLibrary.getLibraryName());
        assertEquals("Warsaw", savedLibrary.getAddress().getCity().getCityName());
        assertEquals("00-001", savedLibrary.getAddress().getCity().getCityCode().get(0).getCityCode());
        assertEquals("Copernicus Street 5", savedLibrary.getAddress().getStreet());
        assertEquals(library.getInventory().getInventoryId(), savedLibrary.getInventory().getInventoryId());

    }

    @Test
    public void updateLibrary() {
        Library library = new Library();
        library.setLibraryName("Warsaw Library");

        City city = new City();
        city.setCityName("Warsaw");

        CityCode cityCode = new CityCode();
        cityCode.setCityCode("00-001");

        city.setCityCode(Collections.singletonList(cityCode));
        cityCode.setCity(city);

        Address address = new Address();
        address.setCity(city);
        address.setStreet("Copernicus Street 5");
        library.setAddress(address);
        address.setLibrary(library);

        Inventory inventory = new Inventory();
        inventory.setLibrary(library);
        library.setInventory(inventory);

        cityRepository.save(city);
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

        City city = new City();
        city.setCityName("Warsaw");

        CityCode cityCode = new CityCode();
        cityCode.setCityCode("00-001");

        city.setCityCode(Collections.singletonList(cityCode));
        cityCode.setCity(city);

        Address address = new Address();
        address.setCity(city);
        address.setStreet("Copernicus Street 5");
        library.setAddress(address);
        address.setLibrary(library);

        Inventory inventory = new Inventory();
        inventory.setLibrary(library);
        library.setInventory(inventory);

        cityRepository.save(city);
        libraryRepository.save(library);

        Library savedLibrary = libraryRepository.findById(library.getLibraryId()).get();

        address.removeLibrary();
        libraryRepository.delete(savedLibrary);

        assertEquals(0, libraryRepository.count());
    }

}
