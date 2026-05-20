package lk.ijse.serenitymentalhealth.dao.custom;

import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dao.SuperDAO;
import lk.ijse.serenitymentalhealth.entity.Therapist;
import lk.ijse.serenitymentalhealth.entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public interface QueryDAO extends SuperDAO {

    public List<Therapist> getTherapistsByProgramId(String programId);

    public List<Object[]> getPatientSessionHistory(int patientId);

    public TherapySession getSessionData(int sessionId);

    public List<TherapySession> getSessionsByTherapistId(int therapistId, Session session);
}
