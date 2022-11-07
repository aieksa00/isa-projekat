package isaapp.g3malt.repository;

import isaapp.g3malt.model.Appointment;

import java.util.ArrayList;

public class AppointmentRepository implements IRepository<Appointment, String> {
    @Override
    public ArrayList<Appointment> getAll() {
        return null;
    }

    @Override
    public Appointment getById(String id) {
        return null;
    }

    @Override
    public String create(Appointment object) {
        return null;
    }

    @Override
    public void update(Appointment object) {

    }

    @Override
    public void delete(String id) {

    }
}
