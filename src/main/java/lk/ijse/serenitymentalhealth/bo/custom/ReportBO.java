package lk.ijse.serenitymentalhealth.bo.custom;

import lk.ijse.serenitymentalhealth.bo.SuperBO;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.entity.Patient;

import java.util.ArrayList;
import java.util.List;

public interface ReportBO extends SuperBO {

    public List<PatientDTO> getAllPatientsInAllPrograms();
}
