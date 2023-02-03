package hotelbooking.services.servicesimpl;

import hotelbooking.models.inputs.FacilityInput;
import hotelbooking.models.pojo.Facility;
import hotelbooking.repositories.repositories.FacilityRepository;
import hotelbooking.services.services.FacilityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String createFacility(FacilityInput facilityInput) {

        Facility facility = modelMapper.map(facilityInput, Facility.class);

        return facilityRepository.createFacility(facility).getFacility();
    }

    @Override
    public List<String> getFacilities() {
        return facilityRepository.getFacilities()
                .stream()
                .map(facility -> modelMapper.map(facility.getFacility(), String.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFacility(String facility) {

        facilityRepository.deleteFacility(facility);
    }
}
