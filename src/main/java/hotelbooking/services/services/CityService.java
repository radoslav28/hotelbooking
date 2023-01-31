package hotelbooking.services.services;

import hotelbooking.models.dto.CityDto;

import java.util.List;

public interface CityService {
    CityDto createCity(String cityName, String country);
    CityDto getCity(Integer id);
    CityDto getCity(String cityName);
    List<CityDto> getCities();
    List<CityDto> getCitiesByCountry(List<String> countries);
    void deleteCity(Integer id);
    void deleteCities(String country);

}

