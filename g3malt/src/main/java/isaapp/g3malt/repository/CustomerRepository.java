package isaapp.g3malt.repository;

import isaapp.g3malt.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
 
}
