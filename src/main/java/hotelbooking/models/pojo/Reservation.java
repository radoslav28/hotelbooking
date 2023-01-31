package hotelbooking.models.pojo;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
    private Integer id;
    private Integer roomId;
    private Integer userId;
    private Date startDate;
    private Date endDate;
    private Double fullPrice;

    public Reservation(Integer id, Integer roomId, Integer userId, Double roomPrice, Date startDate, Date endDate) {
        this.id = id;
        this.roomId = roomId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fullPrice = roomPrice * TimeUnit.DAYS.convert(Math.abs(endDate.getTime() - startDate.getTime()), TimeUnit.MILLISECONDS);
    }

    public Reservation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(Double fullPrice) {
        this.fullPrice = fullPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
