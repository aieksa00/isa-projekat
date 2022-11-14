package isaapp.g3malt.controller;

import isaapp.g3malt.model.User;
import isaapp.g3malt.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/userController")
public class UserController {
	
	@Autowired
	private UserService userService;

    @GetMapping(value = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {
    	List<User> users = (List<User>) userService.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser() {
        User user = userService.findById(1);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getCustomer() {
        User user = userService.findById(1);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        User newUser = userService.save(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/deleteUser")
    public ResponseEntity deleteUser(@RequestBody int id) {
        userService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
