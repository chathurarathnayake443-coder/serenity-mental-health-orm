package lk.ijse.serenitymentalhealth.bo.custom.impl;

import lk.ijse.serenitymentalhealth.bo.custom.TherapySessionBO;
import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dao.DAOFactory;
import lk.ijse.serenitymentalhealth.dao.custom.PatientDAO;
import lk.ijse.serenitymentalhealth.dao.custom.TherapistDAO;
import lk.ijse.serenitymentalhealth.dao.custom.TherapySessionDAO;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;
import lk.ijse.serenitymentalhealth.entity.*;
import lk.ijse.serenitymentalhealth.enums.PaymentStatus;
import lk.ijse.serenitymentalhealth.enums.SessionStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TherapySessionBOImpl implements TherapySessionBO {

    TherapySessionDAO therapySessionDAO = (TherapySessionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPY_SESSION);
    TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPIST);
    PatientDAO patientDAO = (PatientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PATIENT);

    public String showNextId() throws SQLException {
        String id = therapySessionDAO.showNextId();
        return id;
    }

    public List<TherapistDTO> loadTherapistIds() throws SQLException {
        List<Therapist> therapistList = therapistDAO.getAll();
        List<TherapistDTO> therapistDTOList = new ArrayList<>();
        for (Therapist therapist : therapistList) {
            therapistDTOList.add(new TherapistDTO(therapist.getTherapistId()));
        }
        return therapistDTOList;
    }

    public List<PatientDTO> loadPatientIds() throws SQLException {
        List<Patient> patientList = patientDAO.getAll();
        List<PatientDTO> patientDTOList = new ArrayList<>();
        for (Patient patient : patientList) {
            patientDTOList.add(new PatientDTO(patient.getPatientId()));
        }
        return patientDTOList;
    }

    public String getTherapistNameById(int id) throws SQLException {
        String name = therapistDAO.getNameById(id);
        return name;
    }

    public String getPatientNameById(int id) throws SQLException {
        String name = patientDAO.getNameById(id);
        return name;
    }

    public boolean createSession(int hours,int minutes,int duration,LocalDate date,int therapistId){
        return false;
    }
}
