package lk.ijse.serenitymentalhealth.dao.custom.impl;

import lk.ijse.serenitymentalhealth.dao.custom.RegistrationDAO;
import lk.ijse.serenitymentalhealth.entity.Registration;

import java.sql.SQLException;
import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public boolean save(Registration entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Registration entity) throws SQLException {
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
    public List<Registration> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public Registration find(String name) throws SQLException {
        return null;
    }
}
