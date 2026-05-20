package lk.ijse.serenitymentalhealth.dao.custom;

import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dao.CrudDAO;
import lk.ijse.serenitymentalhealth.entity.PatientSession;
import lk.ijse.serenitymentalhealth.enums.PaymentStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;

public interface PatientSessionDAO extends CrudDAO<PatientSession> {

    public boolean save(PatientSession patientSession, Session session);

    public boolean update(double sessionFee, int patientId, int sessionId, PaymentStatus paymentStatus);

}
