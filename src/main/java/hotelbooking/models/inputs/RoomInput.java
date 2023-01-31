package hotelbooking.models.inputs;

public class RoomInput {

    private String hotelName;
    private Integer capacity;
    private Double price;

    public RoomInput(String hotelName, Integer capacity, Double price) {
        this.hotelName = hotelName;
        this.capacity = capacity;
        this.price = price;
    }

    public RoomInput() {
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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
