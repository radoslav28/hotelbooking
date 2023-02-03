package hotelbooking.repositories.repositories;

import hotelbooking.models.pojo.Hotel;

import java.util.List;

public interface HotelRepository {
    Hotel createHotel(Hotel hotel);
    Hotel getHotel(Integer id);
    Hotel getHotel(String hotelName);
    List<Hotel> getHotels();
    void deleteHotel(Integer id);
    List<Hotel> getHotelsByCity (List<Integer> cityIds);
    void giveHotelFacility (Integer hotelId ,String facility);
    List<Hotel> getHotelsByFacility (List<String> facilities);
}
