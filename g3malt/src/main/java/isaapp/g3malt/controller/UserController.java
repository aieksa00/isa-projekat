package isaapp.g3malt.controller;

import isaapp.g3malt.dto.UserInfoDto;
import isaapp.g3malt.model.Customer;
import isaapp.g3malt.model.LoyaltyType;
import isaapp.g3malt.model.User;
import isaapp.g3malt.model.UserCredentials;
import isaapp.g3malt.services.UserCredentialsService;
import isaapp.g3malt.services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.persistence.Column;

@CrossOrigin("*")
@RestController
@RequestMapping("/userController")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserCredentialsService userCredentialsService;

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
    @GetMapping(value = "/getCustomer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfoDto> getCustomer(@PathVariable Integer id) {
        Customer user = (Customer) userService.findById(1);
        
        Iterable<UserCredentials> credentials = userCredentialsService.findAll();
        UserCredentials userCredetial = null;

        for(UserCredentials credential : credentials) {
        	if(credential.getUser().getId() == user.getId()) {
        		userCredetial = credential;
        		continue;
        	}
        }
        
        UserInfoDto userInfoDto = new UserInfoDto();
        
        ModelMapper modelMapper = new ModelMapper();
        userInfoDto = modelMapper.map(user, UserInfoDto.class);
        
        userInfoDto.password = userCredetial.getPassword();
        userInfoDto.email = userCredetial.getEmail();
        
        return new ResponseEntity<UserInfoDto>(userInfoDto, HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "*")
    @PutMapping(value = "/editCustomer/{id}", consumes = "application/json")
	public ResponseEntity<UserInfoDto> editCustomer(@PathVariable Integer id, @RequestBody UserInfoDto userDto) {

		User user = userService.findById(userDto.id);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Iterable<UserCredentials> credentials = userCredentialsService.findAll();
        UserCredentials userCredetial = null;

        for(UserCredentials credential : credentials) {
        	if(credential.getUser().getId() == user.getId()) {
        		userCredetial = credential;
        		continue;
        	}
        }
		
        userCredetial.setPassword(userDto.password);

		user.setName(userDto.name);
		user.setSurname(userDto.surname);
		user.setJmbg(userDto.jmbg);
		user.setGender(userDto.gender);
		user.setAddress(userDto.address);
		user.setCity(userDto.city);
		user.setCountry(userDto.country);
		user.setPhoneNumber(userDto.phoneNumber);

		user = userService.save(user);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
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
