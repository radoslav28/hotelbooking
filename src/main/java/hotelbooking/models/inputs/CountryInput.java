package hotelbooking.models.inputs;

public class CountryInput {
    private String country;

    public CountryInput() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CountryInput(String country) {
        this.country = country;
    }
}
