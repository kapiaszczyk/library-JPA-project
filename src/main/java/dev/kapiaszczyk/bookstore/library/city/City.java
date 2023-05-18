package dev.kapiaszczyk.bookstore.library.city;

import dev.kapiaszczyk.bookstore.library.address.Address;
import dev.kapiaszczyk.bookstore.library.cityCode.CityCode;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long id;

    @Column(name = "city_name")
    private String cityName;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CityCode> cityCodes = new ArrayList<>();


    public City() {
    }

    public City(String cityName) {
        this.cityName = cityName;
    }

    public Long getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public City(String cityName, List<CityCode> cityCodes, List<Address> addresses) {
        this.cityName = cityName;
        this.cityCodes = cityCodes;
        this.addresses = addresses;
    }


    public List<CityCode> getCityCode() {
        return cityCodes;
    }

    public void setCityCode(List<CityCode> cityCodes) {
        this.cityCodes = cityCodes;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }


    public void removeAccount(String cracow) {
        this.cityName = cracow;
    }

    public void setCityName(String cracow) {
        this.cityName = cracow;
    }

    public void getCityName(String cracow) {
        this.cityName = cracow;
    }

    public void addCityCode(CityCode cityCode) {
        cityCodes.add(cityCode);
        cityCode.setCity(this);
    }

    public void removeCityCode(CityCode cityCode) {
        cityCodes.remove(cityCode);
        cityCode.setCity(null);
    }
}
