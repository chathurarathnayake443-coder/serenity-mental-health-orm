package lk.ijse.serenitymentalhealth.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {

    public boolean save(T entity) throws SQLException;

    public boolean update(T entity) throws SQLException;

    public boolean delete(int id) throws SQLException;

    public String showNextId() throws SQLException;

    public List<T> getAll() throws SQLException;
}
