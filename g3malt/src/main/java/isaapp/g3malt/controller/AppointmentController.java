package isaapp.g3malt.controller;

<<<<<<< HEAD
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
=======
import isaapp.g3malt.dto.QuestionnaireDTO;
import isaapp.g3malt.model.Appointment;
import isaapp.g3malt.model.Customer;
import isaapp.g3malt.model.Questionnaire;
import isaapp.g3malt.model.User;
import isaapp.g3malt.services.AppointmentService;
import isaapp.g3malt.services.CustomerService;
import isaapp.g3malt.services.QuestionnaireService;
import isaapp.g3malt.services.UserService;
>>>>>>> bcef546 (schedule appointment)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
<<<<<<< HEAD
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
=======
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointmentController")
public class AppointmentController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/scheduleAppointment",  produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity scheduleAppointment(@RequestBody QuestionnaireDTO questionnaireDTO) {
        Questionnaire questionnaire = new Questionnaire(null, questionnaireDTO.previousDonations, questionnaireDTO.userId, questionnaireDTO.question1, questionnaireDTO.question2, questionnaireDTO.question3,
                questionnaireDTO.question4, questionnaireDTO.question5, questionnaireDTO.question6, questionnaireDTO.question7, questionnaireDTO.question8, questionnaireDTO.question9, questionnaireDTO.question10,
                questionnaireDTO.question11, questionnaireDTO.question12, questionnaireDTO.question13, questionnaireDTO.question14, questionnaireDTO.question15, questionnaireDTO.question16);
        questionnaireService.save(questionnaire);
        Appointment appointment = appointmentService.findById(questionnaireDTO.appointmentId);
        User user = userService.findById(questionnaireDTO.userId);
        Customer customer = customerService.findById(questionnaireDTO.userId);
        appointment.setUser(customer);
        appointment.setFree(false);
        appointmentService.save(appointment);
        return new ResponseEntity(HttpStatus.CREATED);
    }
>>>>>>> bcef546 (schedule appointment)
}
