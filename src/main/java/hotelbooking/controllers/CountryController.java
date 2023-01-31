package hotelbooking.controllers;

import hotelbooking.config.jwt.UserTokenIdManager;
import hotelbooking.exceptions.AccessDeniedException;
import hotelbooking.models.inputs.CountryInput;
import hotelbooking.services.services.CountryService;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private UserTokenIdManager userTokenIdManager;

    @PostMapping
    public String createCountry(@RequestBody CountryInput country, @RequestHeader("Authorization") String token) throws ParseException {

        String userRole = userTokenIdManager.getRoleFromToken(token);

        if(!userRole.equals("ADMIN")){
            throw new AccessDeniedException();
        }

        return countryService.createCountry(country.getCountry());
    }

    @GetMapping
    public List<String> getCountries(){

        return countryService.getCountries();
    }

    @DeleteMapping
    public void deleteCountry(@RequestParam String country, @RequestHeader("Authorization") String token) throws ParseException{

        String userRole = userTokenIdManager.getRoleFromToken(token);

        if (!userRole.equals("ADMIN")){
            throw new AccessDeniedException();
        }

        countryService.deleteCountry(country);
    }



}
