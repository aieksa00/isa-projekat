package isaapp.g3malt.controller;

import isaapp.g3malt.config.WebSecurityConfig;
import isaapp.g3malt.dto.AllUserInfoDto;
import isaapp.g3malt.dto.UpdateUserDTO;
import isaapp.g3malt.dto.UserCredentialsDTO;
import isaapp.g3malt.dto.UserTokenStateDTO;
import isaapp.g3malt.model.GenderType;
import isaapp.g3malt.model.User;
import isaapp.g3malt.model.UserCredentials;
import isaapp.g3malt.model.UserType;
import isaapp.g3malt.services.EmailServiceImpl;
import isaapp.g3malt.services.UserCredentialsService;
import isaapp.g3malt.services.UserService;
import isaapp.g3malt.util.RandomString;


import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import isaapp.g3malt.util.EmailDetails;
import isaapp.g3malt.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/UserCredentialsController")
public class UserCredentialsController {
		
	@Autowired
	private UserCredentialsService userCredentialsService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private EmailServiceImpl emailService;

	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private WebSecurityConfig config;

	@CrossOrigin(origins = "*")
	@GetMapping(value = "/getAllUsersCredentials", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserCredentials>> getAllUsers() {
		List<UserCredentials> usersCredentials = (List<UserCredentials>) userCredentialsService.findAll();
		return new ResponseEntity<>(usersCredentials, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/checkCredeentials", produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserCredentialsDTO> checkUserCredentials(@RequestBody UserCredentialsDTO userCredentialsDTO) {
		UserCredentials userCredentials = userCredentialsService.findByEmail(userCredentialsDTO.getEmail());
		if(userCredentials == null/* || !(userCredentials.getPassword().equals(userCredentialsDTO.getPassword()))*/) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(userCredentialsDTO, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@PutMapping(value = "/changePassword/{newPass}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> changePassword(@PathVariable String newPass) throws AuthenticationException, Exception {
		if(config.changePassword("ADMINISTRATOR", newPass))
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(value = "/logIn", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserTokenStateDTO> createAuthenticationToken(@RequestBody UserCredentialsDTO userCredentialsDTO) {
		UsernamePasswordAuthenticationToken uToken = new UsernamePasswordAuthenticationToken(userCredentialsDTO.getEmail(), userCredentialsDTO.getPassword());
		Authentication authentication = authenticationManager.authenticate(uToken);
		
		// Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
		// kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserCredentials userCredentials = userCredentialsService.findByEmail(userCredentialsDTO.getEmail());

		if(userCredentials.isVerified()){
			// Kreiraj token za tog korisnika
			User user = (User) authentication.getPrincipal();
			String jwt = tokenUtils.generateToken(user.getUsername(), user.getUserType().get(0).getName());
			int expiresIn = tokenUtils.getExpiredIn();

			// Vrati token kao odgovor na uspesnu autentifikaciju
			return ResponseEntity.ok(new UserTokenStateDTO(jwt, expiresIn));
		}

		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/getUserIdByEmail",  produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('CUSTOMER')")
	public ResponseEntity<Integer> getUserIdByEmail(@RequestBody String email) {
		UserCredentials userCredentials = userCredentialsService.findByEmail(email);
		Integer userId = userCredentials.getUser().getId();
		return new ResponseEntity<Integer>(userId, HttpStatus.OK);
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserCredentialsDTO> addNewUser(@RequestBody UserCredentialsDTO userCredentialsDto) {
    	User u = userService.findById(userCredentialsDto.userId);
    	UserCredentials uc = new UserCredentials(null,userCredentialsDto.email,userCredentialsDto.password,u,true);
		UserCredentials newUserCredentials = userCredentialsService.save(uc);
		return new ResponseEntity<UserCredentialsDTO>(userCredentialsDto, HttpStatus.CREATED);
	}

	@PostMapping(value = "/registreUserCredentials", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserCredentials> addNewUserCredentials(@Valid @RequestBody UserCredentialsDTO userCredentialsDTO) {
		UserCredentials uc = new UserCredentials(null, userCredentialsDTO.email, userCredentialsDTO.password, null);
		boolean notFounded = userCredentialsService.getByEmail(userCredentialsDTO.email);

		if(notFounded) {
			RandomString randomString =  new RandomString();
			byte[] array = new byte[7]; // length is bounded by 7
			new Random().nextBytes(array);
			String generatedString = new String(array, Charset.forName("UTF-8"));
			String verifiedString = randomString.getAlphaNumericString(20);
			uc.setVerifiedString(verifiedString);
			UserCredentials newUserCredentials = userCredentialsService.save(uc);
			return new ResponseEntity<UserCredentials>(newUserCredentials, HttpStatus.CREATED);
		}

		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	@CrossOrigin(origins = "*")
	@PutMapping(value="/updateUserCredentials", consumes = "application/json")
	public ResponseEntity updateUserCredentials(@RequestBody UpdateUserDTO user) {
		UserCredentials userCredentials = userCredentialsService.findById(user.userId);
		if(userCredentials == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		UserType ut = null;
		switch(user.getUser().userType) {
			case 3: ut = new UserType(3, "ADMIN");break;
			case 1: ut = new UserType(1, "STAFF");break;
			case 2: ut = new UserType(2, "CUSTOMER");break;
		}
		User newUser =  userService.findById(user.user.userId);
		List<UserType> userTypes = new ArrayList<>();
		userTypes.add(ut);
		newUser.setUserType(userTypes);
		userCredentials.setUser(newUser);
		userCredentialsService.save(userCredentials);

		String token = userCredentials.getVerifiedString();
		String msg = "Please click this link to verify and log In: http://localhost:9090/UserCredentialsController/verifyUser?token=" + token + "&email=" + userCredentials.getEmail();
		EmailDetails email = new EmailDetails("milana.dokic.md@gmail.com", msg, "Verification", "");
		emailService.sendSimpleMail(email);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "/verifyUser")
	public ResponseEntity verifyUser(@RequestParam String token, String email) {
		UserCredentials userCredentials = userCredentialsService.findByEmail(email);
		if(userCredentials.getEmail().equals(email) && userCredentials.getVerifiedString().equals(token)) {
			final HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("Location", "http://localhost:4200");
			ResponseEntity entity = new ResponseEntity(responseHeaders, HttpStatus.PERMANENT_REDIRECT);
			userCredentials.setVerified(true);
			userCredentialsService.save(userCredentials);
			return entity;
		}
		return new ResponseEntity(HttpStatus.UNAUTHORIZED);
	}

	@GetMapping(value = "/getAllUserCredentials", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getAllUserCredentials() {
    	List<UserCredentials> userCredentials = (List<UserCredentials>) userCredentialsService.findAll();
    	List<String> emails = new ArrayList<String>();
    	for(UserCredentials uc : userCredentials){
    		emails.add(uc.getEmail());
    	}
		return new ResponseEntity<>(emails, HttpStatus.OK);
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
