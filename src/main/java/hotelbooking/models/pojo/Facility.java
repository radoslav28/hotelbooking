package hotelbooking.models.pojo;

public class Facility {
    private String facility;

    public Facility(String facility) {
        this.facility = facility;
    }

    public Facility() {
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }
}
