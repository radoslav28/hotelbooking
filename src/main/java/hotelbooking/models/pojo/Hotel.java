package hotelbooking.models.pojo;

public class Hotel {
    private Integer id;
    private String hotelName;
    private Integer cityId;

    public Hotel(Integer id, String hotelName, Integer cityId) {
        this.id = id;
        this.hotelName = hotelName;
        this.cityId = cityId;
    }

    public Hotel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
