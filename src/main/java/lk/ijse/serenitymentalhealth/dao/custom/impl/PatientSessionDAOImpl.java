package lk.ijse.serenitymentalhealth.dao.custom.impl;

import lk.ijse.serenitymentalhealth.dao.custom.PatientSessionDAO;
import lk.ijse.serenitymentalhealth.entity.PatientSession;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class PatientSessionDAOImpl implements PatientSessionDAO {
    @Override
    public boolean save(PatientSession entity) throws SQLException {
        return false;
    }

    public boolean save(PatientSession patientSession, Session session) {
        try {
            session.persist(patientSession);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        // ← no session.close() — BOImpl manages it
    }

    @Override
    public boolean update(PatientSession entity) throws SQLException {
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
    public List<PatientSession> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public PatientSession find(String name) throws SQLException {
        return null;
    }
}
