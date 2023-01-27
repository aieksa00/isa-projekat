package isaapp.g3malt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isaapp.g3malt.model.BloodBankStorage;
import isaapp.g3malt.repository.BloodBankRepository;
import isaapp.g3malt.repository.BloodBankStorageRepository;

@Service
public class BloodBankStorageService implements IService<BloodBankStorage, Integer> {

	@Autowired
    private BloodBankStorageRepository bloodBankStorageRepository;
	
	@Override
	public BloodBankStorage save(BloodBankStorage entity) {
		return bloodBankStorageRepository.save(entity);
	}

	@Override
	public BloodBankStorage edit(BloodBankStorage entity) {
		return null;
	}

	@Override
	public BloodBankStorage findById(Integer id) {
		return bloodBankStorageRepository.findById(id).orElseGet(null);
	}

	@Override
	public Iterable<BloodBankStorage> findAll() {
		return bloodBankStorageRepository.findAll();
	}

	@Override
	public Iterable<BloodBankStorage> findAllById(Iterable<Integer> ids) {
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		bloodBankStorageRepository.deleteById(id);
		
	}

}
