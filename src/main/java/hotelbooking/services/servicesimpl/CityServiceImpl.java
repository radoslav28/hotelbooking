package hotelbooking.services.servicesimpl;

import hotelbooking.models.dto.CityDto;
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
    public CityDto createCity(String cityName, String country) {

        CityDto city = modelMapper.map(cityRepository.createCity(cityName, country), CityDto.class);

        return city;
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
