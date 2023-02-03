package hotelbooking.services.servicesimpl;

import hotelbooking.models.dto.CityDto;
import hotelbooking.models.inputs.CityInput;
import hotelbooking.models.pojo.City;
import hotelbooking.repositories.repositories.CityRepository;
import hotelbooking.services.services.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CityDto createCity(CityInput cityInput) {

        City city = modelMapper.map(cityInput, City.class);

        return modelMapper.map(cityRepository.createCity(city), CityDto.class);
    }

    @Override
    public CityDto getCity(Integer id) {

        CityDto city = modelMapper.map(cityRepository.getCity(id), CityDto.class);

        return city;
    }

    @Override
    public CityDto getCity(String cityName) {

        CityDto city = modelMapper.map(cityRepository.getCity(cityName), CityDto.class);

        return city;
    }

    @Override
    public List<CityDto> getCities() {

        return cityRepository.getCities()
                .stream()
                .map(city -> modelMapper.map(city, CityDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CityDto> getCitiesByCountry(List<String> countries) {

        return cityRepository.getCitiesByCountry(countries)
                .stream()
                .map(city -> modelMapper.map(city, CityDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCity(Integer id) {

        cityRepository.deleteCity(id);
    }

    @Override
    public void deleteCities(String country) {

        cityRepository.deleteCities(country);
    }
}
