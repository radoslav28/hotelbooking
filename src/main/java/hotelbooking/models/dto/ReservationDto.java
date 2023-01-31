package hotelbooking.models.dto;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

public class ReservationDto {
    private UserDto user;
    private RoomDto room;
    private Date startDate;
    private Date endDate;
    private Double fullPrice;

    public ReservationDto(UserDto user,RoomDto room, Date startDate, Date endDate) {
        this.user = user;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fullPrice = room.getPrice()* TimeUnit.DAYS.convert(Math.abs(endDate.getTime() - startDate.getTime()), TimeUnit.MILLISECONDS) ;
    }

    public ReservationDto() {
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public RoomDto getRoom() {
        return room;
    }

    public void setRoom(RoomDto room) {
        this.room = room;
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
