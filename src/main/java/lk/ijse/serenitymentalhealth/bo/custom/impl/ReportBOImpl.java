package lk.ijse.serenitymentalhealth.bo.custom.impl;

import lk.ijse.serenitymentalhealth.bo.custom.ReportBO;
import lk.ijse.serenitymentalhealth.dao.DAOFactory;
import lk.ijse.serenitymentalhealth.dao.custom.QueryDAO;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.entity.Patient;

import java.util.ArrayList;
import java.util.List;

public class ReportBOImpl implements ReportBO {

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

    public List<PatientDTO> getAllPatientsInAllPrograms(){
        List<Patient> patientList = queryDAO.getPatientsInAllPrograms();
        List<PatientDTO> patientDTOList = new ArrayList<>();
        for (Patient patient : patientList) {
            patientDTOList.add(new PatientDTO(patient.getPatientId(), patient.getPatientName()));
        }
        return patientDTOList;
    }
}
