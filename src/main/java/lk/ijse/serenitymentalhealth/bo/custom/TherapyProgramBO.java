package lk.ijse.serenitymentalhealth.bo.custom;

import lk.ijse.serenitymentalhealth.bo.SuperBO;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;
import lk.ijse.serenitymentalhealth.dto.TherapyProgramDTO;
import lk.ijse.serenitymentalhealth.entity.Therapist;
import lk.ijse.serenitymentalhealth.entity.TherapyProgram;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TherapyProgramBO extends SuperBO {

    public boolean saveTherapyProgram(TherapyProgramDTO therapyProgramDTO) throws SQLException;

    public List<TherapyProgramDTO> loadTherapyProgramTable() throws SQLException;

    public boolean updateTherapyProgram(TherapyProgramDTO therapyProgramDTO) throws SQLException;

    public boolean deleteTherapyProgram(String id) throws SQLException;

    public List<TherapyProgramDTO> loadProgramNames() throws SQLException;

    public List<TherapistDTO> loadTherapistIds() throws SQLException;

    public String getTherapistNameById(int id) throws SQLException;

    public String getProgramIdByName(String name) throws SQLException;
}
