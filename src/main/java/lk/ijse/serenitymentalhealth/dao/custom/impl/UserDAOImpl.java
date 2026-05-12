package lk.ijse.serenitymentalhealth.dao.custom.impl;

import lk.ijse.serenitymentalhealth.dao.custom.UserDAO;
import lk.ijse.serenitymentalhealth.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(User entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public String showNextId() throws SQLException {
        return "";
    }

    @Override
    public List<User> getAll() throws SQLException {
        return List.of();
    }
}
