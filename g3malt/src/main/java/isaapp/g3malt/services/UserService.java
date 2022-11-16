package isaapp.g3malt.services;

import isaapp.g3malt.model.User;
import isaapp.g3malt.repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IService<User, Integer>{
	
	@Autowired
    private UserRepository userRepository;

	@Override
	public User save(User entity) {

		return userRepository.save(entity);
	}
	@Override
	public User findById(Integer id) {
		return userRepository.findById(id).orElseGet(null);
	}

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Iterable<User> findAllById(Iterable<Integer> ids) {
		return userRepository.findAllById(ids);
	}

	@Override
	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public User edit(User entity) {
		Optional<User> userFromDB = userRepository.findById(entity.getId());
		if(userFromDB.isPresent()) {
			User userToEdit = userFromDB.get();
			userToEdit = entity;
			return userRepository.save(userToEdit);
		}
		return null;
	}
}