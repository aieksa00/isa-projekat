package isaapp.g3malt.services;

import isaapp.g3malt.model.BloodBank;
import isaapp.g3malt.repository.BloodBankRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
