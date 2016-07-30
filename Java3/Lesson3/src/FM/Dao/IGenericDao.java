package FM.Dao;

import java.util.List;

/**
 * Created by Alex Vasilenko on 14.07.2016.
 * This interface identifies components
 * that can be used to connect to different databases.
 */
public interface IGenericDao<T> {
    void save(T fin);

    void delete(T fin);

    void update(T fin);

    T findById(int id);

    List<T> getAll();

}
