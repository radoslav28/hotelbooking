package hotelbooking.services.services;

import hotelbooking.models.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(String email, String password, String fName, String lName, String phone);
    UserDto getUser(Integer id);
    List<UserDto> getUsers();
    void deleteUser(Integer id);
    UserDto getUser(String email);
}
