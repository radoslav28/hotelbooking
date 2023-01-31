package hotelbooking.repositories.repositories;

import hotelbooking.models.pojo.Country;

import java.util.List;

public interface CountryRepository {
    Country createCountry(String country);
    List<Country> getCountries();
    void deleteCountry (String country);
}
