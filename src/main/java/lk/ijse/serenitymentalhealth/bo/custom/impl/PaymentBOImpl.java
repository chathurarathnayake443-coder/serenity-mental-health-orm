package lk.ijse.serenitymentalhealth.bo.custom.impl;

import lk.ijse.serenitymentalhealth.bo.custom.PaymentBO;
import lk.ijse.serenitymentalhealth.dao.DAOFactory;
import lk.ijse.serenitymentalhealth.dao.custom.PatientSessionDAO;
import lk.ijse.serenitymentalhealth.dao.custom.QueryDAO;
import lk.ijse.serenitymentalhealth.dao.custom.TherapySessionDAO;
import lk.ijse.serenitymentalhealth.dto.PaymentDTO;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;
import lk.ijse.serenitymentalhealth.dto.TherapySessionDTO;
import lk.ijse.serenitymentalhealth.entity.PatientSession;
import lk.ijse.serenitymentalhealth.entity.Therapist;
import lk.ijse.serenitymentalhealth.entity.TherapySession;
import lk.ijse.serenitymentalhealth.enums.PaymentStatus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    TherapySessionDAO therapySessionDAO = (TherapySessionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPY_SESSION);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    PatientSessionDAO patientSessionDAO = (PatientSessionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PATIENT_SESSION);

    public List<Integer> loadTherapySessionIds() throws SQLException {
        List<TherapySession> therapySessionList = therapySessionDAO.getAll();
        List<Integer> therapySessionDTOList = new ArrayList<>();
        for (TherapySession session : therapySessionList) {
            therapySessionDTOList.add(session.getTherapySessionId());
        }
        return therapySessionDTOList;
    }

    public List<PaymentDTO> loadPaymentDTOs(int id) throws SQLException {
        List<PatientSession> list = queryDAO.getSessionPayments(id);
        List<PaymentDTO> paymentDTOList = new ArrayList<>();
        for (PatientSession session : list) {
            paymentDTOList.add(new PaymentDTO(session.getTherapySession().getTherapySessionId(),
                    session.getPatient().getPatientId(),
                    session.getTherapySession().getTherapist().getTherapistId(),
                    session.getTherapySession().getTherapist().getTherapistName(),
                    session.getPatient().getPatientName(),
                    session.getSessionFee(),
                    session.getPaymentStatus()));
            System.out.println("==========================");
            System.out.println(session.getPaymentStatus());
            System.out.println("==========================");
        }
        return paymentDTOList;
    }

    public boolean makePayment(double sessionFee, int patientId, int sessionId, PaymentStatus paymentStatus) throws SQLException {
        return patientSessionDAO.update(sessionFee, patientId, sessionId, paymentStatus);
    }
}
