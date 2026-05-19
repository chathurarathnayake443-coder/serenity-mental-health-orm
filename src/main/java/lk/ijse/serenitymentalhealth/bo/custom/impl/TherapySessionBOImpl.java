package lk.ijse.serenitymentalhealth.bo.custom.impl;

import lk.ijse.serenitymentalhealth.bo.custom.TherapySessionBO;
import lk.ijse.serenitymentalhealth.dao.DAOFactory;
import lk.ijse.serenitymentalhealth.dao.custom.PatientDAO;
import lk.ijse.serenitymentalhealth.dao.custom.TherapySessionDAO;

import java.sql.SQLException;

public class TherapySessionBOImpl implements TherapySessionBO {

    TherapySessionDAO therapySessionDAO = (TherapySessionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPY_SESSION);

    public String showNextId() throws SQLException {
        String id = therapySessionDAO.showNextId();
        return id;
    }
}
