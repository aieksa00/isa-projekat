package isaapp.g3malt.controller;

import java.util.*;

import isaapp.g3malt.dto.*;
import isaapp.g3malt.model.*;
import isaapp.g3malt.services.*;
import isaapp.g3malt.util.EmailDetails;
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

import isaapp.g3malt.model.Appointment;
import isaapp.g3malt.model.User;
import isaapp.g3malt.services.AppointmentService;
import isaapp.g3malt.services.UserService;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private QuestionnaireService questionnaireService;

	@Autowired
	private EmailServiceImpl emailService;

    @Autowired
    private CustomerService customerService;
	
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
//		Customer customer = new Customer(user.getId(), user.getName(), user.getSurname(), user.getStreet(), user.getCity(), user.getCountry(),
//					user.getPhoneNumber(), user.getJmbg(), user.getGender(), user.getProfession(), user.getWorkplace(), user.getUserType(),
//					0, LoyaltyType.bronze, 0, null);
//        appointment.setUser(customer);
		String msg = "Info of your appointment";
		EmailDetails email = new EmailDetails("milana.dokic.md@gmail.com", msg, "Verification", "../../../../../../../frontend/src/app/images/My_Gallery.png");
		emailService.sendMailWithAttachment(email);
        appointment.setFree(false);
        appointmentService.save(appointment);
        return new ResponseEntity(HttpStatus.CREATED);
    }

	@CrossOrigin(origins = "*")
	@GetMapping(value = "/getScheduledAppointments")
	@PreAuthorize("hasAuthority('CUSTOMER')")
	public ResponseEntity<List<AppointmentDTO>> getScheduledAppointments() {
		Iterable<Appointment> appointments = appointmentService.findAll();
		List<AppointmentDTO> notFree = new ArrayList<>();
		for(Appointment appointment : appointments) {
			if(!appointment.isFree()) {
				AppointmentDTO appointmentDTO = new AppointmentDTO(appointment.getId(), appointment.getBloodBankId(), null, appointment.getScheduleDateTime(), appointment.getDuration(), appointment.isFree());
				notFree.add(appointmentDTO);
			}
		}

		return new ResponseEntity<List<AppointmentDTO>>(notFree, HttpStatus.OK);

	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/unscheduleAppointment",  produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('CUSTOMER')")
	public ResponseEntity<List<AppointmentDTO>> unscheduleAppointment(@RequestBody Integer appointmentId) {
		Appointment app = appointmentService.findById(appointmentId);
		app.setFree(true);
		appointmentService.save(app);

		Iterable<Appointment> appointments = appointmentService.findAll();
		List<AppointmentDTO> notFree = new ArrayList<>();
		for(Appointment appointment : appointments) {
			if(!appointment.isFree()) {
				AppointmentDTO appointmentDTO = new AppointmentDTO(appointment.getId(), appointment.getBloodBankId(), null, appointment.getScheduleDateTime(), appointment.getDuration(), appointment.isFree());
				notFree.add(appointmentDTO);
			}
		}

		return new ResponseEntity<List<AppointmentDTO>>(notFree, HttpStatus.OK);
	}

}
