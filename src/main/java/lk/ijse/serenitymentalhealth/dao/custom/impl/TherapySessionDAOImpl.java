package lk.ijse.serenitymentalhealth.dao.custom.impl;

import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dao.custom.TherapySessionDAO;
import lk.ijse.serenitymentalhealth.entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Integer maxId = session.createQuery("SELECT MAX(t.therapySessionId) FROM TherapySession t", Integer.class)
                    .uniqueResult();
            transaction.commit();
            return (maxId == null) ? "1" : String.valueOf(maxId + 1);
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return "1";
    }

    @Override
    public List<TherapyProgram> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public TherapyProgram find(String name) throws SQLException {
        return null;
    }
}
