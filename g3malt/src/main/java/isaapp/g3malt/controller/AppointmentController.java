package isaapp.g3malt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
}
