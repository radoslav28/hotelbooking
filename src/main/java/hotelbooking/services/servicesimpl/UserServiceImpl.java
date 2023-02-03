package hotelbooking.services.servicesimpl;

import hotelbooking.models.dto.UserDto;
import hotelbooking.models.inputs.UserInput;
import hotelbooking.models.pojo.User;
import hotelbooking.repositories.repositories.UserRepository;
import hotelbooking.services.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserInput userInput) {

        User user = modelMapper.map(userInput, User.class);

        UserDto userDto = modelMapper.map(userRepository.createUser(user), UserDto.class);

        return userDto;
    }

    @Override
    public UserDto getUser(Integer id) {

        UserDto user = modelMapper.map(userRepository.getUser(id), UserDto.class);

        return user;
    }

    @Override
    public List<UserDto> getUsers() {

        return userRepository.getUsers()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer id) {

        userRepository.deleteUser(id);
    }

    @Override
    public UserDto getUser(String email) {

        UserDto user = modelMapper.map(userRepository.getUser(email), UserDto.class);

        return user;
    }
}
