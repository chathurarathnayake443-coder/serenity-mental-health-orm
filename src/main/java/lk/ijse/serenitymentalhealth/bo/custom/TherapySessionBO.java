package lk.ijse.serenitymentalhealth.bo.custom;

import lk.ijse.serenitymentalhealth.bo.SuperBO;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;
import lk.ijse.serenitymentalhealth.entity.Therapist;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TherapySessionBO extends SuperBO {

    public String showNextId() throws SQLException;

    public List<TherapistDTO> loadTherapistIds() throws SQLException;
}
