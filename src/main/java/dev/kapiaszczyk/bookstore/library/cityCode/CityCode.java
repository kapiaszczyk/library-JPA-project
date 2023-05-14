package dev.kapiaszczyk.bookstore.library.cityCode;

import jakarta.persistence.*;

@Entity
@Table(name = "city_code")
public class CityCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_code_id")
    private String cityCodeId;


    // Relation with City
    @Column(name = "city_id")
    private String cityId;

    public CityCode() {
    }

    public String getCityCodeId() {
        return cityCodeId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

}
