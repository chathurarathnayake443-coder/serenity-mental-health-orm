package lk.ijse.serenitymentalhealth.bo;

import lk.ijse.serenitymentalhealth.bo.custom.impl.PatientBOImpl;

public class BOFactory {
    private static BOFactory instance;
    private BOFactory(){}
    public static BOFactory getInstance() {
        return instance==null?instance=new BOFactory():instance;
    }
    public enum BOTypes{
        PATIENT,THERAPIST,THERAPY_PROGRAM,THERAPY_SESSION
    }
    public SuperBO getBOFactory(BOTypes boType){
        switch (boType){
            case PATIENT:
                return new PatientBOImpl();

            default:
                return null;
        }
    }

}

