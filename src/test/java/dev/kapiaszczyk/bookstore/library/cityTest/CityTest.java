package dev.kapiaszczyk.bookstore.library.cityTest;

import dev.kapiaszczyk.bookstore.library.city.City;
import dev.kapiaszczyk.bookstore.library.city.CityRepository;
import dev.kapiaszczyk.bookstore.library.cityCode.CityCode;
import dev.kapiaszczyk.bookstore.library.cityCode.CityCodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CityTest {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityCodeRepository cityCodeRepository;

    private City city;

    @BeforeEach
    public void setUp() {
        city = new City("Warsaw");
        cityRepository.save(city);
    }

    @Test
    public void saveCityTest() {
        City savedCity = cityRepository.findById(city.getCityId()).get();
        assertNotNull(savedCity);
    }

    @Test
    public void saveCityWithCityCodeTest() {
        CityCode cityCode = new CityCode("00-000");
        City city = new City("Gdansk");
        city.setCityCode(Collections.singletonList(cityCode));
        cityCode.setCity(city);

        cityRepository.save(city);

        City savedCity = cityRepository.findById(city.getCityId()).get();
        assertNotNull(savedCity);
    }

    @Test
    public void updateCityTest() {
        City savedCity = cityRepository.findById(city.getCityId()).get();
        savedCity.setCityName("Cracow");
        cityRepository.save(savedCity);

        City updatedCity = cityRepository.findById(city.getCityId()).get();
        assertNotNull(updatedCity);
        assertThat(savedCity.getCityId(), equalTo(updatedCity.getCityId()));
        assertThat(savedCity.getCityName(), equalTo(updatedCity.getCityName()));
    }

    @Test
    public void deleteCityTest() {
        City savedCity = cityRepository.findById(city.getCityId()).get();

        cityRepository.delete(savedCity);
        assertEquals(0, cityRepository.count());
    }

}
