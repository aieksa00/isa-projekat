package isaapp.g3malt.controller;

import java.util.*;

import isaapp.g3malt.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import isaapp.g3malt.model.Appointment;
import isaapp.g3malt.model.BloodBank;
import isaapp.g3malt.model.User;
import isaapp.g3malt.services.AppointmentService;
import isaapp.g3malt.services.BloodBankService;
import isaapp.g3malt.services.UserCredentialsService;
import isaapp.g3malt.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/BloodBankController")
public class BloodBankController {
	
	@Autowired
	private BloodBankService bloodBankService;
	@Autowired
	private UserService userService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private UserCredentialsService userCredentialsService;
	
	private ModelMapper modelMapper = new ModelMapper();

	@GetMapping(value = "BloodBank/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BloodBankDto> getBloodBank(@PathVariable Integer id) {

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
		
		Set<Appointment> futureAppointments = appointmentService.findAllFutureAppointmentsForBloodBank(new Date(), bloodBank.getId());
		Set<FutureAppointmentDto> futureAppointmentsDtos = new HashSet<FutureAppointmentDto>();
		for(Appointment appointment : futureAppointments) {
			FutureAppointmentDto futureAppointmentsDto = modelMapper.map(appointment, FutureAppointmentDto.class);
			futureAppointmentsDtos.add(futureAppointmentsDto);
		}
		bloodBankDto.setBloodBankFutureAppointments(futureAppointmentsDtos);
		
		return new ResponseEntity<>(bloodBankDto, HttpStatus.OK);
	}
	
	@CrossOrigin("*")
	@PostMapping(value = "CreateAppointment/{id}", consumes = "application/json")
	public ResponseEntity<FutureAppointmentDto> createAppointment(@PathVariable Integer id, @RequestBody FutureAppointmentDto dto) {
		Appointment appointment = new Appointment();
		appointment.setBloodBankId(id);
		appointment.setCustomer(null);
		appointment.setDuration(dto.getDuration());
		appointment.setFree(true);
		appointment.setPrice(1000.00);
		appointment.setScheduleDateTime(dto.getScheduleDateTime());
		
		Set<User> staff = new HashSet<User>();
		for(StaffDto staffDto : dto.getMedicalStaff()) {
			User user = userService.findById(staffDto.getId());
			staff.add(user);
		}
		
		appointment.setMedicalStaff(staff);
		
		appointmentService.save(appointment);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(value = "UpdateBloodBank/{id}", consumes = "application/json")
	public ResponseEntity<BloodBankDto> updateStudent(@PathVariable Integer id, @RequestBody UpdateBloodBankDto dto) {

		BloodBank bloodBank = bloodBankService.findById(id);

		if (bloodBank == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		bloodBank.setName(dto.getBloodBankName());
		bloodBank.setStreet(dto.getBloodBankStreet());
		bloodBank.setCity(dto.getBloodBankCity());
		bloodBank.setCountry(dto.getBloodBankCountry());
		bloodBank.setDescription(dto.getBloodBankDescription());

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
	
	@CrossOrigin(origins = "*")
    @PostMapping(value = "/addBloodBank", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<CreateBloodBankDTO> addBloodBank(@RequestBody CreateBloodBankDTO bloodbankDto) {
		User administrator = userService.findById(bloodbankDto.administratorId);
		Set<User> users = new HashSet<User>();
		users.add(administrator);
		BloodBank bloodBank = new BloodBank(null, bloodbankDto.name, bloodbankDto.street, bloodbankDto.city, bloodbankDto.country, bloodbankDto.description, 0, null, users, bloodbankDto.workingHours, null, null);
        BloodBank newBloodBank = bloodBankService.save(bloodBank);
        return new ResponseEntity<CreateBloodBankDTO>(bloodbankDto, HttpStatus.CREATED);
    }

	@CrossOrigin(origins = "*")
	@GetMapping(value = "/getAllBloodBanks", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<List<BloodBank>> getAllBloodBanks() {
    	List<BloodBank> banks = (List<BloodBank>) bloodBankService.findAll();
		return new ResponseEntity<>(banks, HttpStatus.OK);
    }

	@CrossOrigin("*")
	@PostMapping (value = "/getFilteredBloodBanks", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BloodBank>> getFilteredBloodBanks(@RequestBody SearchBanksDTO searchBanksDTO) {
		String searchName = searchBanksDTO.getSearchByName().equals("name") ? searchBanksDTO.getSearch() : "";
		String searchCity = searchBanksDTO.getSearchByName().equals("city") ? searchBanksDTO.getSearch() : "";
		Double filter = searchBanksDTO.getFilterValue().equals("") ? 0 : Double.parseDouble(searchBanksDTO.getFilterValue());

		List<BloodBank> banks = bloodBankService.searchFilterSort(searchName, searchCity, filter, searchBanksDTO.getSortValue());

		return new ResponseEntity<>(banks, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/getAllAppointmentsForBloodBank/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CalenderEventDto>> getAllAppointmentsForBloodBank(@PathVariable String email) {
    	var u = userCredentialsService.findByEmail(email);
		BloodBank bb = bloodBankService.findById(u.getUser().getId());
		var apps = bb.getFreeAppointments();
		List<CalenderEventDto> events = new ArrayList<CalenderEventDto>();
		for(Appointment app: apps){
			CalenderEventDto event = new CalenderEventDto(app.getId().toString(),app.getScheduleDateTime(),app.getDuration(),app.getCustomer());
			events.add(event);
		}
		return new ResponseEntity<>(events, HttpStatus.OK);
    }
}
