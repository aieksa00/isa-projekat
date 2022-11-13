package isaapp.g3malt.repository;

import isaapp.g3malt.model.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    
}
