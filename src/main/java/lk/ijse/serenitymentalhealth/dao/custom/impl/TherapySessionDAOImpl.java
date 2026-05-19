package lk.ijse.serenitymentalhealth.dao.custom.impl;

import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dao.custom.TherapySessionDAO;
import lk.ijse.serenitymentalhealth.entity.TherapyProgram;
import lk.ijse.serenitymentalhealth.entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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


    public boolean save(TherapySession therapySession, Session session) {
        try {
            session.persist(therapySession);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isTherapistAvailable(int therapistId, LocalDate date,
                                        LocalTime startTime, LocalTime endTime,
                                        Session session) {
        try {
            String hql = "SELECT COUNT(s) FROM TherapySession s " +
                    "WHERE s.therapist.therapistId = :therapistId " +
                    "AND s.date = :date " +
                    "AND s.status != 'CANCELLED' " +
                    "AND s.startTime < :endTime " +
                    "AND (s.startTime + s.timeDuration) > :startTime";

            Long count = session.createQuery(hql, Long.class)
                    .setParameter("therapistId", therapistId)
                    .setParameter("date", date)
                    .setParameter("startTime", startTime)
                    .setParameter("endTime", endTime)
                    .uniqueResult();

            return count == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
