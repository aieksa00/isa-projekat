package isaapp.g3malt.repository;

import isaapp.g3malt.model.User;

import java.util.ArrayList;

public class UserRepository implements IRepository<User, String> {
    @Override
    public ArrayList<User> getAll() {
        return null;
    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public User create(User object) {
        return object;
    }

    @Override
    public void update(User object) {

    }

    @Override
    public void delete(String id) {

    }
}
