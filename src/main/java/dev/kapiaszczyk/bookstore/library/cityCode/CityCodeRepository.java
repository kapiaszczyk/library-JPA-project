package dev.kapiaszczyk.bookstore.library.cityCode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityCodeRepository extends JpaRepository<CityCode, Long> {
    CityCode findByCityCode(String cityCode);

    CityCode findByCityCityName(String cityName);
}
