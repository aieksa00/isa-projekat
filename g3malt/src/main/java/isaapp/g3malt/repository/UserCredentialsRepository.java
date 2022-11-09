package isaapp.g3malt.repository;

import isaapp.g3malt.model.UserCredentials;

import java.util.ArrayList;

public class UserCredentialsRepository implements IRepository<UserCredentials, String>{
    @Override
    public ArrayList<UserCredentials> getAll() {
        return null;
    }

    @Override
    public UserCredentials getById(String id) {
        return null;
    }

    @Override
    public UserCredentials create(UserCredentials object) {
        return object;
    }

    @Override
    public void update(UserCredentials object) {

    }

    @Override
    public void delete(String id) {

    }
}
