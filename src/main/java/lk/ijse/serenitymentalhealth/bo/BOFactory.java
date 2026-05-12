package lk.ijse.serenitymentalhealth.bo;

import lk.ijse.serenitymentalhealth.bo.custom.impl.PatientBOImpl;
import lk.ijse.serenitymentalhealth.bo.custom.impl.TherapistBOImpl;
import lk.ijse.serenitymentalhealth.bo.custom.impl.TherapyProgramBOImpl;
import lk.ijse.serenitymentalhealth.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory instance;
    private BOFactory(){}
    public static BOFactory getInstance() {
        return instance==null?instance=new BOFactory():instance;
    }
    public enum BOTypes{
        PATIENT,THERAPIST,THERAPY_PROGRAM,THERAPY_SESSION,USER
    }
    public SuperBO getBOFactory(BOTypes boType){
        switch (boType){
            case PATIENT:
                return new PatientBOImpl();
            case THERAPIST:
                return new TherapistBOImpl();
            case THERAPY_PROGRAM:
                return new TherapyProgramBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }

}

