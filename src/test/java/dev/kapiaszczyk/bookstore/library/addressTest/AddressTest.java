package dev.kapiaszczyk.bookstore.library.addressTest;

import dev.kapiaszczyk.bookstore.library.address.Address;
import dev.kapiaszczyk.bookstore.library.address.AddressRepository;
import dev.kapiaszczyk.bookstore.library.city.City;
import dev.kapiaszczyk.bookstore.library.city.CityRepository;
import dev.kapiaszczyk.bookstore.library.cityCode.CityCode;
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
public class AddressTest {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CityRepository cityRepository;

    private Address address;
    private City city;
    private CityCode cityCode;

    @Test
    public void addAddressTest() {
        city = new City("Warsaw");
        cityCode = new CityCode("00-000");

        cityCode.setCity(city);
        city.setCityCode(Collections.singletonList(cityCode));

        cityRepository.save(city);

        address = new Address();
        address.setStreet("St. Mary's Street 5");
        address.setCity(cityRepository.findById(city.getCityId()).get()); // set the city field

        addressRepository.save(address);

        Long addressId = address.getAddressId();
        assertNotNull(addressId);

        Address savedAddress = addressRepository.findById(addressId).get();

        assertNotNull(savedAddress);
        assertNotNull(savedAddress.getAddressId());
        assertEquals("St. Mary's Street 5", savedAddress.getStreet());
        assertNotNull(savedAddress.getCity());
        assertEquals("Warsaw", savedAddress.getCity().getCityName());
    }

    @Test
    public void updateAddressTest() {

        // Create address
        city = new City("Warsaw");
        cityCode = new CityCode("00-000");

        cityCode.setCity(city);
        city.setCityCode(Collections.singletonList(cityCode));

        cityRepository.save(city);

        address = new Address();
        address.setStreet("St. Mary's Street 5");
        address.setCity(cityRepository.findById(city.getCityId()).get());

        addressRepository.save(address);

        // Update address
        Address savedAddress = addressRepository.findById(address.getAddressId()).get();
        savedAddress.setStreet("St. Mary's Street 6");
        addressRepository.save(savedAddress);

        // Check if address was updated
        Address updatedAddress = addressRepository.findById(address.getAddressId()).get();
        assertEquals("St. Mary's Street 6", updatedAddress.getStreet());
    }

    @Test
    public void deleteAddress() {
        // Create address
        city = new City("Warsaw");
        cityCode = new CityCode("00-000");

        cityCode.setCity(city);
        city.setCityCode(Collections.singletonList(cityCode));

        cityRepository.save(city);

        address = new Address();
        address.setStreet("St. Mary's Street 5");
        address.setCity(cityRepository.findById(city.getCityId()).get());

        addressRepository.save(address);

        // Delete address

        addressRepository.delete(address);

        // Check if address was deleted
        assertEquals(0, addressRepository.count());
    }

}
