package lk.ijse.serenitymentalhealth.bo.custom;

import lk.ijse.serenitymentalhealth.bo.SuperBO;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;

public interface PatientBO extends SuperBO {

    public boolean savePatient(PatientDTO patientDTO);
}
