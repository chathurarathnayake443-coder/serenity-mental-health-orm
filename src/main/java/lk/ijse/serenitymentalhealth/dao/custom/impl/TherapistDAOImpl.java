package lk.ijse.serenitymentalhealth.dao.custom.impl;

import lk.ijse.serenitymentalhealth.dao.custom.TherapistDAO;
import lk.ijse.serenitymentalhealth.entity.Therapist;

import java.sql.SQLException;
import java.util.List;

public class TherapistDAOImpl implements TherapistDAO {
    @Override
    public boolean save(Therapist entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Therapist entity) throws SQLException {
        return false;
    }

    @Override
    public String showNextId() throws SQLException {
        return "";
    }

    @Override
    public List<Therapist> getAll() throws SQLException {
        return List.of();
    }
}
