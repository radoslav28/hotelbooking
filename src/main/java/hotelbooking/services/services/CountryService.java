package hotelbooking.services.services;

import java.util.List;

public interface CountryService {
    String createCountry(String country);
    List<String> getCountries ();
    void deleteCountry (String country);
}
