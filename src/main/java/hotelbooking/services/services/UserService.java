package hotelbooking.services.services;

import hotelbooking.models.dto.UserDto;
import hotelbooking.models.inputs.UserInput;

import java.util.List;

public interface UserService {
    UserDto createUser(UserInput userInput);
    UserDto getUser(Integer id);
    List<UserDto> getUsers();
    void deleteUser(Integer id);
    UserDto getUser(String email);
}
