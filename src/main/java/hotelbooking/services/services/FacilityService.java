package hotelbooking.services.services;

import hotelbooking.models.inputs.FacilityInput;

import java.util.List;

public interface FacilityService {
    String createFacility(FacilityInput facilityInput);
    List<String> getFacilities();
    void deleteFacility (String facility);
}
