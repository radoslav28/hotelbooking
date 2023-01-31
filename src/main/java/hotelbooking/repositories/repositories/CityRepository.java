package hotelbooking.repositories.repositories;

import hotelbooking.models.pojo.City;

import java.util.List;

public interface CityRepository {
    City createCity(String cityName, String countryName);
    City getCity(Integer id);
    City getCity(String cityName);
    List<City> getCities();
    List<City> getCities(List<String> cities);
    List<City> getCitiesByCountry(List<String> countries);
    void deleteCity(Integer id);
    void deleteCities(String country);
}
