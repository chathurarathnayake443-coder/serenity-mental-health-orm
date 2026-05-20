package lk.ijse.serenitymentalhealth.dao.custom.impl;

import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dao.custom.PatientSessionDAO;
import lk.ijse.serenitymentalhealth.entity.PatientSession;
import lk.ijse.serenitymentalhealth.enums.PaymentStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    public boolean update(double sessionFee, int patientId, int sessionId, PaymentStatus paymentStatus){

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            String hql = """
                    UPDATE PatientSession ps
                    SET ps.sessionFee = :sessionFee,
                        ps.paymentStatus = :paymentStatus
                    WHERE ps.patient.patientId = :patientId
                    AND ps.therapySession.therapySessionId = :sessionId
                    """;

            session.createQuery(hql)
                    .setParameter("sessionFee", sessionFee)
                    .setParameter("paymentStatus", sessionFee >= 2000 ? PaymentStatus.COMPLETED : PaymentStatus.PENDING)
                    .setParameter("patientId", patientId)
                    .setParameter("sessionId", sessionId)
                    .executeUpdate();

            session.clear();
            transaction.commit();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
        finally{
            session.close();
        }
    }
}
