package hotelbooking.services.servicesimpl;

import hotelbooking.repositories.repositories.CityRepository;
import hotelbooking.repositories.repositories.CountryRepository;
import hotelbooking.services.services.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String createCountry(String country) {

        return countryRepository.createCountry(country).getCountry();
    }

    @Override
    public List<String> getCountries() {

        return countryRepository.getCountries()
                .stream()
                .map(country -> modelMapper.map(country.getCountry(), String.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCountry(String country) {

        cityRepository.deleteCities(country);
        countryRepository.deleteCountry(country);
    }
}
