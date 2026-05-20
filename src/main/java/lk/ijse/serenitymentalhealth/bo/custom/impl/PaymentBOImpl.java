package lk.ijse.serenitymentalhealth.bo.custom.impl;

import lk.ijse.serenitymentalhealth.bo.custom.PaymentBO;
import lk.ijse.serenitymentalhealth.dao.DAOFactory;
import lk.ijse.serenitymentalhealth.dao.custom.TherapySessionDAO;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;
import lk.ijse.serenitymentalhealth.dto.TherapySessionDTO;
import lk.ijse.serenitymentalhealth.entity.Therapist;
import lk.ijse.serenitymentalhealth.entity.TherapySession;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    TherapySessionDAO therapySessionDAO = (TherapySessionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPY_SESSION);

    public List<Integer> loadTherapySessionIds() throws SQLException {
        List<TherapySession> therapySessionList = therapySessionDAO.getAll();
        List<Integer> therapySessionDTOList = new ArrayList<>();
        for (TherapySession session : therapySessionList) {
            therapySessionDTOList.add(session.getTherapySessionId());
        }
        return therapySessionDTOList;
    }
}
