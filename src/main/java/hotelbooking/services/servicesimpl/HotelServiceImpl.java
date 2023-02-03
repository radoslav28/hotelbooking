package hotelbooking.services.servicesimpl;

import hotelbooking.models.dto.CityDto;
import hotelbooking.models.dto.HotelDto;
import hotelbooking.models.inputs.HotelInput;
import hotelbooking.models.pojo.City;
import hotelbooking.models.pojo.Hotel;
import hotelbooking.repositories.repositories.CityRepository;
import hotelbooking.repositories.repositories.HotelRepository;
import hotelbooking.services.services.HotelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public HotelDto createHotel(HotelInput hotelInput) {

        City city = cityRepository.getCity(hotelInput.getCityName());
        CityDto cityDto = modelMapper.map(city, CityDto.class);

        Hotel hotel = modelMapper.map(hotelInput, Hotel.class);
        hotel.setCityId(city.getId());

        HotelDto hotelDto = modelMapper.map(hotelRepository.createHotel(hotel), HotelDto.class);
        hotelDto.setCity(cityDto);

        return hotelDto;
    }

    @Override
    public HotelDto getHotel(Integer id) {

        Hotel hotel = hotelRepository.getHotel(id);
        HotelDto hotelDto = modelMapper.map(hotel, HotelDto.class);

        City city = cityRepository.getCity(hotel.getCityId());
        CityDto cityDto = modelMapper.map(city, CityDto.class);

        hotelDto.setCity(cityDto);

        return hotelDto;
    }

    @Override
    public List<HotelDto> getHotels() {

        List<City> cityList = cityRepository.getCities();
        List<Hotel> hotelList = hotelRepository.getHotels();
        List<HotelDto> hotels = new ArrayList<>();

        for (Hotel h : hotelList) {

            HotelDto hotelDto = modelMapper.map(h, HotelDto.class);

            cityList
                    .stream()
                    .filter(c -> c.getId().equals(h.getCityId()))
                    .findFirst()
                    .ifPresent(c -> hotelDto.setCity(modelMapper.map(c, CityDto.class)));

            hotels.add(hotelDto);
        }

        return hotels;
    }

    @Override
    public void deleteHotel(Integer id) {

        hotelRepository.deleteHotel(id);
    }

    @Override
    public List<HotelDto> getHotelsByCity(List<String> cities) {

        List<City> cityList = cityRepository.getCities(cities);
        List<Integer> cityIds = cityList
                .stream()
                .map(City::getId)
                .collect(Collectors.toList());

        List<Hotel> hotelList = hotelRepository.getHotelsByCity(cityIds);
        List<HotelDto> hotels = new ArrayList<>();

        for (Hotel h : hotelList) {

            HotelDto hotelDto = modelMapper.map(h, HotelDto.class);

            cityList
                    .stream()
                    .filter(c -> c.getId().equals(h.getCityId()))
                    .findFirst()
                    .ifPresent(c -> hotelDto.setCity(modelMapper.map(c, CityDto.class)));

            hotels.add(hotelDto);
        }

        return hotels;
    }

    @Override
    public void giveHotelFacility(String hotelName, String facility) {

        hotelRepository.giveHotelFacility(hotelRepository.getHotel(hotelName).getId(), facility);
    }

    @Override
    public List<HotelDto> getHotelsByFacility(List<String> facilities) {

        List<City> cityList = cityRepository.getCities();
        List<Hotel> hotelList = hotelRepository.getHotelsByFacility(facilities);

        Set<Integer> ids = new HashSet<>();
        for (int i = 0; i < hotelList.size(); i++) {
            Hotel h = hotelList.get(i);
            if (!ids.contains(h.getId())) {
                ids.add(h.getId());
            } else {
                hotelList.remove(h);
            }

        }

        List<HotelDto> hotels = new ArrayList<>();

        for (Hotel h : hotelList) {

            HotelDto hotelDto = modelMapper.map(h, HotelDto.class);

            cityList
                    .stream()
                    .filter(c -> c.getId().equals(h.getCityId()))
                    .findFirst()
                    .ifPresent(c -> hotelDto.setCity(modelMapper.map(c, CityDto.class)));

            hotels.add(hotelDto);
        }

        return hotels;
    }
}
