package isaapp.g3malt.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isaapp.g3malt.dto.AppointmentReviewDto;
import isaapp.g3malt.dto.BloodBankDto;
import isaapp.g3malt.dto.FutureAppointmentDto;
import isaapp.g3malt.dto.StaffDto;
import isaapp.g3malt.model.Appointment;
import isaapp.g3malt.model.BloodBank;
import isaapp.g3malt.model.User;
import isaapp.g3malt.services.AppointmentService;
import isaapp.g3malt.services.BloodBankService;
import isaapp.g3malt.services.UserCredentialsService;
import isaapp.g3malt.services.UserService;

@RestController
@RequestMapping("/AppointmentController")
public class AppointmentController {
	
	@Autowired
	private BloodBankService bloodBankService;
	@Autowired
	private UserService userService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private UserCredentialsService userCredentialsService;
	
	private ModelMapper modelMapper = new ModelMapper();

	@GetMapping(value = "GetAppointmentReviewDtoById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('STAFF')")
	public ResponseEntity<AppointmentReviewDto> getBloodBank(@PathVariable Integer id) {
		
		Appointment appointment = appointmentService.findById(id);

		if (appointment == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		AppointmentReviewDto dto = modelMapper.map(appointment, AppointmentReviewDto.class);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
