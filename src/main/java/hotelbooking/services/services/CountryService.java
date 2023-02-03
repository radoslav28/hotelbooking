package hotelbooking.services.services;

import hotelbooking.models.inputs.CountryInput;

import java.util.List;

public interface CountryService {
    String createCountry(CountryInput countryInput);
    List<String> getCountries ();
    void deleteCountry (String country);
}
