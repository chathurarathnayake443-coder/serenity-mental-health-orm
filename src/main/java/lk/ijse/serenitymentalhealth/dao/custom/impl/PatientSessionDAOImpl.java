package lk.ijse.serenitymentalhealth.dao.custom.impl;

import lk.ijse.serenitymentalhealth.dao.custom.PatientSessionDAO;
import lk.ijse.serenitymentalhealth.entity.PatientSession;

import java.sql.SQLException;

public class PatientSessionDAOImpl implements PatientSessionDAO {
    @Override
    public boolean save(PatientSession entity) throws SQLException {
        return false;
    }
}
