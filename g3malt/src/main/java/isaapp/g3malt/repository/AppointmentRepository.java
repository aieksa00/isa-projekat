package isaapp.g3malt.repository;

import isaapp.g3malt.model.Appointment;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	
	@Query("select a from Appointment a where (a.scheduleDateTime >= ?1 and a.bloodBankId = ?2)")
	public Set<Appointment> findAllFutureAppointmentsForBloodBank(Date scheduleDateTime, Integer bloodBankId);

	@Query("select a from Appointment  a where (a.customer.id = ?1) order by a.scheduleDateTime desc")
	public List<Appointment> findByCustomerId(Integer id);
	
}
