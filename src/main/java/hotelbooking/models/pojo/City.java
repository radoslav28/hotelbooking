package hotelbooking.models.pojo;

public class City {
    private Integer id;
    private String cityName;
    private  String countryName;

    public City(Integer id, String cityName, String countryName) {
        this.id = id;
        this.cityName = cityName;
        this.countryName = countryName;
    }

    public City() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
