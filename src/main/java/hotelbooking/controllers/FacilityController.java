package hotelbooking.controllers;

import hotelbooking.config.jwt.UserTokenIdManager;
import hotelbooking.exceptions.AccessDeniedException;
import hotelbooking.models.inputs.FacilityInput;
import hotelbooking.services.services.FacilityService;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facilities")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;
    @Autowired
    private UserTokenIdManager userTokenIdManager;

    @PostMapping
    public String createFacility(@RequestBody FacilityInput facility, @RequestHeader("Authorization") String token) throws ParseException {

        String userRole = userTokenIdManager.getRoleFromToken(token);

        if (!userRole.equals("ADMIN")){
            throw new AccessDeniedException();
        }

        return facilityService.createFacility(facility.getFacility());
    }

    @GetMapping()
    public List<String> getFacilities(){

        return facilityService.getFacilities();
    }

    @DeleteMapping
    public void deleteFacility (@RequestParam String facility, @RequestHeader("Authorization") String token) throws ParseException {

        String userRole = userTokenIdManager.getRoleFromToken(token);

        if (!userRole.equals("ADMIN")){
            throw new AccessDeniedException();
        }

        facilityService.deleteFacility(facility);
    }

}
