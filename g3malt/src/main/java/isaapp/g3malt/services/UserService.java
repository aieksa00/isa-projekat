package isaapp.g3malt.services;

import isaapp.g3malt.model.User;
import isaapp.g3malt.repository.UserRepository;

import java.util.ArrayList;

public class UserService implements IService<User, String>{

    private UserRepository userRepository = new UserRepository();
    @Override
    public ArrayList<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getById(String id) {
        return userRepository.getById(id);
    }

    @Override
    public User create(User object) {
        return userRepository.create(object);
    }

    @Override
    public void update(User object) {
        userRepository.update(object);
    }

    @Override
    public void delete(String id) {
        userRepository.delete(id);
    }
}