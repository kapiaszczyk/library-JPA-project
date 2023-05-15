package dev.kapiaszczyk.bookstore.library.cityTest;

import dev.kapiaszczyk.bookstore.library.city.City;
import dev.kapiaszczyk.bookstore.library.city.CityRepository;
import dev.kapiaszczyk.bookstore.library.cityCode.CityCode;
import dev.kapiaszczyk.bookstore.library.cityCode.CityCodeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityTest {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityCodeRepository cityCodeRepository;

    @Test
    public void saveCityTest() {
        City city = new City("Warsaw");
        cityRepository.save(city);

        Long cityId = city.getCityId();

        assertNotNull(cityId);
    }

    @Test
    public void saveCityWithCityCodeTest() {
        CityCode cityCode = new CityCode("00-000");

        City city = new City("Warsaw");
        city.setCityCode(Collections.singletonList(cityCode));
        cityCode.setCity(city);

        cityRepository.save(city);

        Long cityId = city.getCityId();

        assertNotNull(cityId);
    }

    @Test
    public void updateCityTest() {
        City city = new City("Warsaw");
        cityRepository.save(city);
        city.setCityName("Cracow");
        cityRepository.save(city);
        assertNotNull(city);
    }

    @Test
    public void deleteCityTest() {
        City city = new City("Warsaw");
        cityRepository.save(city);
        cityRepository.delete(city);
        assertNotNull(city);
    }

}
