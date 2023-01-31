package hotelbooking.repositories.repositories;

import hotelbooking.models.pojo.User;

import java.util.List;

public interface UserRepository {
    User createUser(String email, String password,
                       String fName, String lName, String phone);
    User getUser(Integer id);
    List<User> getUsers();
    void deleteUser(Integer id);

    User getUser(String email);
}
