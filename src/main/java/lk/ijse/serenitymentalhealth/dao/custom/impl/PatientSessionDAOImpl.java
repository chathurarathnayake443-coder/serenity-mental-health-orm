package lk.ijse.serenitymentalhealth.dao.custom.impl;

import lk.ijse.serenitymentalhealth.dao.custom.PatientSessionDAO;
import lk.ijse.serenitymentalhealth.entity.PatientSession;

import java.sql.SQLException;
import java.util.List;

public class PatientSessionDAOImpl implements PatientSessionDAO {
    @Override
    public boolean save(PatientSession entity) throws SQLException {
        return false;
    }

    @Override
    public String showNextId() throws SQLException {
        return "";
    }

    @Override
    public List<PatientSession> getAll() throws SQLException {
        return List.of();
    }
}
