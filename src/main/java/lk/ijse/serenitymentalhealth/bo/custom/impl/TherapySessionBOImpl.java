package lk.ijse.serenitymentalhealth.bo.custom.impl;

import lk.ijse.serenitymentalhealth.bo.custom.TherapySessionBO;
import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dao.DAOFactory;
import lk.ijse.serenitymentalhealth.dao.custom.*;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;
import lk.ijse.serenitymentalhealth.dto.TherapyProgramDTO;
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
    TherapyProgramDAO therapyProgramDAO = (TherapyProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPY_PROGRAM);
    RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.REGISTRATION);

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

    public List<PatientDTO> loadPatientIdsByProgram(String id) throws SQLException {
        List<Patient> patientList = registrationDAO.getPatientsByProgramId(id);
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

    public String getProgramNameById(String id) throws SQLException {
        String name = therapyProgramDAO.getNameById(id);
        return name;
    }

    public List<TherapyProgramDTO> loadTherapyProgramTable() throws SQLException {
        List<TherapyProgram> therapyProgramList = therapyProgramDAO.getAll();
        List<TherapyProgramDTO> therapyProgramDTOList = new ArrayList<>();
        for (TherapyProgram therapyProgram : therapyProgramList) {
            therapyProgramDTOList.add(new TherapyProgramDTO(therapyProgram.getTherapyProgramId(), therapyProgram.getTherapyProgramName(), therapyProgram.getDescription(), String.valueOf(therapyProgram.getDuration()), therapyProgram.getCost()));
        }
        return therapyProgramDTOList;
    }

    public boolean createSession(int hours,int minutes,int duration,LocalDate date,int therapistId, String programId, List<PatientDTO> patientDTOList) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            LocalTime startTime = LocalTime.of(hours, minutes);
            LocalTime endTime = startTime.plusMinutes(duration);


            //checking therapist availability

            boolean available = therapySessionDAO.isTherapistAvailable(
                    therapistId, date, startTime, endTime, session);

            if (!available) {  // not available status
                transaction.rollback();
                return false;
            }

            Therapist therapist = session.get(Therapist.class, therapistId);
            TherapyProgram program = session.get(TherapyProgram.class, programId);

            TherapySession therapySession = new TherapySession();
            therapySession.setDate(date);
            therapySession.setStartTime(startTime);
            therapySession.setTimeDuration(duration);
            therapySession.setStatus(SessionStatus.PENDING);
            therapySession.setTherapist(therapist);
            therapySession.setTherapyProgram(program);

            therapySessionDAO.save(therapySession, session);
        }
        catch(Exception e){

        }
        return false;
    }
}
