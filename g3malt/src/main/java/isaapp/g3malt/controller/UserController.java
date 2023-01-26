package isaapp.g3malt.controller;

import isaapp.g3malt.dto.NewUserDTO;
import isaapp.g3malt.dto.PenaltyPointDto;
import isaapp.g3malt.dto.UserDTO;
import isaapp.g3malt.model.GenderType;
import isaapp.g3malt.model.MedicalStaff;
import isaapp.g3malt.model.LoyaltyType;
import isaapp.g3malt.model.User;
import isaapp.g3malt.model.UserType;
import isaapp.g3malt.dto.UserInfoDto;
import isaapp.g3malt.model.BloodBank;
import isaapp.g3malt.model.Customer;
import isaapp.g3malt.model.UserCredentials;
import isaapp.g3malt.services.BloodBankService;
import isaapp.g3malt.services.CustomerService;
import isaapp.g3malt.services.UserCredentialsService;
import isaapp.g3malt.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private CustomerService customerService;
	
	@Autowired
	private UserCredentialsService userCredentialsService;
	
	@Autowired
	private BloodBankService bloodBankService;
	
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
    
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getFilteredUsers/{name}/{surname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getFilteredUsers(@PathVariable String name,@PathVariable String surname) {
    	if(name.equals("~"))
    		name="";
    	if(surname.equals("~"))
    		surname="";
    	List<User> users = (List<User>) userService.findFiltered(name,surname);
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
        Customer user = customerService.findById(id);
        
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
    @PostMapping(value = "/addStaff/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDTO> addNewUser(@PathVariable Integer id, @Valid @RequestBody UserDTO userDto) {
    	GenderType g = userDto.gender.equals("male")?GenderType.male:GenderType.female;
        UserType ut = new UserType(1, "STAFF");
        List<UserType> userTypes = new ArrayList<>();
        userTypes.add(ut);
    	MedicalStaff user = new MedicalStaff(null, userDto.name, userDto.surname, userDto.address, userDto.city, userDto.country, userDto.phoneNumber, userDto.jmbg, g, userDto.profession, userDto.workplace, userTypes,"",null);
        User newUser = userService.save(user);
        userDto.setUserId(newUser.getId());
        BloodBank b = bloodBankService.findById(id);
        b.getAllStaff().add(newUser);
        bloodBankService.save(b);
        return new ResponseEntity<UserDTO>(userDto, HttpStatus.CREATED);
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/addAdmin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDTO> addNewAdmin(@Valid @RequestBody UserDTO userDto) {
    	GenderType g = userDto.gender.equals("male")?GenderType.male:GenderType.female;
        UserType ut = new UserType(3, "ADMIN");
        List<UserType> userTypes = new ArrayList<>();
        userTypes.add(ut);
    	User user = new User(null, userDto.name, userDto.surname, userDto.address, userDto.city, userDto.country, userDto.phoneNumber, userDto.jmbg, g, userDto.profession, userDto.workplace, userTypes,"");
        User newUser = userService.save(user);
        userDto.setUserId(newUser.getId());
        return new ResponseEntity<UserDTO>(userDto, HttpStatus.OK);
    }

    @PostMapping(value = "/addRegisteredUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> addRegisteredUser(@Valid @RequestBody UserDTO userDTO) {
        GenderType g = userDTO.gender.equals("male")?GenderType.male:GenderType.female;
        UserType ut = null;
        switch(userDTO.userType) {
            case 3: ut = new UserType(3, "ADMIN");break;
            case 1: ut = new UserType(1, "STAFF");break;
            case 2: ut = new UserType(2, "CUSTOMER");break;
        }
        List<UserType> userTypes = new ArrayList<>();
        userTypes.add(ut);
        //User user = new User(null, userDTO.name, userDTO.surname, userDTO.address, userDTO.city, userDTO.country, userDTO.phoneNumber, userDTO.jmbg, g, userDTO.profession, userDTO.workplace, userTypes, "");
        //User newUser = userService.save(user);
        //userDTO.setUserId(newUser.getId());
        Customer customer = new Customer(null, userDTO.name, userDTO.surname, userDTO.address, userDTO.city, userDTO.country, userDTO.phoneNumber, userDTO.jmbg, g, userDTO.profession, userDTO.workplace, userTypes, "", 0, LoyaltyType.bronze, 0, null);
        User newCustomer = userService.save(customer);
        userDTO.setUserId(newCustomer.getId());
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/deleteUser")
    public ResponseEntity deleteUser(@RequestBody int id) {
        userService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @PostMapping(value = "/addPenaltyPoint", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('STAFF')")
    public ResponseEntity<User> AddPenaltyPoint(@RequestBody PenaltyPointDto dto) {
    	
    	Customer customer = (Customer)userService.findById(dto.getCustomerId());
    	
    	Integer penaltyPoints = customer.getPenalty() + dto.getPenaltyPoint();
    	customer.setPenalty(penaltyPoints);
    	
        Customer newCustomer = (Customer)userService.save(customer);
        
        return new ResponseEntity<User>(HttpStatus.OK);
    }
    
    
}
