package lk.ijse.serenitymentalhealth.bo.custom;

import lk.ijse.serenitymentalhealth.bo.SuperBO;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;
import lk.ijse.serenitymentalhealth.entity.Therapist;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TherapistBO extends SuperBO {

    public boolean saveTherapist(TherapistDTO therapistDTO) throws SQLException;

    public String showNextId() throws SQLException;

    public List<TherapistDTO> loadTherapistTable() throws SQLException;
}
