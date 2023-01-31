package hotelbooking.services.services;

import java.util.List;

public interface FacilityService {
    String createFacility(String facility);
    List<String> getFacilities();
    void deleteFacility (String facility);
}
