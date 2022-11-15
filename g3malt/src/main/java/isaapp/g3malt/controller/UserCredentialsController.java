package isaapp.g3malt.controller;

import isaapp.g3malt.dto.UserCredentialsDTO;
import isaapp.g3malt.model.User;
import isaapp.g3malt.model.UserCredentials;
import isaapp.g3malt.services.UserCredentialsService;
import isaapp.g3malt.services.UserService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/UserCredentialsController")
public class UserCredentialsController {
		
	@Autowired
	private UserCredentialsService userCredentialsService;
	
	@Autowired
	private UserService userService;
	
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/addUserCredentials", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserCredentials> addNewUser(@RequestBody UserCredentialsDTO userCredentialsDto) {
    	User u = userService.findById(userCredentialsDto.userId);
    	UserCredentials uc = new UserCredentials(null,userCredentialsDto.email,userCredentialsDto.password,u);
        UserCredentials newUserCredentials = userCredentialsService.save(uc);
        return new ResponseEntity<UserCredentials>(newUserCredentials, HttpStatus.CREATED);
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
}
