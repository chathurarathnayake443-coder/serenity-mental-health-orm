package lk.ijse.serenitymentalhealth.dao.custom;

import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dao.CrudDAO;
import lk.ijse.serenitymentalhealth.entity.User;
import lk.ijse.serenitymentalhealth.exception.LoginException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {

    public boolean delete(String id) throws SQLException;

    public User findByName(String name) throws SQLException, LoginException;
}
