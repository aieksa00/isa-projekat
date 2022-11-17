package isaapp.g3malt.services;

import isaapp.g3malt.model.Appointment;
import isaapp.g3malt.repository.AppointmentRepository;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService implements IService<Appointment, Integer>{
	
	@Autowired
    private AppointmentRepository appointmentRepository;

	@Override
	public Appointment save(Appointment entity) {
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
}
