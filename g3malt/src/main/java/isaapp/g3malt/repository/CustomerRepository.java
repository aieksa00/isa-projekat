package isaapp.g3malt.repository;

import isaapp.g3malt.model.Customer;

import java.util.ArrayList;

public class CustomerRepository implements IRepository<Customer, String> {
    @Override
    public ArrayList<Customer> getAll() {
        return null;
    }

    @Override
    public Customer getById(String id) {
        return null;
    }

    @Override
    public String create(Customer object) {
        return null;
    }

    @Override
    public void update(Customer object) {

    }

    @Override
    public void delete(String id) {

    }
}
