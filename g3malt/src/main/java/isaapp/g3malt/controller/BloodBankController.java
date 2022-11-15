package isaapp.g3malt.controller;

import isaapp.g3malt.dto.BloodBankDto;
import isaapp.g3malt.dto.StaffDto;
import isaapp.g3malt.model.BloodBank;
import isaapp.g3malt.model.User;
import isaapp.g3malt.services.BloodBankService;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/BloodBankController")
public class BloodBankController {
	
	@Autowired
	private BloodBankService bloodBankService;
	
	private ModelMapper modelMapper = new ModelMapper();

	@GetMapping(value = "BloodBank/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BloodBankDto> getStudent(@PathVariable Integer id) {

		BloodBank bloodBank = bloodBankService.findById(id);

		if (bloodBank == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		BloodBankDto bloodBankDto = modelMapper.map(bloodBank, BloodBankDto.class);
		
		Set<StaffDto> allStaff = new HashSet<StaffDto>(); 
		for(User user : bloodBank.getAllStaff()) {
			StaffDto staffDto = modelMapper.map(user, StaffDto.class);
			allStaff.add(staffDto);
		}
		bloodBankDto.setBloodBankAdministrators(allStaff);

		return new ResponseEntity<>(bloodBankDto, HttpStatus.OK);
	}
	
	@PutMapping(value = "UpdateDescription/{id}", consumes = "application/json")
	public ResponseEntity<BloodBankDto> updateStudent(@PathVariable Integer id, @RequestBody String description) {

		BloodBank bloodBank = bloodBankService.findById(id);

		if (bloodBank == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		bloodBank.setDescription(description);

		bloodBank = bloodBankService.save(bloodBank);
		
		BloodBankDto bloodBankDto = modelMapper.map(bloodBank, BloodBankDto.class);
		
		Set<StaffDto> allStaff = new HashSet<StaffDto>(); 
		for(User user : bloodBank.getAllStaff()) {
			StaffDto staffDto = modelMapper.map(user, StaffDto.class);
			allStaff.add(staffDto);
		}
		bloodBankDto.setBloodBankAdministrators(allStaff);
		
		return new ResponseEntity<>(bloodBankDto, HttpStatus.OK);
	}
}

