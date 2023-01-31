package hotelbooking.models.inputs;

import java.sql.Date;

public class ReservationInput {
    private Integer roomId;
    private String userEmail;
    private Date startDate;
    private Date endDate;

    public ReservationInput(Integer roomId, String userEmail, Date startDate, Date endDate) {
        this.roomId = roomId;
        this.userEmail = userEmail;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ReservationInput() {
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
