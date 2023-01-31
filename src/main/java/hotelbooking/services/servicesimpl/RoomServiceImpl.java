package hotelbooking.services.servicesimpl;

import hotelbooking.models.dto.CityDto;
import hotelbooking.models.dto.HotelDto;
import hotelbooking.models.dto.RoomDto;
import hotelbooking.models.pojo.City;
import hotelbooking.models.pojo.Hotel;
import hotelbooking.models.pojo.Room;
import hotelbooking.repositories.repositories.CityRepository;
import hotelbooking.repositories.repositories.HotelRepository;
import hotelbooking.repositories.repositories.RoomRepository;
import hotelbooking.services.services.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoomDto createRoom(String hotelName, Integer capacity, Double price) {

        Hotel hotel = hotelRepository.getHotel(hotelName);
        HotelDto hotelDto = modelMapper.map(hotel, HotelDto.class);

        City city = cityRepository.getCity(hotel.getCityId());
        CityDto cityDto = modelMapper.map(city, CityDto.class);

        hotelDto.setCity(cityDto);

        Room room = roomRepository.createRoom(hotel.getId(), capacity, price);
        RoomDto roomDto = modelMapper.map(room, RoomDto.class);

        roomDto.setHotel(hotelDto);

        return roomDto;
    }

    @Override
    public RoomDto getRoom(Integer id) {

        Room room = roomRepository.getRoom(id);
        RoomDto roomDto = modelMapper.map(room, RoomDto.class);

        Hotel hotel = hotelRepository.getHotel(room.getHotelId());
        HotelDto hotelDto = modelMapper.map(hotel, HotelDto.class);

        City city = cityRepository.getCity(hotel.getCityId());
        CityDto cityDto = modelMapper.map(city, CityDto.class);

        hotelDto.setCity(cityDto);
        roomDto.setHotel(hotelDto);

        return roomDto;
    }

    @Override
    public List<RoomDto> getRooms() {

        List<City> cityList = cityRepository.getCities();
        List<Hotel> hotelList = hotelRepository.getHotels();
        List<Room> roomList = roomRepository.getRooms();
        List<RoomDto> rooms = new ArrayList<>();

        for (Room r : roomList) {

            RoomDto roomDto = modelMapper.map(r, RoomDto.class);

            for (Hotel hotel : hotelList) {

                if (hotel.getId().equals(r.getHotelId())) {

                    HotelDto hotelDto = modelMapper.map(hotel, HotelDto.class);

                    cityList
                            .stream()
                            .filter(c -> c.getId().equals(hotel.getCityId()))
                            .findFirst()
                            .ifPresent(c -> hotelDto.setCity(modelMapper.map(c, CityDto.class)));

                    roomDto.setHotel(hotelDto);
                    break;
                }
            }

            rooms.add(roomDto);
        }

        return rooms;
    }

    @Override
    public void deleteRoom(Integer id) {

        roomRepository.deleteRoom(id);
    }

    @Override
    public List<RoomDto> getRoomsByHotel(String hotelName) {

        Hotel hotel = hotelRepository.getHotel(hotelName);
        HotelDto hotelDto = modelMapper.map(hotel, HotelDto.class);
        hotelDto.setCity(modelMapper.map(cityRepository.getCity(hotel.getCityId()), CityDto.class));

        List<Room> roomList = roomRepository.getRoomsByHotel(Collections.singletonList(hotel.getId()));


        List<RoomDto> rooms = roomList
                .stream()
                .map(r -> modelMapper.map(r, RoomDto.class))
                .collect(Collectors.toList());

        rooms.forEach(r -> r.setHotel(hotelDto));

        return rooms;
    }
}
