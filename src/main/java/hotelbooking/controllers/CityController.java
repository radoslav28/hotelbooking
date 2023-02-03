package hotelbooking.controllers;

import hotelbooking.config.jwt.UserTokenIdManager;
import hotelbooking.exceptions.AccessDeniedException;
import hotelbooking.models.dto.CityDto;
import hotelbooking.models.inputs.CityInput;
import hotelbooking.services.services.CityService;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;
    @Autowired
    private UserTokenIdManager userTokenIdManager;

    @PostMapping
    public CityDto createCity(@RequestBody CityInput cityInput, @RequestHeader("Authorization") String token) throws ParseException {

        String userRole = userTokenIdManager.getRoleFromToken(token);

        if (!userRole.equals("ADMIN")){
            throw new AccessDeniedException();
        }

        return cityService.createCity(cityInput);
    }

    @GetMapping("/{id}")
    public CityDto getCity(@PathVariable Integer id) {

        return cityService.getCity(id);
    }

    @GetMapping
    public List<CityDto> getCities(){

        return cityService.getCities();
    }

    @DeleteMapping
    public void deleteCity(@RequestParam Integer id ,@RequestHeader("Authorization") String token) throws ParseException {

        String userRole = userTokenIdManager.getRoleFromToken(token);

        if  (!userRole.equals("ADMIN")){
            throw new AccessDeniedException();
        }

        cityService.deleteCity(id);
    }
}
