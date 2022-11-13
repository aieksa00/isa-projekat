package isaapp.g3malt.controller;

import isaapp.g3malt.model.UserCredentials;
import isaapp.g3malt.services.UserCredentialsService;
import isaapp.g3malt.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/UserCredentialsController")
public class UserCredentialsController {
		
	@Autowired
	private UserCredentialsService userCredentialsService;
	
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/addUserCredentials", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserCredentials> addNewUser(@RequestBody UserCredentials userCredentials) {
        UserCredentials newUserCredentials = userCredentialsService.save(userCredentials);
        return new ResponseEntity<UserCredentials>(newUserCredentials, HttpStatus.CREATED);
    }
}
