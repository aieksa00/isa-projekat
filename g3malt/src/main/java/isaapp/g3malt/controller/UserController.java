package isaapp.g3malt.controller;

import isaapp.g3malt.dto.NewUserDTO;
import isaapp.g3malt.dto.UserDTO;
import isaapp.g3malt.model.GenderType;
import isaapp.g3malt.model.MedicalStaff;
import isaapp.g3malt.model.User;
import isaapp.g3malt.model.UserType;
import isaapp.g3malt.dto.UserInfoDto;
import isaapp.g3malt.model.Customer;
import isaapp.g3malt.model.UserCredentials;
import isaapp.g3malt.services.UserCredentialsService;
import isaapp.g3malt.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/userController")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserCredentialsService userCredentialsService;
	
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
    	List<User> users = (List<User>) userService.findAll();
    	List<UserDTO> userDtos = new ArrayList<UserDTO>();
    	for(User u : users) {
    		UserDTO dto = new UserDTO(u);
    		userDtos.add(dto);
    	}
    		
		return new ResponseEntity<>(userDtos, HttpStatus.OK);
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
		user.setStreet(userDto.street);
		user.setCity(userDto.city);
		user.setCountry(userDto.country);
		user.setPhoneNumber(userDto.phoneNumber);

		user = userService.save(user);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/addStaff", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> addNewUser(@RequestBody UserDTO userDto) {
    	GenderType g = userDto.gender.equals("male")?GenderType.male:GenderType.female;
        UserType ut = new UserType(1, "STAFF");
        List<UserType> userTypes = new ArrayList<>();
        userTypes.add(ut);
    	MedicalStaff user = new MedicalStaff(null, userDto.name, userDto.surname, userDto.address, userDto.city, userDto.country, userDto.phoneNumber, userDto.jmbg, g, userDto.profession, userDto.workplace, userTypes,null);
        User newUser = userService.save(user);
        userDto.setUserId(newUser.getId());
        return new ResponseEntity<UserDTO>(userDto, HttpStatus.CREATED);
    }

    @PostMapping(value = "/addRegisteredUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> addRegisteredUser(@Valid @RequestBody UserDTO userDTO) {
        GenderType g = userDTO.gender.equals("male")?GenderType.male:GenderType.female;
        UserType ut = null;
        switch(userDTO.userType) {
            case 0: ut = new UserType(0, "ADMIN");break;
            case 1: ut = new UserType(1, "STAFF");break;
            case 2: ut = new UserType(2, "CUSTOMER");break;
        }
        List<UserType> userTypes = new ArrayList<>();
        userTypes.add(ut);
        User user = new User(null, userDTO.name, userDTO.surname, userDTO.address, userDTO.city, userDTO.country, userDTO.phoneNumber, userDTO.jmbg, g, userDTO.profession, userDTO.workplace, userTypes);
        User newUser = userService.save(user);
        userDTO.setUserId(newUser.getId());
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/deleteUser")
    public ResponseEntity deleteUser(@RequestBody int id) {
        userService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    
}
