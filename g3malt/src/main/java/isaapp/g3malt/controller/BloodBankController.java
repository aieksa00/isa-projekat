package isaapp.g3malt.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import isaapp.g3malt.model.BloodBankDTO;
import isaapp.g3malt.model.User;
import isaapp.g3malt.services.AppointmentService;
import isaapp.g3malt.services.BloodBankService;
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
			FutureAppointmentDto futureAppointmentsDto = new FutureAppointmentDto();
			Date time = appointment.getScheduleDateTime();
			futureAppointmentsDto = modelMapper.map(appointment, FutureAppointmentDto.class);
			futureAppointmentsDto.setScheduleDateTime(time.toString());
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
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		try {
			Date date = formatter.parse(dto.getScheduleDateTime());
			System.out.println(date);
			appointment.setScheduleDateTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
    public ResponseEntity<BloodBank> addNewUser(@RequestBody BloodBankDTO bloodbankDto) {
		User administrator = userService.findById(bloodbankDto.administratorId);
		Set<User> users = new HashSet<User>();
		users.add(administrator);
		BloodBank bloodBank = new BloodBank(null, bloodbankDto.name, bloodbankDto.street, bloodbankDto.city, bloodbankDto.country, bloodbankDto.description, 0, null, users, bloodbankDto.workingHours, null, null);
        BloodBank newBloodBank = bloodBankService.save(bloodBank);
        return new ResponseEntity<BloodBank>(newBloodBank, HttpStatus.CREATED);
    }

	@CrossOrigin(origins = "*")
	@GetMapping(value = "/getAllBloodBanks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BloodBank>> getAllBloodBanks() {
    	List<BloodBank> banks = (List<BloodBank>) bloodBankService.findAll();
		return new ResponseEntity<>(banks, HttpStatus.OK);
    }

	@PostMapping (value = "/getFilteredBloodBanks", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BloodBank>> getFilteredBloodBanks(@RequestBody SearchBanksDTO searchBanksDTO) {
		String searchName = searchBanksDTO.getSearchByName().equals("name") ? searchBanksDTO.getSearch() : "";
		String searchCity = searchBanksDTO.getSearchByName().equals("city") ? searchBanksDTO.getSearch() : "";
		Double filter = searchBanksDTO.getFilterValue().equals("") ? 0 : Double.parseDouble(searchBanksDTO.getFilterValue());

		List<BloodBank> banks = bloodBankService.searchFilterSort(searchName, searchCity, filter, searchBanksDTO.getSortValue());

		return new ResponseEntity<>(banks, HttpStatus.OK);
	}
	
	@PostMapping (value = "/getAllBloodBanksWithFreeAppointment", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BloodBankWithRatingDTO>> getAllBloodBanksWithFreeAppointment(@RequestBody String appointmentTime) {
		List<BloodBank> banks = (List<BloodBank>) bloodBankService.findAllWithFreeAppointment(appointmentTime);
		List<BloodBankWithRatingDTO> banksDto = new ArrayList<BloodBankWithRatingDTO>();
		
		for(BloodBank b : banks) {
			BloodBankWithRatingDTO bloodBankWithRatingDTO = modelMapper.map(b, BloodBankWithRatingDTO.class);
			banksDto.add(bloodBankWithRatingDTO);
		}

		return new ResponseEntity<>(banksDto, HttpStatus.OK);
	}
}
