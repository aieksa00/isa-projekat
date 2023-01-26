package isaapp.g3malt.services;

import isaapp.g3malt.model.Customer;
import isaapp.g3malt.model.User;
import isaapp.g3malt.repository.CustomerRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements IService<Customer, Integer>{
	
	@Autowired
    private CustomerRepository customerRepository;

	@Override
	public Customer save(Customer entity) {
		return customerRepository.save(entity);
	}

	@Override
	public Customer findById(Integer id) {
		return customerRepository.findById(id).orElseGet(null);
	}

	@Override
	public Iterable<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Iterable<Customer> findAllById(Iterable<Integer> ids) {
		return customerRepository.findAllById(ids);
	}

	@Override
	public void deleteById(Integer id) {
		customerRepository.deleteById(id);
	}

	@Override
	public Customer edit(Customer entity) {
		Optional<Customer> customerFromDB = customerRepository.findById(entity.getId());
		if(customerFromDB.isPresent()) {
			Customer customerToEdit = customerFromDB.get();
			customerToEdit = entity;
			return customerRepository.save(customerToEdit);
		}
		return null;
	}

	@Scheduled(cron = "0 0 0 1 * *")
	public void doSomething() {
		Iterable<Customer> customerList = findAll();
		for(Customer customer : customerList) {
			customer.setPenalty(0);
			save(customer);
		}
	}
}
