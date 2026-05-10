package lk.ijse.serenitymentalhealth.dao.custom.impl;

import lk.ijse.serenitymentalhealth.dao.custom.TherapySessionDAO;
import lk.ijse.serenitymentalhealth.entity.TherapyProgram;

import java.sql.SQLException;

public class TherapySessionDAOImpl implements TherapySessionDAO {
    @Override
    public boolean save(TherapyProgram entity) throws SQLException {
        return false;
    }
}
