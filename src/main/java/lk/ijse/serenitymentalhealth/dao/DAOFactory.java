package lk.ijse.serenitymentalhealth.dao;

import lk.ijse.serenitymentalhealth.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory instance;
    private  DAOFactory() {}
    public static DAOFactory getInstance() {
        return instance == null ? instance = new DAOFactory() : instance;
    }
    public enum DAOTypes {
        PATIENT,
        PATIENT_SESSION,
        REGISTRATION,
        THERAPIST,
        THERAPY_PROGRAM,
        THERAPY_SESSION,
        USER
    }
    public SuperDAO getDAO(DAOTypes daoType){
        switch (daoType){
            case PATIENT:
                return new PatientDAOImpl();
            case PATIENT_SESSION:
                return new PatientSessionDAOImpl();
            case REGISTRATION:
                return new RegistrationDAOImpl();
            case THERAPIST:
                return new TherapistDAOImpl();
            case THERAPY_PROGRAM:
                return new TherapyProgramDAOImpl();
            case THERAPY_SESSION:
                return new TherapySessionDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }

}

