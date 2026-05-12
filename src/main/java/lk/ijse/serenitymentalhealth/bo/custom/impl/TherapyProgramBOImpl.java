package lk.ijse.serenitymentalhealth.bo.custom.impl;

import lk.ijse.serenitymentalhealth.bo.custom.TherapyProgramBO;
import lk.ijse.serenitymentalhealth.dao.DAOFactory;
import lk.ijse.serenitymentalhealth.dao.custom.TherapistDAO;
import lk.ijse.serenitymentalhealth.dao.custom.TherapyProgramDAO;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;
import lk.ijse.serenitymentalhealth.entity.Therapist;

import java.sql.SQLException;

public class TherapyProgramBOImpl implements TherapyProgramBO {

    TherapyProgramDAO therapistProgramDAO = (TherapyProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPY_PROGRAM);

    public boolean saveTherapist(TherapistDTO therapistDTO) throws SQLException {

        Therapist therapist = new Therapist();
        therapist.setTherapistName(therapistDTO.getTherapistName());
        therapist.setTherapistEmail(therapistDTO.getTherapistEmail());
        therapist.setTherapistPhone(therapistDTO.getTherapistPhone());
        therapist.setTherapistAddress(therapistDTO.getTherapistAddress());

        return therapistDAO.save(therapist);
    }
}
