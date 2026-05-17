package lk.ijse.serenitymentalhealth.bo.custom.impl;

import lk.ijse.serenitymentalhealth.bo.custom.RegistrationBO;
import lk.ijse.serenitymentalhealth.dao.DAOFactory;
import lk.ijse.serenitymentalhealth.dao.custom.PatientDAO;
import lk.ijse.serenitymentalhealth.dao.custom.RegistrationDAO;
import lk.ijse.serenitymentalhealth.dao.custom.TherapyProgramDAO;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.dto.TherapyProgramDTO;
import lk.ijse.serenitymentalhealth.entity.Patient;
import lk.ijse.serenitymentalhealth.entity.TherapyProgram;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationBOImpl implements RegistrationBO {

    RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.REGISTRATION);
    PatientDAO patientDAO = (PatientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PATIENT);
    TherapyProgramDAO therapyProgramDAO = (TherapyProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPY_PROGRAM);

    public String showNextId() throws SQLException {
        String id = registrationDAO.showNextId();
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

    public List<TherapyProgramDTO> loadProgramNames() throws SQLException {
        List<TherapyProgram> programList = therapyProgramDAO.getAll();
        List<TherapyProgramDTO> therapyProgramDTOList = new ArrayList<>();
        for (TherapyProgram therapyProgram : programList) {
            therapyProgramDTOList.add(new TherapyProgramDTO(therapyProgram.getTherapyProgramId(),therapyProgram.getTherapyProgramName()));
        }
        return therapyProgramDTOList;
    }
}
