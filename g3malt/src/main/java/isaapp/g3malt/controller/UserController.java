package isaapp.g3malt.controller;

import isaapp.g3malt.model.User;
import isaapp.g3malt.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/userController")
public class UserController {

    @GetMapping(value = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<User>> getAllUsers() {
        UserService userService = new UserService();
        ArrayList<User> userList = userService.getAll();
        return new ResponseEntity<ArrayList<User>>(userList, HttpStatus.OK);
    }

    @GetMapping(value = "/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@RequestBody String id) {
        UserService userService = new UserService();
        User user = userService.getById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        UserService userService = new UserService();
        User newUser = userService.create(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/deleteUser")
    public ResponseEntity deleteUser(@RequestBody String id) {
        UserService userService = new UserService();
        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
