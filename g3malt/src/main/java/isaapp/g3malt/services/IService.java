package isaapp.g3malt.services;

import java.util.ArrayList;

public interface IService<T,U> {
    public ArrayList<T> getAll();
    public T getById(U id);
    public T create(T object); //returns object
    public void update(T object);
    public void delete(U id);
}
