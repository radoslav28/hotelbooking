package hotelbooking.repositories.repositories;

import hotelbooking.models.pojo.Facility;

import java.util.List;

public interface FacilityRepository {
    Facility createFacility(String facility);
    List<Facility> getFacilities();
    void deleteFacility(String facility);
}
