package hotelbooking.models.pojo;

public class Room {
    private Integer id;
    private Integer hotelId;
    private Integer capacity;
    private Double price;

    public Room(Integer id, Integer hotelId, Integer capacity, Double price) {
        this.id = id;
        this.hotelId = hotelId;
        this.capacity = capacity;
        this.price = price;
    }

    public Room() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
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
