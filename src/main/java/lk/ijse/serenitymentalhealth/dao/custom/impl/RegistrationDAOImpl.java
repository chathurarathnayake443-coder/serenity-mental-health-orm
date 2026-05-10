package lk.ijse.serenitymentalhealth.dao.custom.impl;

import lk.ijse.serenitymentalhealth.dao.custom.RegistrationDAO;
import lk.ijse.serenitymentalhealth.entity.Registration;

import java.sql.SQLException;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public boolean save(Registration entity) throws SQLException {
        return false;
    }
}
