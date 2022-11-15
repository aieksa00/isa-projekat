package isaapp.g3malt.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isaapp.g3malt.model.BloodBank;
import isaapp.g3malt.model.BloodBankDTO;
import isaapp.g3malt.model.User;
import isaapp.g3malt.services.BloodBankService;
import isaapp.g3malt.services.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/bloodBankController")
public class BloodBankController {
	
	@Autowired
	private BloodBankService bloodbankService;
	
	@Autowired
	private UserService userService;
	
	@CrossOrigin(origins = "*")
    @PostMapping(value = "/addBloodBank", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BloodBank> addNewUser(@RequestBody BloodBankDTO bloodbankDto) {
		User administrator = userService.findById(bloodbankDto.administratorId);
		Set<User> users = new HashSet<User>();
		users.add(administrator);
		BloodBank bloodBank = new BloodBank(null, bloodbankDto.name, bloodbankDto.address, bloodbankDto.description, 0, null, users, bloodbankDto.workingHours);
        BloodBank newBloodBank = bloodbankService.save(bloodBank);
        return new ResponseEntity<BloodBank>(newBloodBank, HttpStatus.CREATED);
    }
}
