package isaapp.g3malt.repository;

import isaapp.g3malt.model.Appointment;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	
	@Query("select a from Appointment a where (a.scheduleDateTime >= ?1 and a.bloodBank.id = ?2)")
	public Set<Appointment> findAllFutureAppointmentsForBloodBank(Date scheduleDateTime, Integer bloodBankId);
	
}
