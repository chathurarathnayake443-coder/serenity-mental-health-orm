package lk.ijse.serenitymentalhealth.bo.custom;

import lk.ijse.serenitymentalhealth.bo.SuperBO;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.dto.TherapyProgramDTO;
import lk.ijse.serenitymentalhealth.entity.Patient;
import lk.ijse.serenitymentalhealth.entity.TherapyProgram;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RegistrationBO extends SuperBO {

    public String showNextId() throws SQLException;

    public List<PatientDTO> loadPatientTable() throws SQLException;

    public List<TherapyProgramDTO> loadProgramNames() throws SQLException;
}
