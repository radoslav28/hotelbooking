package hotelbooking.controllers;

import hotelbooking.config.MyUserSec;
import hotelbooking.config.PasswordHash;
import hotelbooking.config.authentication.AuthenticationRequest;
import hotelbooking.config.authentication.AuthenticationResponse;
import hotelbooking.config.jwt.JwtUtil;
import hotelbooking.config.jwt.UserTokenIdManager;
import hotelbooking.exceptions.AccessDeniedException;
import hotelbooking.models.dto.UserDto;
import hotelbooking.models.inputs.UserInput;
import hotelbooking.services.services.UserService;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordHash passwordHash;
    @Autowired
    private UserTokenIdManager userTokenIdManager;


    @PostMapping("/register")
    public UserDto createUser(@RequestBody UserInput userInput) {

        userInput.setPassword(passwordHash.doHashing(userInput.getPassword()));
        return userService.createUser(userInput.getEmail(), userInput.getPassword(), userInput.getFirstName(), userInput.getLastName(), userInput.getPhone());
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                            passwordHash.doHashing(authenticationRequest.getPassword())));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect email or password", e);
        }

        final MyUserSec userDetails = (MyUserSec) userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt).getJwt());
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Integer id, @RequestHeader("Authorization") String token) throws ParseException {

        String userRole = userTokenIdManager.getRoleFromToken(token);

        if (!userRole.equals("ADMIN")){
            throw new AccessDeniedException();
        }

        return userService.getUser(id);
    }

    @GetMapping
    public List<UserDto> getUsers(@RequestHeader("Authorization") String token) throws ParseException {

        String userRole = userTokenIdManager.getRoleFromToken(token);

        if (!userRole.equals("ADMIN")){
            throw new AccessDeniedException();
        }

        return userService.getUsers();
    }

    @DeleteMapping
    public void deleteUser(@RequestParam Integer id ,@RequestHeader("Authorization") String token) throws ParseException {

        String userRole = userTokenIdManager.getRoleFromToken(token);

        if (!userRole.equals("ADMIN")){
            throw new AccessDeniedException();
        }

        userService.deleteUser(id);
    }

}
