package isaapp.g3malt.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		Appointment appointment = appointmentService.findById(questionnaireDTO.appointmentId);
		if(!appointment.isFree()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
        Questionnaire questionnaire = new Questionnaire(null, questionnaireDTO.previousDonations, questionnaireDTO.userId, questionnaireDTO.question1, questionnaireDTO.question2, questionnaireDTO.question3,
                questionnaireDTO.question4, questionnaireDTO.question5, questionnaireDTO.question6, questionnaireDTO.question7, questionnaireDTO.question8, questionnaireDTO.question9, questionnaireDTO.question10,
                questionnaireDTO.question11, questionnaireDTO.question12, questionnaireDTO.question13, questionnaireDTO.question14, questionnaireDTO.question15, questionnaireDTO.question16);
        questionnaireService.save(questionnaire);
        Customer customer = customerService.findById(questionnaireDTO.userId);
        appointment.setUser(customer);
		appointment.setFree(false);
		appointmentService.save(appointment);
		String msg = "Info of your appointment";
		EmailDetails email = new EmailDetails("milana.dokic.md@gmail.com", msg, "Verification", "../../../../../../../frontend/src/app/images/My_Gallery.png");
		emailService.sendMailWithAttachment(email);

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
		Calendar c = Calendar.getInstance();
		c.setTime(app.getScheduleDateTime());
		c.add(Calendar.DAY_OF_MONTH, -1);
		Date date = c.getTime();
		if((new Date()).after(date)) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

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

	@CrossOrigin("*")
	@PostMapping(value = "CreateAppointmentByPatient/{id}", consumes = "application/json")
	@PreAuthorize("hasAuthority('CUSTOMER')")
	public ResponseEntity<Integer> CreateAppointmentByPatient(@PathVariable Integer id, @RequestBody CreateAppointmentByPatientDTO dto) {
		Appointment appointment = new Appointment();
		appointment.setBloodBankId(id);
		Customer customer = customerService.findById(dto.getCustomerId());
		//appointment.setCustomer(customer);
		appointment.setDuration(dto.getDuration());
		appointment.setFree(true);
		appointment.setPrice(1000.00);
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		try {
			Date date = formatter.parse(dto.getScheduleDateTime());
			System.out.println(date);
			appointment.setScheduleDateTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		appointment = appointmentService.save(appointment);
		
		return new ResponseEntity<>(appointment.getId(), HttpStatus.OK);
	}
}
