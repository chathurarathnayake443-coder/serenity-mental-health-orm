package lk.ijse.serenitymentalhealth.bo.custom;

import lk.ijse.serenitymentalhealth.bo.SuperBO;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.dto.RegistrationDTO;
import lk.ijse.serenitymentalhealth.dto.TherapyProgramDTO;
import lk.ijse.serenitymentalhealth.entity.Patient;
import lk.ijse.serenitymentalhealth.entity.Registration;
import lk.ijse.serenitymentalhealth.entity.TherapyProgram;
import lk.ijse.serenitymentalhealth.enums.PaymentStatus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RegistrationBO extends SuperBO {

    public boolean saveRegistration(RegistrationDTO registrationDTO) throws SQLException;

    public boolean updateRegistration(RegistrationDTO registrationDTO) throws SQLException;

    public String showNextId() throws SQLException;

    public List<PatientDTO> loadPatientTable() throws SQLException;

    public List<TherapyProgramDTO> loadProgramNames() throws SQLException;

    public String getIdByName(String name) throws SQLException;

    public double getPriceById(String id) throws SQLException;

    public List<RegistrationDTO> loadRegistrationData(String name) throws SQLException;
}
