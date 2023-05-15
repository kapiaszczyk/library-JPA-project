package dev.kapiaszczyk.bookstore.library.cityCode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CityCodeTest {

    @Autowired
    private CityCodeRepository cityRepository;

    @Test
    public void saveCityCode() {
        CityCode cityCode = new CityCode("00-000");
        cityRepository.save(cityCode);
        assertNotNull(cityCode);
    }

    @Test
    public void updateCityCode() {
        CityCode cityCode = new CityCode("00-000");
        cityRepository.save(cityCode);
        cityCode.setCityCode("11-111");
        cityRepository.save(cityCode);
        assertNotNull(cityCode);
    }

    @Test
    public void deleteCityCode() {
        CityCode cityCode = new CityCode("00-000");
        cityRepository.save(cityCode);
        cityRepository.delete(cityCode);
    }

}
