package hotelbooking.models.inputs;

public class CityInput {
    private String cityName;
    private String countryName;

    public CityInput(String cityName, String countryName) {
        this.cityName = cityName;
        this.countryName = countryName;
    }

    public CityInput() {
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
