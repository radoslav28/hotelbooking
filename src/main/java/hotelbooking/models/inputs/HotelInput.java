package hotelbooking.models.inputs;

public class HotelInput {

    private String hotelName;
    private String cityName;

    public HotelInput(String hotelName, String cityName) {
        this.hotelName = hotelName;
        this.cityName = cityName;
    }

    public HotelInput() {
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
