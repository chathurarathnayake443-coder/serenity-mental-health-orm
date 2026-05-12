package lk.ijse.serenitymentalhealth.dao.custom.impl;

import lk.ijse.serenitymentalhealth.dao.custom.TherapySessionDAO;
import lk.ijse.serenitymentalhealth.entity.TherapyProgram;

import java.sql.SQLException;
import java.util.List;

public class TherapySessionDAOImpl implements TherapySessionDAO {
    @Override
    public boolean save(TherapyProgram entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(TherapyProgram entity) throws SQLException {
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
    public List<TherapyProgram> getAll() throws SQLException {
        return List.of();
    }
}
