package hotelbooking.services.servicesimpl;

import hotelbooking.models.dto.*;
import hotelbooking.models.pojo.*;
import hotelbooking.repositories.repositories.*;
import hotelbooking.services.services.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ReservationDto createReservation(Integer roomId, String email, Date startDate, Date endDate) {

        Room room = roomRepository.getRoom(roomId);
        RoomDto roomDto = modelMapper.map(room, RoomDto.class);

        Hotel hotel = hotelRepository.getHotel(room.getHotelId());
        HotelDto hotelDto = modelMapper.map(hotel, HotelDto.class);

        City city = cityRepository.getCity(hotel.getCityId());
        CityDto cityDto = modelMapper.map(city, CityDto.class);

        hotelDto.setCity(cityDto);
        roomDto.setHotel(hotelDto);

        User user = userRepository.getUser(email);
        UserDto userDto = modelMapper.map(user, UserDto.class);

        Reservation reservation = reservationRepository.createReservation(roomId, user.getId(), room.getPrice(), startDate, endDate);
        ReservationDto reservationDto = modelMapper.map(reservation,ReservationDto.class);

        reservationDto.setRoom(roomDto);
        reservationDto.setUser(userDto);


        return reservationDto;
    }

    @Override
    public ReservationDto getReservation(Integer id) {

        Reservation reservation = reservationRepository.getReservation(id);
        ReservationDto reservationDto = modelMapper.map(reservation, ReservationDto.class);

        Room room = roomRepository.getRoom(reservation.getRoomId());
        RoomDto roomDto = modelMapper.map(room, RoomDto.class);

        Hotel hotel = hotelRepository.getHotel(room.getHotelId());
        HotelDto hotelDto = modelMapper.map(hotel, HotelDto.class);

        City city = cityRepository.getCity(hotel.getCityId());
        CityDto cityDto = modelMapper.map(city, CityDto.class);

        hotelDto.setCity(cityDto);
        roomDto.setHotel(hotelDto);
        reservationDto.setRoom(roomDto);

        User user = userRepository.getUser(reservation.getUserId());
        UserDto userDto = modelMapper.map(user, UserDto.class);

        reservationDto.setUser(userDto);

        return reservationDto;
    }

    @Override
    public List<ReservationDto> getReservations() {

        List<User> userList = userRepository.getUsers();
        List<City> cityList = cityRepository.getCities();
        List<Hotel> hotelList = hotelRepository.getHotels();
        List<Room> roomList = roomRepository.getRooms();
        List<Reservation> reservationList = reservationRepository.getReservations();
        List<ReservationDto> reservations = new ArrayList<>();

        for (Reservation re : reservationList) {
            ReservationDto reservationDto = modelMapper.map(re, ReservationDto.class);

            for (Room r : roomList) {
                RoomDto roomDto = modelMapper.map(r, RoomDto.class);

                if (r.getId().equals(re.getRoomId())) {

                    for (Hotel h : hotelList) {
                        HotelDto hotelDto = modelMapper.map(h, HotelDto.class);

                        if (h.getId().equals(r.getHotelId())) {

                            for (City c : cityList) {
                                CityDto cityDto = modelMapper.map(c, CityDto.class);

                                if (c.getId().equals(h.getCityId())) {
                                    hotelDto.setCity(cityDto);
                                    break;
                                }
                            }

                            roomDto.setHotel(hotelDto);
                            break;
                        }
                    }

                    reservationDto.setRoom(roomDto);
                    break;
                }
            }

            for (User u : userList) {
                UserDto userDto = modelMapper.map(u, UserDto.class);

                if (u.getId().equals(re.getUserId())) {
                    reservationDto.setUser(userDto);
                    break;
                }
            }
            reservations.add(reservationDto);
        }

        return reservations;
    }

    @Override
    public void deleteReservation(Integer id) {

        reservationRepository.deleteReservation(id);
    }
}
