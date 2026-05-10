package lk.ijse.serenitymentalhealth.dao;

import java.sql.SQLException;

public interface CrudDAO<T> extends SuperDAO {

    public boolean save(T entity) throws SQLException;
}
