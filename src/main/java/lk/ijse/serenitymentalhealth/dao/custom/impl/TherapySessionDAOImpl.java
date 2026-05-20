package lk.ijse.serenitymentalhealth.dao.custom.impl;

import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dao.custom.TherapySessionDAO;
import lk.ijse.serenitymentalhealth.entity.TherapyProgram;
import lk.ijse.serenitymentalhealth.entity.TherapySession;
import lk.ijse.serenitymentalhealth.enums.SessionStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TherapySessionDAOImpl implements TherapySessionDAO {
    @Override
    public boolean save(TherapySession entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(TherapySession entity) throws SQLException {
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
    public List<TherapySession> getAll() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            List<TherapySession> sessionList = session.createQuery("FROM TherapySession", TherapySession.class).getResultList();
            transaction.commit();
            return sessionList;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public TherapySession find(String name) throws SQLException {
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
            String hql = """
                    SELECT COUNT(s) FROM TherapySession s
                    WHERE s.therapist.therapistId = :therapistId
                    AND s.date = :date
                    AND s.status != 'CANCELLED'
                    AND s.startTime < :endTime
                    AND s.endTime > :startTime
                    """;

            System.out.println("=== AVAILABILITY CHECK ===");
            System.out.println("TherapistId : " + therapistId);
            System.out.println("Date        : " + date);
            System.out.println("StartTime   : " + startTime);
            System.out.println("EndTime     : " + endTime);

            Long count = session.createQuery(hql, Long.class)
                    .setParameter("therapistId", therapistId)
                    .setParameter("date", date)
                    .setParameter("startTime", startTime)
                    .setParameter("endTime", endTime)
                    .uniqueResult();

            System.out.println("Count       : " + count);
            System.out.println("Available   : " + (count == 0));
            System.out.println("==========================");

            return count == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cancelSession(int sessionId, Session session) {
        TherapySession therapySession = session.get(TherapySession.class, sessionId);
        if (therapySession == null) return false;

        therapySession.setStatus(SessionStatus.CANCELLED);
        session.merge(therapySession);
        return true;
    }

    public boolean rescheduleSession(int sessionId, LocalDate newDate, LocalTime newStartTime, LocalTime newEndTime, Session session) {
        TherapySession therapySession = session.get(TherapySession.class, sessionId);
        if (therapySession == null) return false;

        String hql = """
                SELECT COUNT(s) FROM TherapySession s
                WHERE s.therapist.therapistId = :therapistId
                AND s.date = :date
                AND s.status != 'CANCELLED'
                AND s.therapySessionId != :currentSessionId
                AND s.startTime < :newEndTime
                AND s.endTime > :newStartTime
                """;

        Long count = session.createQuery(hql, Long.class)
                .setParameter("therapistId", therapySession.getTherapist().getTherapistId())
                .setParameter("date", newDate)
                .setParameter("currentSessionId", sessionId)
                .setParameter("newStartTime", newStartTime)
                .setParameter("newEndTime", newEndTime)
                .uniqueResult();

        if (count != null && count > 0) return false;

        therapySession.setDate(newDate);
        therapySession.setStartTime(newStartTime);
        therapySession.setEndTime(newEndTime);
        therapySession.setTimeDuration((int) java.time.Duration.between(newStartTime, newEndTime).toMinutes());
        therapySession.setStatus(SessionStatus.RESCHEDULED);

        session.merge(therapySession);
        return true;
    }
}
