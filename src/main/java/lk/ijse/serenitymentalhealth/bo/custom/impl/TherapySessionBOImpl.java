package lk.ijse.serenitymentalhealth.bo.custom.impl;

import lk.ijse.serenitymentalhealth.bo.custom.TherapySessionBO;
import lk.ijse.serenitymentalhealth.dao.DAOFactory;
import lk.ijse.serenitymentalhealth.dao.custom.PatientDAO;
import lk.ijse.serenitymentalhealth.dao.custom.TherapistDAO;
import lk.ijse.serenitymentalhealth.dao.custom.TherapySessionDAO;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;
import lk.ijse.serenitymentalhealth.entity.Therapist;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapySessionBOImpl implements TherapySessionBO {

    TherapySessionDAO therapySessionDAO = (TherapySessionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPY_SESSION);
    TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPIST);

    public String showNextId() throws SQLException {
        String id = therapySessionDAO.showNextId();
        return id;
    }

    public List<TherapistDTO> loadTherapistIds() throws SQLException {
        List<Therapist> therapistList = therapistDAO.getAll();
        List<TherapistDTO> therapistDTOList = new ArrayList<>();
        for (Therapist therapist : therapistList) {
            therapistDTOList.add(new TherapistDTO(therapist.getTherapistId()));
        }
        return therapistDTOList;
    }
}
