package lk.ijse.serenitymentalhealth.bo.custom.impl;

import lk.ijse.serenitymentalhealth.bo.custom.TherapySessionBO;
import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dao.DAOFactory;
import lk.ijse.serenitymentalhealth.dao.custom.*;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;
import lk.ijse.serenitymentalhealth.dto.TherapyProgramDTO;
import lk.ijse.serenitymentalhealth.dto.TherapySessionDTO;
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
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    PatientSessionDAO patientSessionDAO = (PatientSessionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PATIENT_SESSION);

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

    public List<TherapistDTO> loadTherapistIdsByProgram(String id) throws SQLException {
        List<Therapist> therapistList = queryDAO.getTherapistsByProgramId(id);
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

//    public boolean createSession(int hours,int minutes,int duration,LocalDate date,int therapistId, String programId, List<PatientDTO> patientDTOList) throws SQLException {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//
//        try{
//            LocalTime startTime = LocalTime.of(hours, minutes);
//            LocalTime endTime = startTime.plusMinutes(duration);
//
//            //checking therapist availability
//
//            boolean available = therapySessionDAO.isTherapistAvailable(
//                    therapistId, date, startTime, endTime, session);
//
//            if (!available) {  // not available status
//                transaction.rollback();
//                return false;
//            }
//
//            Therapist therapist = session.get(Therapist.class, therapistId);
//            TherapyProgram program = session.get(TherapyProgram.class, programId);
//
//            TherapySession therapySession = new TherapySession();
//            therapySession.setDate(date);
//            therapySession.setStartTime(startTime);
//            therapySession.setTimeDuration(duration);
//            therapySession.setStatus(SessionStatus.PENDING);
//            therapySession.setTherapist(therapist);
//            therapySession.setTherapyProgram(program);
//
//            therapySessionDAO.save(therapySession, session);
//
//            for (PatientDTO patientDTO : patientDTOList) {
//                Patient patient = session.get(Patient.class, patientDTO.getPatientId());
//
//                PatientSession patientSession = new PatientSession();
//                patientSession.setPatient(patient);
//                patientSession.setTherapySession(therapySession);
//                patientSession.setSessionFee(0.0);
//                patientSession.setPaymentStatus(PaymentStatus.PENDING);
//
//                // step 7 — save patient session using shared session
//                patientSessionDAO.save(patientSession, session);
//            }
//
//        }
//        catch(Exception e){
//            e.printStackTrace();
//            transaction.rollback();
//        }
//        return false;
//    }

    public boolean createSession(int hours, int minutes, int duration, LocalDate date,
                                 int therapistId, String programId,
                                 List<PatientDTO> patientDTOList) throws SQLException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            LocalTime startTime = LocalTime.of(hours, minutes);
            LocalTime endTime = startTime.plusMinutes(duration);

            // Check therapist availability
            boolean available = therapySessionDAO.isTherapistAvailable(
                    therapistId, date, startTime, endTime, session);

            if (!available) {
                new javafx.scene.control.Alert(
                        javafx.scene.control.Alert.AlertType.WARNING,
                        "Therapist is not available at this time."
                ).show();
                transaction.rollback();
                return false;
            }

            Therapist therapist = session.get(Therapist.class, therapistId);
            TherapyProgram program = session.get(TherapyProgram.class, programId);

            if (therapist == null) {
                throw new RuntimeException("Therapist not found with ID: " + therapistId);
            }
            if (program == null) {
                throw new RuntimeException("Program not found with ID: " + programId);
            }

            TherapySession therapySession = new TherapySession();
            therapySession.setDate(date);
            therapySession.setStartTime(startTime);
            therapySession.setTimeDuration(duration);
            therapySession.setStatus(SessionStatus.PENDING);
            therapySession.setTherapist(therapist);
            therapySession.setTherapyProgram(program);

            therapySessionDAO.save(therapySession, session);

            for (PatientDTO patientDTO : patientDTOList) {
                Patient patient = session.get(Patient.class, patientDTO.getPatientId());

                if (patient == null) {
                    throw new RuntimeException("Patient not found with ID: " + patientDTO.getPatientId());
                }

                PatientSession patientSession = new PatientSession();
                patientSession.setPatient(patient);
                patientSession.setTherapySession(therapySession);
                patientSession.setSessionFee(0.0);
                patientSession.setPaymentStatus(PaymentStatus.PENDING);

                patientSessionDAO.save(patientSession, session);
            }

            transaction.commit();
            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public List<Integer> loadSessionIds() throws SQLException {
        List<TherapySession> list = therapySessionDAO.getAll();
        List<Integer> sessionIdList = new ArrayList<>();
        for (TherapySession therapySession : list) {
            sessionIdList.add(therapySession.getTherapySessionId());
        }
        return sessionIdList;
    }

    public TherapySessionDTO getSessionData(int id){
        TherapySession sessionData = queryDAO.getSessionData(id);
        List<PatientDTO> patientNames = new ArrayList<>();
        List<PatientSession> patientSessionList = sessionData.getPatientSessions();
        for (PatientSession patientSession : patientSessionList) {
            patientNames.add(new PatientDTO(patientSession.getPatient().getPatientId(), patientSession.getPatient().getPatientName()));
        }
        return new TherapySessionDTO(sessionData.getTherapyProgram().getTherapyProgramName(), sessionData.getTherapist().getTherapistName(),sessionData.getDate(),sessionData.getStartTime(),sessionData.getTimeDuration(),sessionData.getStatus(),patientNames);
    }

    public boolean cancelSession(int sessionId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            boolean result = therapySessionDAO.cancelSession(sessionId, session);
            transaction.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean rescheduleSession(int sessionId, LocalDate newDate, int hours, int minutes, int duration) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            LocalTime newStartTime = LocalTime.of(hours, minutes);
            LocalTime newEndTime   = newStartTime.plusMinutes(duration);

            boolean result = therapySessionDAO.rescheduleSession(
                    sessionId, newDate, newStartTime, newEndTime, session);

            transaction.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }
}
