package lk.ijse.serenitymentalhealth.bo.custom.impl;

import lk.ijse.serenitymentalhealth.bo.custom.TherapyProgramBO;
import lk.ijse.serenitymentalhealth.dao.DAOFactory;
import lk.ijse.serenitymentalhealth.dao.custom.TherapistDAO;
import lk.ijse.serenitymentalhealth.dao.custom.TherapyProgramDAO;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;
import lk.ijse.serenitymentalhealth.dto.TherapyProgramDTO;
import lk.ijse.serenitymentalhealth.entity.Patient;
import lk.ijse.serenitymentalhealth.entity.Therapist;
import lk.ijse.serenitymentalhealth.entity.TherapyProgram;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapyProgramBOImpl implements TherapyProgramBO {

    TherapyProgramDAO therapyProgramDAO = (TherapyProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPY_PROGRAM);
    TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPIST);

    public boolean saveTherapyProgram(TherapyProgramDTO therapyProgramDTO) throws SQLException {

        TherapyProgram therapyProgram = new TherapyProgram();
        therapyProgram.setTherapyProgramId(therapyProgramDTO.getTherapyProgramId());
        therapyProgram.setTherapyProgramName(therapyProgramDTO.getTherapyProgramName());
        therapyProgram.setDescription(therapyProgramDTO.getTherapyProgramDescription());
        therapyProgram.setCost(therapyProgramDTO.getTherapyProgramCost());
        therapyProgram.setDuration(Integer.parseInt(therapyProgramDTO.getTherapyProgramDuration()));

        return therapyProgramDAO.save(therapyProgram);
    }

    public boolean updateTherapyProgram(TherapyProgramDTO therapyProgramDTO) throws SQLException {
        TherapyProgram therapyProgram = new TherapyProgram();
        therapyProgram.setTherapyProgramId(therapyProgramDTO.getTherapyProgramId());
        therapyProgram.setTherapyProgramName(therapyProgramDTO.getTherapyProgramName());
        therapyProgram.setDescription(therapyProgramDTO.getTherapyProgramDescription());
        therapyProgram.setCost(therapyProgramDTO.getTherapyProgramCost());
        therapyProgram.setDuration(Integer.parseInt(therapyProgramDTO.getTherapyProgramDuration()));

        return therapyProgramDAO.update(therapyProgram);
    }

    public boolean deleteTherapyProgram(String id) throws SQLException {
        return therapyProgramDAO.delete(id);
    }

    public List<TherapyProgramDTO> loadTherapyProgramTable() throws SQLException {
        List<TherapyProgram> therapyProgramList = therapyProgramDAO.getAll();
        List<TherapyProgramDTO> therapyProgramDTOList = new ArrayList<>();
        for (TherapyProgram therapyProgram : therapyProgramList) {
            therapyProgramDTOList.add(new TherapyProgramDTO(therapyProgram.getTherapyProgramId(), therapyProgram.getTherapyProgramName(), therapyProgram.getDescription(), String.valueOf(therapyProgram.getDuration()), therapyProgram.getCost()));
        }
        return therapyProgramDTOList;
    }

    public List<TherapyProgramDTO> loadProgramNames() throws SQLException {
        List<TherapyProgram> programList = therapyProgramDAO.getAll();
        List<TherapyProgramDTO> therapyProgramDTOList = new ArrayList<>();
        for (TherapyProgram therapyProgram : programList) {
            therapyProgramDTOList.add(new TherapyProgramDTO(therapyProgram.getTherapyProgramId(),therapyProgram.getTherapyProgramName()));
        }
        return therapyProgramDTOList;
    }

    public List<TherapistDTO> loadTherapistIds() throws SQLException {
        List<Therapist> therapistList = therapistDAO.getAll();
        List<TherapistDTO> therapistDTOList = new ArrayList<>();
        for (Therapist therapist : therapistList) {
            therapistDTOList.add(new TherapistDTO(therapist.getTherapistId()));
        }
        return therapistDTOList;
    }

    public String getTherapistNameById(int id) throws SQLException {
        String name = therapistDAO.getNameById(id);
        return name;
    }

}
