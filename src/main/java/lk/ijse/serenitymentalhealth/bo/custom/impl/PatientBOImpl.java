package lk.ijse.serenitymentalhealth.bo.custom.impl;

import lk.ijse.serenitymentalhealth.bo.custom.PatientBO;
import lk.ijse.serenitymentalhealth.dao.DAOFactory;
import lk.ijse.serenitymentalhealth.dao.custom.PatientDAO;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.entity.Patient;

public class PatientBOImpl implements PatientBO {

    PatientDAO patientDAO = (PatientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PATIENT);

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
}
