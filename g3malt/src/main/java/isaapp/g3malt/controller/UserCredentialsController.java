package isaapp.g3malt.controller;

import isaapp.g3malt.dto.AllUserInfoDto;
import isaapp.g3malt.dto.UpdateUserDTO;
import isaapp.g3malt.dto.UserCredentialsDTO;
import isaapp.g3malt.model.GenderType;
import isaapp.g3malt.model.User;
import isaapp.g3malt.model.UserCredentials;
import isaapp.g3malt.model.UserType;
import isaapp.g3malt.repository.UserCredentialsRepository;
import isaapp.g3malt.repository.UserRepository;
import isaapp.g3malt.services.UserCredentialsService;
import isaapp.g3malt.services.UserService;

import java.util.ArrayList;
import java.util.List;

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

@CrossOrigin("*")
@RestController
@RequestMapping("/UserCredentialsController")
public class UserCredentialsController {
		
	@Autowired
	private UserCredentialsService userCredentialsService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping(value = "/getAllUsersCredentials", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserCredentials>> getAllUsers() {
		List<UserCredentials> usersCredentials = (List<UserCredentials>) userCredentialsService.findAll();
		return new ResponseEntity<>(usersCredentials, HttpStatus.OK);
	}
	
	@GetMapping(value = "/GetUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllUserInfoDto> getUserById(@PathVariable Integer id) {
        UserCredentials userCredentials = userCredentialsService.findById(id);
        if (userCredentials == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
        
        AllUserInfoDto allUserInfoDto = mapAllUserInfoDto(userCredentials);
        return new ResponseEntity<AllUserInfoDto>(allUserInfoDto, HttpStatus.OK);
    }
	
	@PutMapping(value = "UpdateUser/{id}", consumes = "application/json")
	public ResponseEntity<AllUserInfoDto> updateUser(@PathVariable Integer id, @RequestBody AllUserInfoDto dto) {

		UserCredentials userCredentials = userCredentialsService.findById(id);
        
		if (userCredentials == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
        
        User user = userCredentials.getUser();
		
		user.setName(dto.getUserName());
		user.setSurname(dto.getUserSurname());
		user.setStreet(dto.getUserStreet());
		user.setCity(dto.getUserCity());
		user.setCountry(dto.getUserCountry());
		user.setPhoneNumber(dto.getUserPhoneNumber());
		user.setJmbg(dto.getUserJmbg());
		if(dto.getUserGender()==0)
			user.setGender(GenderType.male);
		else
			user.setGender(GenderType.female);
		user.setProfession(dto.getUserProfession());
		user.setWorkplace(dto.getUserWorkplace());
		
		userCredentials.setEmail(dto.getUserCredentialsEmail());
		userCredentials.setPassword(dto.getUserCredentialsPassword());
		userCredentials.setUser(user);

		userCredentials = userCredentialsService.save(userCredentials);
		
		AllUserInfoDto allUserInfoDto = mapAllUserInfoDto(userCredentials);
		
		return new ResponseEntity<>(allUserInfoDto, HttpStatus.OK);
	}
	
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/addUserCredentials", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserCredentials> addNewUser(@RequestBody UserCredentialsDTO userCredentialsDto) {
    	User u = userService.findById(userCredentialsDto.userId);
    	UserCredentials uc = new UserCredentials(null,userCredentialsDto.email,userCredentialsDto.password,u);
		UserCredentials newUserCredentials = userCredentialsService.save(uc);
		return new ResponseEntity<UserCredentials>(newUserCredentials, HttpStatus.CREATED);
	}

	@PostMapping(value = "/registreUserCredentials", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserCredentials> addNewUserCredentials(@Valid @RequestBody UserCredentialsDTO userCredentialsDTO) {
		UserCredentials uc = new UserCredentials(null, userCredentialsDTO.email, userCredentialsDTO.password, null);
		boolean notFounded = userCredentialsService.getByEmail(userCredentialsDTO.email);

		if(notFounded) {
			UserCredentials newUserCredentials = userCredentialsService.save(uc);
			return new ResponseEntity<UserCredentials>(newUserCredentials, HttpStatus.CREATED);
		}

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredentialsDTO.getEmail(), userCredentialsDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		//TODO token

		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	@PutMapping(value="/updateUserCredentials", consumes = "application/json")
	public ResponseEntity<UserCredentials> updateUserCredentials(@RequestBody UpdateUserDTO user) {
		UserCredentials userCredentials = userCredentialsService.findById(user.userId);
		GenderType g = user.getUser().gender.equals("male")?GenderType.male:GenderType.female;
		UserType ut = null;
		switch(user.getUser().userType) {
			case 0: ut = new UserType(0, "ADMIN");break;
			case 1: ut = new UserType(1, "STAFF");break;
			case 2: ut = new UserType(2, "CUSTOMER");break;
		}
		User newUser = new User(null, user.getUser().name, user.getUser().surname, user.getUser().address, user.getUser().city, user.getUser().country, user.getUser().phoneNumber, user.getUser().jmbg, g, user.getUser().profession, user.getUser().workplace, ut);
		if(userCredentials == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		userCredentials.setUser(newUser);
		userCredentialsService.save(userCredentials);
		return new ResponseEntity<>(userCredentials, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllUserCredentials", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserCredentialsDTO>> getAllUserCredentials() {
    	List<UserCredentials> userCredentials = (List<UserCredentials>) userCredentialsService.findAll();
    	List<UserCredentialsDTO> dtos = new ArrayList<UserCredentialsDTO>();
    	for(UserCredentials uc : userCredentials){
    		dtos.add(new UserCredentialsDTO(uc.getEmail(),uc.getPassword(),uc.getUser().getId()));
    	}
		return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
	
	private AllUserInfoDto mapAllUserInfoDto(UserCredentials userCredentials) {
		User user = userCredentials.getUser();
		AllUserInfoDto allUserInfoDto = new AllUserInfoDto(
        		user.getId(),
        		user.getName(),
        		user.getSurname(),
        		user.getStreet(),
        		user.getCity(),
        		user.getCountry(),
        		user.getPhoneNumber(),
        		user.getJmbg(),
        		user.getGender().getValue(),
        		user.getProfession(),
        		user.getWorkplace(),
        		userCredentials.getEmail(),
        		userCredentials.getPassword()
        		);
		return allUserInfoDto;
	}
}
