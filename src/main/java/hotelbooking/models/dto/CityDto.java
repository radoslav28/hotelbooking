package hotelbooking.models.dto;

public class CityDto {
    private String cityName;
    private String countryName;

    public CityDto(String cityName, String countryName) {
        this.cityName = cityName;
        this.countryName = countryName;
    }

    public CityDto() {
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
