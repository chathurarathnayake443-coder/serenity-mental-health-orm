package lk.ijse.serenitymentalhealth.bo.custom.impl;

import lk.ijse.serenitymentalhealth.bo.custom.TherapistBO;
import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dao.DAOFactory;
import lk.ijse.serenitymentalhealth.dao.custom.PatientDAO;
import lk.ijse.serenitymentalhealth.dao.custom.QueryDAO;
import lk.ijse.serenitymentalhealth.dao.custom.TherapistDAO;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;
import lk.ijse.serenitymentalhealth.dto.TherapyProgramDTO;
import lk.ijse.serenitymentalhealth.dto.TherapySessionDTO;
import lk.ijse.serenitymentalhealth.entity.Patient;
import lk.ijse.serenitymentalhealth.entity.Therapist;
import lk.ijse.serenitymentalhealth.entity.TherapyProgram;
import lk.ijse.serenitymentalhealth.entity.TherapySession;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapistBOImpl implements TherapistBO {
    TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPIST);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

    public boolean saveTherapist(TherapistDTO therapistDTO) throws SQLException {

        Therapist therapist = new Therapist();
        therapist.setTherapistName(therapistDTO.getTherapistName());
        therapist.setTherapistEmail(therapistDTO.getTherapistEmail());
        therapist.setTherapistPhone(therapistDTO.getTherapistPhone());
        therapist.setTherapistAddress(therapistDTO.getTherapistAddress());

        return therapistDAO.save(therapist);
    }

    public boolean updateTherapist(TherapistDTO therapistDTO) throws SQLException {
        Therapist therapist = new Therapist();
        therapist.setTherapistName(therapistDTO.getTherapistName());
        therapist.setTherapistEmail(therapistDTO.getTherapistEmail());
        therapist.setTherapistPhone(therapistDTO.getTherapistPhone());
        therapist.setTherapistAddress(therapistDTO.getTherapistAddress());

        return therapistDAO.update(therapist);
    }

    public boolean deleteTherapist(int id) throws SQLException {
        return therapistDAO.delete(id);
    }

    public String showNextId() throws SQLException {
        String id = therapistDAO.showNextId();
        return id;
    }

    public List<TherapistDTO> loadTherapistTable() throws SQLException {
        List<Therapist> therapistList = therapistDAO.getAll();
        List<TherapistDTO> therapistDTOList = new ArrayList<>();
        for (Therapist therapist : therapistList) {
            therapistDTOList.add(new TherapistDTO(therapist.getTherapistId(),therapist.getTherapistName(),therapist.getTherapistEmail(),therapist.getTherapistPhone(),therapist.getTherapistAddress()));
        }
        return therapistDTOList;
    }

    public boolean assignTherapistToProgram(String programId, int therapistId) throws SQLException{
        boolean result = therapistDAO.assignProgramToTherapist(programId,therapistId);
        return result;
    }

    public boolean removeTherapistFromProgram(String programId, int therapistId) throws SQLException {
        return therapistDAO.removeTherapistFromProgram(programId, therapistId);
    }

    public List<TherapySessionDTO> getSessionsByTherapistId(int therapistId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            List<TherapySession> sessionList = queryDAO.getSessionsByTherapistId(therapistId, session);
            List<TherapySessionDTO> dtoList = new ArrayList<>();

            for (TherapySession ts : sessionList) {
                dtoList.add(new TherapySessionDTO(
                        ts.getTherapySessionId(),
                        ts.getTherapyProgram().getTherapyProgramName(),
                        ts.getDate(),
                        ts.getStartTime()
                ));
            }
            return dtoList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
