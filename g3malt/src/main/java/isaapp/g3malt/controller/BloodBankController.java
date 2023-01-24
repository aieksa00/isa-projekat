package isaapp.g3malt.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import isaapp.g3malt.dto.*;
import isaapp.g3malt.model.*;
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

	@GetMapping(value = "BloodBank/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('STAFF')")
	public ResponseEntity<BloodBankDto> getBloodBank(@PathVariable String email) {
		
		Integer userId = userCredentialsService.findByEmail(email).getUser().getId();
		Integer bloodBankId = bloodBankService.findByStaffId(userId);
		BloodBank bloodBank = bloodBankService.findById(bloodBankId);

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

	@CrossOrigin(origins =  "*")
	@GetMapping(value = "BloodBankById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('CUSTOMER')")
	public ResponseEntity<BloodBankDto> getBloodBankById(@PathVariable Integer id) {

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

	@CrossOrigin(origins =  "*")
	@PostMapping(value = "BloodBankId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('CUSTOMER')")
	public ResponseEntity<BloodBandAppDTO> getBloodBankId(@PathVariable Integer id, @RequestBody String email) {

		BloodBank bloodBank = bloodBankService.findById(id);

		if (bloodBank == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		BloodBandAppDTO bloodBandAppDTO = new BloodBandAppDTO(bloodBank.getId(), bloodBank.getName(), bloodBank.getStreet(), bloodBank.getCity(), null);

		User user = userCredentialsService.findByEmail(email).getUser();

		List<Appointment> userAppointments = appointmentService.findByCustomerId(user.getId());
		if (userAppointments.isEmpty()) {
				Set<Appointment> appointments = bloodBank.getFreeAppointments();
				Set<AppointmentDTO> appoints = new HashSet<>();
				for( Appointment appointment : appointments) {

					if(appointment.isFree() && appointment.getScheduleDateTime().after(new Date())) {
						AppointmentDTO appointmentDTO = new AppointmentDTO(appointment.getId(), appointment.getBloodBankId(), null, appointment.getScheduleDateTime(), appointment.getDuration(), appointment.isFree());
						appoints.add(appointmentDTO);
					}
				}

				bloodBandAppDTO.setFreeAppointments(appoints);
				return new ResponseEntity<>(bloodBandAppDTO, HttpStatus.OK);
		}

		Date temp = new Date(122,11,23,16,00,00);
		Appointment app = new Appointment(1, null, temp, 30, 1500, null, true);

		for(Appointment appointment : userAppointments) {
			if(appointment.getScheduleDateTime().after(app.getScheduleDateTime()) && !(appointment.isFree())) {
				app = appointment;
			}
		}

		Set<Appointment> appointments = bloodBank.getFreeAppointments();
		Set<AppointmentDTO> appoints = new HashSet<>();
		for( Appointment appointment : appointments) {

			Calendar c = Calendar.getInstance();
			c.setTime(appointment.getScheduleDateTime());
			c.add(Calendar.MONTH, -6);
			Date date = c.getTime();

			if(appointment.isFree() && appointment.getScheduleDateTime().after(new Date()) && app.getScheduleDateTime().before(date) && !userAppointments.contains(appointment)) {
				AppointmentDTO appointmentDTO = new AppointmentDTO(appointment.getId(), appointment.getBloodBankId(), null, appointment.getScheduleDateTime(), appointment.getDuration(), appointment.isFree());
				appoints.add(appointmentDTO);
			}
		}

		bloodBandAppDTO.setFreeAppointments(appoints);

		return new ResponseEntity<>(bloodBandAppDTO, HttpStatus.OK);
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
	
    @PostMapping(value = "/UpdateBloodBankStorage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('STAFF')")
    public ResponseEntity<BloodBank> UpdateBloodBankStorage(@RequestBody UpdateBloodBankStorageDto dto) {
		BloodBank bloodBank = bloodBankService.findById(dto.getBloodBankId());
		
		switch(dto.getBloodType()) {
			case("APlus"):
				int newQuantityA = bloodBank.getBloodBankStorage().getAPlus() + 1;
				bloodBank.getBloodBankStorage().setAPlus(newQuantityA);
				break;
			case("BPlus"):
				int newQuantityB = bloodBank.getBloodBankStorage().getBPlus() + 1;
				bloodBank.getBloodBankStorage().setBPlus(newQuantityB);
				break;
			case("ABPlus"):
				int newQuantityAB = bloodBank.getBloodBankStorage().getABPlus() + 1;
				bloodBank.getBloodBankStorage().setABPlus(newQuantityAB);
				break;
			case("OPlus"):
				int newQuantityO = bloodBank.getBloodBankStorage().getOPlus() + 1;
				bloodBank.getBloodBankStorage().setOPlus(newQuantityO);
				break;
			case("AMinus"):
				int newQuantityAm = bloodBank.getBloodBankStorage().getAMinus() + 1;
				bloodBank.getBloodBankStorage().setAMinus(newQuantityAm);
				break;
			case("BMinus"):
				int newQuantityBm = bloodBank.getBloodBankStorage().getBMinus() + 1;
				bloodBank.getBloodBankStorage().setBMinus(newQuantityBm);
				break;
			case("ABMinus"):
				int newQuantityABm = bloodBank.getBloodBankStorage().getABMinus() + 1;
				bloodBank.getBloodBankStorage().setABMinus(newQuantityABm);
				break;
			case("OMinus"):
				int newQuantityOm = bloodBank.getBloodBankStorage().getOMinus() + 1;
				bloodBank.getBloodBankStorage().setOMinus(newQuantityOm);
				break;
		}
		BloodBank newBloodBank = bloodBankService.save(bloodBank);
        return new ResponseEntity<BloodBank>(HttpStatus.OK);
    }
	
	@CrossOrigin(origins = "*")
    @PostMapping(value = "/addBloodBank", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('ADMIN')")
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
    public ResponseEntity<List<BloodBankWithRatingDTO>> getAllBloodBanks() {
    	List<BloodBank> banks = (List<BloodBank>) bloodBankService.findAll();
    	List<BloodBankWithRatingDTO> banksDto = new ArrayList<BloodBankWithRatingDTO>();
		
		for(BloodBank b : banks) {
			BloodBankWithRatingDTO bloodBankWithRatingDTO = modelMapper.map(b, BloodBankWithRatingDTO.class);
			banksDto.add(bloodBankWithRatingDTO);
		}
		
		return new ResponseEntity<>(banksDto, HttpStatus.OK);
    }

	@PostMapping (value = "/getFilteredBloodBanks", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BloodBankWithRatingDTO>> getFilteredBloodBanks(@RequestBody SearchBanksDTO searchBanksDTO) {
		String searchName = searchBanksDTO.getSearchByName().equals("name") ? searchBanksDTO.getSearch() : "";
		String searchCity = searchBanksDTO.getSearchByName().equals("city") ? searchBanksDTO.getSearch() : "";
		Double filter = searchBanksDTO.getFilterValue().equals("") ? 0 : Double.parseDouble(searchBanksDTO.getFilterValue());

		List<BloodBank> banks = bloodBankService.searchFilterSort(searchName, searchCity, filter, searchBanksDTO.getSortValue());
		List<BloodBankWithRatingDTO> bloodBanks = new ArrayList<>();
		for(BloodBank bloodBank : banks) {
			BloodBankWithRatingDTO bloodBankWithRatingDTO = modelMapper.map(bloodBank, BloodBankWithRatingDTO.class);
			bloodBanks.add(bloodBankWithRatingDTO);
		}

		return new ResponseEntity<>(bloodBanks, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/getSortedAppointments", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('CUSTOMER')")
	public ResponseEntity<List<AppointmentDTO>> getSortAppointments(@RequestBody SortDTO sortDTO) {
		List<Appointment> apps = bloodBankService.sortAppointments(sortDTO);
		List<AppointmentDTO> appointments = new ArrayList<>();

		User user = userCredentialsService.findByEmail(sortDTO.getEmail()).getUser();
		List<Appointment> userAppointments = appointmentService.findByCustomerId(user.getId());

		if (userAppointments.isEmpty()) {
			for( Appointment appointment : apps) {

				if(appointment.isFree() && appointment.getScheduleDateTime().after(new Date())) {
					AppointmentDTO appointmentDTO = new AppointmentDTO(appointment.getId(), appointment.getBloodBankId(), null, appointment.getScheduleDateTime(), appointment.getDuration(), appointment.isFree());
					appointments.add(appointmentDTO);
				}
			}

			return new ResponseEntity<>(appointments, HttpStatus.OK);
		}

		Date temp = new Date(122,11,23,16,00,00);
		Appointment app = new Appointment(1, null, temp, 30, 1500, null, true);

		for(Appointment appointment : userAppointments) {
			if(appointment.getScheduleDateTime().after(app.getScheduleDateTime()) && !(appointment.isFree())) {
				app = appointment;
			}
		}

		for( Appointment appointment : apps) {

			Calendar c = Calendar.getInstance();
			c.setTime(appointment.getScheduleDateTime());
			c.add(Calendar.MONTH, -6);
			Date date = c.getTime();

			if(appointment.isFree() && appointment.getScheduleDateTime().after(new Date()) && app.getScheduleDateTime().before(date) && !userAppointments.contains(appointment)) {
				AppointmentDTO appointmentDTO = new AppointmentDTO(appointment.getId(), appointment.getBloodBankId(), null, appointment.getScheduleDateTime(), appointment.getDuration(), appointment.isFree());
				appointments.add(appointmentDTO);
			}
		}
		return new ResponseEntity<>(appointments, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/getAllAppointmentsForBloodBank/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('STAFF')")
    public ResponseEntity<List<CalenderEventDto>> getAllAppointmentsForBloodBank(@PathVariable String email) {
    	var u = userCredentialsService.findByEmail(email);
		BloodBank bb = bloodBankService.findById(bloodBankService.findByStaffId(u.getUser().getId()));
		var apps = bb.getFreeAppointments();
		List<CalenderEventDto> events = new ArrayList<CalenderEventDto>();
		for(Appointment app: apps){
			CalenderEventDto event = new CalenderEventDto(app.getId().toString(),app.getScheduleDateTime(),app.getDuration(),app.getCustomer());
			events.add(event);
		}
		return new ResponseEntity<>(events, HttpStatus.OK);
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
	
	@PostMapping (value = "/getAppointmentFromBloodBank", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> getAppointmentFromBloodBank(@RequestBody BloodBankAppointmentDto bloodBankAppointmentDto) {
		BloodBank bank = bloodBankService.findById(bloodBankAppointmentDto.bloodBankId);

		for(Appointment a : bank.getFreeAppointments()) {
			String date = a.getScheduleDateTime().toString().split(" ", 2)[0];
			String hour = a.getScheduleDateTime().toString().split(" ", 2)[1];
			String date2 = bloodBankAppointmentDto.getAppointmentTime().split(" ", 2)[0];
			String hour2 = bloodBankAppointmentDto.getAppointmentTime().split(" ", 2)[1];
			if(date.equals(date2)) {
				String exHour = hour.split(":")[0];
				String exHour2 = hour2.split(":")[0];
				if(hour.split(":")[0].equals(hour2.split(":")[0])) {
					return new ResponseEntity<>(a.getId(), HttpStatus.OK);
				}
			}
		}

		return new ResponseEntity<>(1, HttpStatus.OK);
	}
}
