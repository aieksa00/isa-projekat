package isaapp.g3malt.services;

import isaapp.g3malt.dto.SortDTO;
import isaapp.g3malt.dto.SortHistoryDTO;
import isaapp.g3malt.model.Appointment;
import isaapp.g3malt.model.BloodBank;
import isaapp.g3malt.model.Customer;
import isaapp.g3malt.model.User;
import isaapp.g3malt.repository.AppointmentRepository;

import java.util.*;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService implements IService<Appointment, Integer>{
	
	@Autowired
    private AppointmentRepository appointmentRepository;

	@Override
	public Appointment save(Appointment entity) {
		List<Appointment> app = (List<Appointment>) appointmentRepository.findByDate(entity.getScheduleDateTime(),entity.getScheduleDateTimeEnd(), entity.getBloodBankId());
		if(!app.isEmpty())
			if(app.get(0).getId() != entity.getId()) {
				return null;
			}
		return appointmentRepository.save(entity);
	}

	@Override
	public Appointment findById(Integer id) {
		return appointmentRepository.findById(id).orElseGet(null);
	}

	@Override
	public Iterable<Appointment> findAll() {
		return appointmentRepository.findAll();
	}

	@Override
	public Iterable<Appointment> findAllById(Iterable<Integer> ids) {
		return appointmentRepository.findAllById(ids);
	}

	@Override
	public void deleteById(Integer id) {
		appointmentRepository.deleteById(id);
	}

	@Override
	public Appointment edit(Appointment entity) {
		Optional<Appointment> appointmentFromDB = appointmentRepository.findById(entity.getId());
		if(appointmentFromDB.isPresent()) {
			Appointment appointmentToEdit = appointmentFromDB.get();
			appointmentToEdit = entity;
			return appointmentRepository.save(appointmentToEdit);
		}
		return null;
	}
	
	public Set<Appointment> findAllFutureAppointmentsForBloodBank(Date scheduleDateTime, Integer bloodBankId){
		return appointmentRepository.findAllFutureAppointmentsForBloodBank(scheduleDateTime, bloodBankId);
	}

	public List<Appointment> sortAppointmentsHistory(String sort, User user) {
		List<Appointment> apps = findByCustomerId(user.getId());
		String sortValue = sort;

		Comparator<Appointment> compareDateTimeAsc = new Comparator<Appointment>() {
			@Override
			public int compare(Appointment a1, Appointment a2) {
				return a1.getScheduleDateTime().compareTo(a2.getScheduleDateTime());
			}
		};

		Comparator<Appointment> compareDateTimeDesc = new Comparator<Appointment>() {
			@Override
			public int compare(Appointment a1, Appointment a2) {
				return a2.getScheduleDateTime().compareTo(a1.getScheduleDateTime());
			}
		};

		Comparator<Appointment> compareDurationAsc = new Comparator<Appointment>() {
			@Override
			public int compare(Appointment a1, Appointment a2) {
				return Integer.compare(a1.getDuration(), a2.getDuration());
			}
		};

		Comparator<Appointment> compareDurationDesc = new Comparator<Appointment>() {
			@Override
			public int compare(Appointment a1, Appointment a2) {
				return Integer.compare(a2.getDuration(), a1.getDuration());
			}
		};

		if(sortValue.equals("dateAsc")) {
			Collections.sort(apps, compareDateTimeAsc);
		} else if(sortValue.equals("dateDesc")) {
			Collections.sort(apps, compareDateTimeDesc);
		}else if(sortValue.equals("durationAsc")) {
			Collections.sort(apps, compareDurationAsc);
		}else if(sortValue.equals("durationDesc")) {
			Collections.sort(apps, compareDurationDesc);
		};

		return apps;
	}

	public List<Appointment> findByCustomerId(Integer id) {
		return appointmentRepository.findByCustomerId(id);
	}

	public Iterable<Customer> findAllCustomersByBloodBankId(Integer bloodBankId){
		Iterable<Appointment> allApps = appointmentRepository.findAllCustomersByBloodBankId(bloodBankId);
		List<Customer> customers = new ArrayList<Customer>();
		for(Appointment app : allApps) {
			Customer c = app.getCustomer();
			if(c!=null)
				customers.add(app.getCustomer());
		}
		return customers;
	}
	public Iterable<Customer> findAllFillteredCustomersByBloodBankId(Integer bloodBankId, String sortParam){
		List<Customer> customers = (List<Customer>)findAllCustomersByBloodBankId(bloodBankId);
		
		Comparator<Customer> compareName = new Comparator<Customer>() {
			@Override
			public int compare(Customer c1, Customer c2) {
				return c1.getName().compareTo(c2.getName());
			}
		};

		Comparator<Customer> compareSurname = new Comparator<Customer>() {
			@Override
			public int compare(Customer c1, Customer c2) {
				return c1.getSurname().compareTo(c2.getSurname());
			}
		};

		if(sortParam.equals("name")) {
			Collections.sort(customers, compareName);
		}
		else if(sortParam.equals("surname")) {
			Collections.sort(customers, compareSurname);
		}
		
		return customers;
	}
}

