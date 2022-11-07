package isaapp.g3malt.repository;

import java.util.ArrayList;

public interface IRepository<T,U>{
    public ArrayList<T> getAll();
    public T getById(U id);
    public U create(T object); //returns objects id
    public void update(T object);
    public void delete(U id);
}
