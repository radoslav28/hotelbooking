package hotelbooking.models.dto;

public class HotelDto {
    private String hotelName;
    private CityDto city;

    public HotelDto(String hotelName, CityDto city) {
        this.hotelName = hotelName;
        this.city = city;
    }

    public HotelDto() {
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public CityDto getCity() {
        return city;
    }

    public void setCity(CityDto city) {
        this.city = city;
    }
}
