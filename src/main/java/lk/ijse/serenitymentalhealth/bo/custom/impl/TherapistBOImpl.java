package lk.ijse.serenitymentalhealth.bo.custom.impl;

import lk.ijse.serenitymentalhealth.bo.custom.TherapistBO;
import lk.ijse.serenitymentalhealth.dao.DAOFactory;
import lk.ijse.serenitymentalhealth.dao.custom.PatientDAO;
import lk.ijse.serenitymentalhealth.dao.custom.TherapistDAO;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;
import lk.ijse.serenitymentalhealth.entity.Patient;
import lk.ijse.serenitymentalhealth.entity.Therapist;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapistBOImpl implements TherapistBO {
    TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPIST);

    public boolean saveTherapist(TherapistDTO therapistDTO) throws SQLException {

        Therapist therapist = new Therapist();
        therapist.setTherapistName(therapistDTO.getTherapistName());
        therapist.setTherapistEmail(therapistDTO.getTherapistEmail());
        therapist.setTherapistPhone(therapistDTO.getTherapistPhone());

        return therapistDAO.save(therapist);
    }

    public String showNextId() throws SQLException {
        String id = therapistDAO.showNextId();
        return id;
    }

    public List<TherapistDTO> loadTherapistTable() throws SQLException {
        List<Therapist> therapistList = therapistDAO.getAll();
        List<TherapistDTO> therapistDTOList = new ArrayList<>();
        for (Therapist therapist : therapistList) {
            therapistDTOList.add(new TherapistDTO(therapist.getTherapistId(),therapist.getTherapistName(),therapist.getTherapistEmail(),therapist.getTherapistPhone()));
        }
        return therapistDTOList;
    }
}
