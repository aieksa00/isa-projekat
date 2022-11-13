package isaapp.g3malt.services;

import isaapp.g3malt.model.UserCredentials;
import isaapp.g3malt.repository.UserCredentialsRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialsService implements IService<UserCredentials, Integer>{

	@Autowired
    private UserCredentialsRepository userCredentialsRepository;

	@Override
	public UserCredentials save(UserCredentials entity) {
		return userCredentialsRepository.save(entity);
	}

	@Override
	public UserCredentials findById(Integer id) {
		return userCredentialsRepository.findById(id).orElseGet(null);
	}

	@Override
	public Iterable<UserCredentials> findAll() {
		return userCredentialsRepository.findAll();
	}

	@Override
	public Iterable<UserCredentials> findAllById(Iterable<Integer> ids) {
		return userCredentialsRepository.findAllById(ids);
	}

	@Override
	public void deleteById(Integer id) {
		userCredentialsRepository.deleteById(id);
	}

	@Override
	public UserCredentials edit(UserCredentials entity) {
		Optional<UserCredentials> userCredentialsFromDB = userCredentialsRepository.findById(entity.getId());
		if(userCredentialsFromDB.isPresent()) {
			UserCredentials userCredentialsToEdit = userCredentialsFromDB.get();
			userCredentialsToEdit = entity;
			return userCredentialsRepository.save(userCredentialsToEdit);
		}
		return null;
	}
}
