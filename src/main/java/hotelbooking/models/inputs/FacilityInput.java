package hotelbooking.models.inputs;

public class FacilityInput {
    private String facility;

    public FacilityInput(String facility) {
        this.facility = facility;
    }

    public FacilityInput() {
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }
}
