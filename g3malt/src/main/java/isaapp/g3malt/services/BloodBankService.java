package isaapp.g3malt.services;

import isaapp.g3malt.model.BloodBank;
import isaapp.g3malt.repository.BloodBankRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
	
	public BloodBank findByStaffId(Integer staffId) {
		return bloodBankRepository.findByStaffId(staffId);
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
}
