package lk.ijse.serenitymentalhealth.dao.custom.impl;

import com.mysql.cj.xdevapi.SessionFactory;
import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dao.custom.QueryDAO;
import lk.ijse.serenitymentalhealth.entity.Therapist;
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
                       ts.date
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
}
