package hotelbooking.services.services;

import hotelbooking.models.dto.CityDto;
import hotelbooking.models.inputs.CityInput;

import java.util.List;

public interface CityService {
    CityDto createCity(CityInput cityInput);
    CityDto getCity(Integer id);
    CityDto getCity(String cityName);
    List<CityDto> getCities();
    List<CityDto> getCitiesByCountry(List<String> countries);
    void deleteCity(Integer id);
    void deleteCities(String country);

}

