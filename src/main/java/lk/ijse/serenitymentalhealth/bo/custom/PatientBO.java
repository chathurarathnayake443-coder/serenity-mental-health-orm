package lk.ijse.serenitymentalhealth.bo.custom;

import lk.ijse.serenitymentalhealth.bo.SuperBO;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.entity.Patient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PatientBO extends SuperBO {

    public boolean savePatient(PatientDTO patientDTO);

    public String showNextId() throws SQLException;

    public List<PatientDTO> loadPatientTable() throws SQLException;

    public boolean updatePatient(PatientDTO patientDTO);

    public boolean deletePatient(int id);
}
