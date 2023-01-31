package hotelbooking.services.services;

import hotelbooking.models.dto.HotelDto;

import java.util.List;

public interface HotelService {
    HotelDto createHotel (String hotelName, String cityName);
    HotelDto getHotel (Integer id);
    List<HotelDto> getHotels();
    void deleteHotel (Integer id);
    List<HotelDto> getHotelsByCity (List<String> cities);
    void giveHotelFacility (String hotelName, String facility);
    List<HotelDto> getHotelsByFacility (List<String> facilities);
}
