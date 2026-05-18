package lk.ijse.serenitymentalhealth.bo.custom.impl;

import lk.ijse.serenitymentalhealth.bo.custom.RegistrationBO;
import lk.ijse.serenitymentalhealth.dao.DAOFactory;
import lk.ijse.serenitymentalhealth.dao.custom.PatientDAO;
import lk.ijse.serenitymentalhealth.dao.custom.RegistrationDAO;
import lk.ijse.serenitymentalhealth.dao.custom.TherapyProgramDAO;
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

public class RegistrationBOImpl implements RegistrationBO {

    RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.REGISTRATION);
    PatientDAO patientDAO = (PatientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PATIENT);
    TherapyProgramDAO therapyProgramDAO = (TherapyProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPY_PROGRAM);

    public boolean saveRegistration(RegistrationDTO registrationDTO) throws SQLException {

        Registration registration = new Registration();
        Patient patient = new Patient();
        TherapyProgram therapyProgram = new TherapyProgram();

        patient.setPatientId(registrationDTO.getPatientId());
        therapyProgram.setTherapyProgramId(registrationDTO.getProgramId());
        registration.setPatient(patient);
        registration.setTherapyProgram(therapyProgram);
        registration.setRegistrationFee(registrationDTO.getFee());
        registration.setRegisteredDate(registrationDTO.getDate());

        double programPrice = therapyProgramDAO.getPriceById(registrationDTO.getProgramId());
        if(registrationDTO.getFee() < programPrice){
            registration.setPaymentStatus(PaymentStatus.PENDING);
        }
        else{
            registration.setPaymentStatus(PaymentStatus.COMPLETED);
        }

        return registrationDAO.save(registration);
    }

    public boolean updateRegistration(RegistrationDTO registrationDTO) throws SQLException {

        System.out.println("Program ID in BO: " + registrationDTO.getProgramId());
        double programPrice1 = therapyProgramDAO.getPriceById(registrationDTO.getProgramId());
        System.out.println("Program Price: " + programPrice1);
        System.out.println("Fee paid: " + registrationDTO.getFee());

        Registration registration = new Registration();
        Patient patient = new Patient();
        TherapyProgram therapyProgram = new TherapyProgram();
        registration.setRegistrationId(registrationDTO.getRegistrationId());
        patient.setPatientId(registrationDTO.getPatientId());
        therapyProgram.setTherapyProgramId(registrationDTO.getProgramId());
        registration.setPatient(patient);
        registration.setTherapyProgram(therapyProgram);
        registration.setRegistrationFee(registrationDTO.getFee());
        double programPrice = therapyProgramDAO.getPriceById(registrationDTO.getProgramId());
        if(registrationDTO.getFee() < programPrice){
            registration.setPaymentStatus(PaymentStatus.PENDING);
        }
        else{
            registration.setPaymentStatus(PaymentStatus.COMPLETED);
        }
        registration.setRegisteredDate(registrationDTO.getDate());


        return registrationDAO.update(registration);
    }

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

    public String getIdByName(String name) throws SQLException {
        return therapyProgramDAO.getIdByName(name);
    }

    public List<RegistrationDTO> loadRegistrationData(String name) throws SQLException {
        List<Registration> registrationList = registrationDAO.loadRegisterationData(name);
        List<RegistrationDTO> registrationDTOList = new ArrayList<>();
        for (Registration registration : registrationList) {
            registrationDTOList.add(new RegistrationDTO(registration.getRegistrationId(), registration.getPatient().getPatientId(), registration.getTherapyProgram().getTherapyProgramId(),registration.getRegistrationFee(),registration.getRegisteredDate(),registration.getPaymentStatus()));
        }
        return registrationDTOList;
    }

    public double getPriceById(String id) throws SQLException {
        return therapyProgramDAO.getPriceById(id);
    }

}
