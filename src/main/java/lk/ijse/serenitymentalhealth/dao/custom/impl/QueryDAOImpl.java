package lk.ijse.serenitymentalhealth.dao.custom.impl;

import com.mysql.cj.xdevapi.SessionFactory;
import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dao.custom.QueryDAO;
import lk.ijse.serenitymentalhealth.entity.Patient;
import lk.ijse.serenitymentalhealth.entity.PatientSession;
import lk.ijse.serenitymentalhealth.entity.Therapist;
import lk.ijse.serenitymentalhealth.entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    public List<Therapist> getTherapistsByProgramId(String programId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            List<Therapist> therapists = session.createQuery(
                            "SELECT t FROM Therapist t JOIN t.therapyPrograms tp WHERE tp.therapyProgramId = :programId",
                            Therapist.class)
                    .setParameter("programId", programId)
                    .list();
            transaction.commit();
            return therapists;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    public List<Object[]> getPatientSessionHistory(int patientId) {
        Session session = FactoryConfiguration.getInstance().getSession();

        try {
            String hql = """
                SELECT ts.therapySessionId,
                       ts.therapist.therapistName,
                       ts.date,
                       ts.therapyProgram.therapyProgramName
                FROM PatientSession ps
                JOIN ps.therapySession ts
                WHERE ps.patient.patientId = :patientId
                """;

            return session.createQuery(hql, Object[].class)
                    .setParameter("patientId", patientId)
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }


    public TherapySession getSessionData(int sessionId){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            String hql = """
                SELECT ts FROM TherapySession ts
                JOIN FETCH ts.therapyProgram
                JOIN FETCH ts.therapist
                JOIN FETCH ts.patientSessions ps
                JOIN FETCH ps.patient
                WHERE ts.therapySessionId = :sessionId
                """;

            TherapySession result = session.createQuery(hql, TherapySession.class)
                    .setParameter("sessionId", sessionId)
                    .uniqueResult();
            transaction.commit();
            return result;
        }
        catch(Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        return null;
    }

    public List<TherapySession> getSessionsByTherapistId(int therapistId, Session session) {
        String hql = """
            SELECT ts FROM TherapySession ts
            JOIN FETCH ts.therapyProgram
            WHERE ts.therapist.therapistId = :therapistId
            AND ts.status != 'CANCELLED'
            """;

        return session.createQuery(hql, TherapySession.class)
                .setParameter("therapistId", therapistId)
                .getResultList();
    }

    public List<PatientSession> getSessionPayments(int sessionId){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            String hql = """
        SELECT ps FROM PatientSession ps
        JOIN FETCH ps.therapySession ts
        JOIN FETCH ts.therapist
        JOIN FETCH ps.patient
        WHERE ts.therapySessionId = :sessionId
        """;

            List<PatientSession> results = session.createQuery(hql, PatientSession.class)
                    .setParameter("sessionId", sessionId)
                    .getResultList();
            transaction.commit();
            return results;
        }
        catch(Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        finally{
            session.close();
        }
        return null;
    }

    public List<Patient> getPatientsInAllPrograms(){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            String hql = """
        SELECT p FROM Patient p
        WHERE (
            SELECT COUNT(DISTINCT r.therapyProgram.therapyProgramId)
            FROM Registration r
            WHERE r.patient.patientId = p.patientId
        ) = (
            SELECT COUNT(tp.therapyProgramId)
            FROM TherapyProgram tp
        )
        """;

            List<Patient> patients = session.createQuery(hql, Patient.class)
                    .getResultList();
            transaction.commit();
            return patients;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            session.close();
        }
        return null;
    }
}
