package lk.ijse.serenitymentalhealth.bo.custom.impl;

import lk.ijse.serenitymentalhealth.bo.custom.PatientBO;
import lk.ijse.serenitymentalhealth.dao.DAOFactory;
import lk.ijse.serenitymentalhealth.dao.custom.PatientDAO;
import lk.ijse.serenitymentalhealth.dao.custom.QueryDAO;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.dto.PatientSessionHistoryDTO;
import lk.ijse.serenitymentalhealth.entity.Patient;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientBOImpl implements PatientBO {

    PatientDAO patientDAO = (PatientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PATIENT);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

    public boolean savePatient(PatientDTO patientDTO){

        Patient patient = new Patient();
        patient.setPatientName(patientDTO.getPatientName());
        patient.setAge(patientDTO.getPatientAge());
        patient.setAddress(patientDTO.getPatientAddress());
        patient.setPhone(patientDTO.getPatientPhone());
        patient.setGuardianName(patientDTO.getGuardianName());
        patient.setGuardianPhone(patientDTO.getGuardianPhone());

        return patientDAO.save(patient);
    }

    public boolean updatePatient(PatientDTO patientDTO){
        Patient patient = new Patient();
        patient.setPatientId(patientDTO.getPatientId());
        patient.setPatientName(patientDTO.getPatientName());
        patient.setAge(patientDTO.getPatientAge());
        patient.setPhone(patientDTO.getPatientPhone());
        patient.setAddress(patientDTO.getPatientAddress());
        patient.setGuardianName(patientDTO.getGuardianName());
        patient.setGuardianPhone(patientDTO.getGuardianPhone());

        return patientDAO.update(patient);
    }

    public boolean deletePatient(int id){
        return patientDAO.delete(id);
    }

    public String showNextId() throws SQLException {
        String id = patientDAO.showNextId();
        return id;
    }

    public List<PatientDTO> loadPatientTable() throws SQLException {
        List<Patient> patientList = patientDAO.getAll();
        List<PatientDTO> patientDTOList = new ArrayList<>();
        for (Patient patient : patientList) {
            patientDTOList.add(new PatientDTO(patient.getPatientId(),patient.getPatientName(),patient.getAge(),patient.getAddress(), patient.getPhone(), patient.getGuardianName(), patient.getGuardianPhone()));
        }
        return patientDTOList;
    }

    public List<PatientSessionHistoryDTO> getPatientSessionHistory(int id) throws SQLException {
        List<Object[]> rawResults = queryDAO.getPatientSessionHistory(id);

        List<PatientSessionHistoryDTO> dtoList = new ArrayList<>();

        for (Object[] row : rawResults) {
            int sessionId        = (int)row[0];
            String therapistName = (String)row[1];
            LocalDate date       = (LocalDate)row[2];
            String programName   = (String)    row[3];

            dtoList.add(new PatientSessionHistoryDTO(sessionId, therapistName, date,programName));
        }

        return dtoList;
    }
}
