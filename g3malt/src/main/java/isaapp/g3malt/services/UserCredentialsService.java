package isaapp.g3malt.services;

import isaapp.g3malt.model.UserCredentials;
import isaapp.g3malt.repository.UserCredentialsRepository;

import java.util.ArrayList;

public class UserCredentialsService implements IService<UserCredentials, String>{

    private UserCredentialsRepository userCredentialsRepository = new UserCredentialsRepository();
    @Override
    public ArrayList<UserCredentials> getAll() {
        return userCredentialsRepository.getAll();
    }

    @Override
    public UserCredentials getById(String id) {
        return userCredentialsRepository.getById(id);
    }

    @Override
    public UserCredentials create(UserCredentials object) {
        return userCredentialsRepository.create(object);
    }

    @Override
    public void update(UserCredentials object) {
        userCredentialsRepository.update(object);
    }

    @Override
    public void delete(String id) {
        userCredentialsRepository.delete(id);
    }
}
