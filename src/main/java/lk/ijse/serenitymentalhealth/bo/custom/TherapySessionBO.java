package lk.ijse.serenitymentalhealth.bo.custom;

import lk.ijse.serenitymentalhealth.bo.SuperBO;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;
import lk.ijse.serenitymentalhealth.dto.TherapyProgramDTO;
import lk.ijse.serenitymentalhealth.entity.Patient;
import lk.ijse.serenitymentalhealth.entity.Therapist;
import lk.ijse.serenitymentalhealth.entity.TherapyProgram;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface TherapySessionBO extends SuperBO {

    public String showNextId() throws SQLException;

    public List<TherapistDTO> loadTherapistIds() throws SQLException;

    public List<PatientDTO> loadPatientIds() throws SQLException;

    public String getTherapistNameById(int id) throws SQLException;

    public String getPatientNameById(int id) throws SQLException;

    public String getProgramNameById(String id) throws SQLException;

    public List<PatientDTO> loadPatientIdsByProgram(String id) throws SQLException;

    public List<TherapyProgramDTO> loadTherapyProgramTable() throws SQLException;

    public boolean createSession(int hours, int minutes, int duration, LocalDate date, int therapistId, String programId, List<PatientDTO> list) throws SQLException;
}
