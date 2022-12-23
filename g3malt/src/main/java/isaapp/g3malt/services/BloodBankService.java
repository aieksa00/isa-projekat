package isaapp.g3malt.services;

import isaapp.g3malt.dto.FutureAppointmentDto;
import isaapp.g3malt.dto.SortDTO;
import isaapp.g3malt.model.Appointment;
import isaapp.g3malt.model.BloodBank;
import isaapp.g3malt.model.User;
import isaapp.g3malt.repository.BloodBankRepository;

import java.util.*;

import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BloodBankService implements IService<BloodBank, Integer>{
	
	@Autowired
    private BloodBankRepository bloodBankRepository;

	@Override
	public BloodBank save(BloodBank entity) {
		return bloodBankRepository.save(entity);
	}

	@Override
	public BloodBank findById(Integer id) {
		return bloodBankRepository.findById(id).orElseGet(null);
	}

	@Override
	public Iterable<BloodBank> findAll() {
		return bloodBankRepository.findAll();
	}

	@Override
	public Iterable<BloodBank> findAllById(Iterable<Integer> ids) {
		return bloodBankRepository.findAllById(ids);
	}
	
	public List<BloodBank> searchFilterSort(String searchName, String searchCity, Double filterValue, String sortValue) {
		List<BloodBank> banks = bloodBankRepository.searchFilterSort(searchName, searchCity, filterValue);
		Comparator<BloodBank> compareName = new Comparator<BloodBank>() {
			@Override
			public int compare(BloodBank bb1, BloodBank bb2) {
				return bb1.getName().compareTo(bb2.getName());
			}
		};

		Comparator<BloodBank> compareCity = new Comparator<BloodBank>() {
			@Override
			public int compare(BloodBank bb1, BloodBank bb2) {
				return bb1.getCity().compareTo(bb2.getCity());
			}
		};

		Comparator<BloodBank> compareRating = new Comparator<BloodBank>() {
			@Override
			public int compare(BloodBank bb1, BloodBank bb2) {
				return Double.compare(bb1.getRating(), bb2.getRating());
			}
		};

		if(sortValue.equals("city")) {
			Collections.sort(banks, compareCity);
		} else if(sortValue.equals("name")) {
			Collections.sort(banks, compareName);
		} else if(sortValue.equals(("rating"))) {
			Collections.sort(banks, compareRating);
		}

		return banks;
	}

	public List<Appointment> sortAppointments(SortDTO sortDTO) {
		BloodBank bloodBank = findById(sortDTO.bloodBankId);
		List<Appointment> appointments = new ArrayList(bloodBank.getFreeAppointments());
		String sortValue = sortDTO.sortValue;

		Comparator<Appointment> compareDateTime = new Comparator<Appointment>() {
			@Override
			public int compare(Appointment a1, Appointment a2) {
				return a1.getScheduleDateTime().compareTo(a2.getScheduleDateTime());
			}
		};

		Comparator<Appointment> compareDuration = new Comparator<Appointment>() {
			@Override
			public int compare(Appointment a1, Appointment a2) {

				return Integer.compare(a1.getDuration(), a2.getDuration());
			}
		};

		if(sortValue.equals("duration")) {
			Collections.sort(appointments, compareDuration);
		} else if(sortValue.equals("date")) {
			Collections.sort(appointments, compareDateTime);
		};

		return appointments;
	}
	
	public Integer findByStaffId(Integer staff) {
		return bloodBankRepository.findByStaffId(staff);
	}

	@Override
	public void deleteById(Integer id) {
		bloodBankRepository.deleteById(id);
	}

	@Override
	public BloodBank edit(BloodBank entity) {
		Optional<BloodBank> bloodBankFromDB = bloodBankRepository.findById(entity.getId());
		if(bloodBankFromDB.isPresent()) {
			BloodBank bloodBankToEdit = bloodBankFromDB.get();
			bloodBankToEdit = entity;
			return bloodBankRepository.save(bloodBankToEdit);
		}
		return null;
	}
			
	public Iterable<BloodBank> findAllWithFreeAppointment(String appointmentTime) {
		
		List<BloodBank> banks = bloodBankRepository.findAll();
		List<BloodBank> banksWithFreeAppointment = new ArrayList<BloodBank>();
		for(BloodBank b : banks) {
			for(Appointment a : b.getFreeAppointments()) {
				Date time = a.getScheduleDateTime();
				String date = time.toString().split(" ", 2)[0];
				String hour = time.toString().split(" ", 2)[1];
				String date2 = appointmentTime.split(" ", 2)[0];
				String hour2 = appointmentTime.split(" ", 2)[1];
				if(date.equals(date2)) {
					String exHour = hour.split(":")[0];
					String exHour2 = hour2.split(":")[0];
					if(hour.split(":")[0].equals(hour2.split(":")[0])) {
						banksWithFreeAppointment.add(b);
					}
				}
			}
		}
		
		return banksWithFreeAppointment;
	}
}
