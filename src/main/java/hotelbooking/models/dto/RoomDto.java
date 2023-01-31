package hotelbooking.models.dto;

public class RoomDto {
    private HotelDto hotel;
    private Integer capacity;
    private Double price;

    public RoomDto(HotelDto hotel, Integer capacity, Double price) {
        this.hotel = hotel;
        this.capacity = capacity;
        this.price = price;
    }

    public RoomDto() {
    }

    public HotelDto getHotel() {
        return hotel;
    }

    public void setHotel(HotelDto hotel) {
        this.hotel = hotel;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
