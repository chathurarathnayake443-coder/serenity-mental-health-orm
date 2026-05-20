package lk.ijse.serenitymentalhealth.bo.custom;

import lk.ijse.serenitymentalhealth.bo.SuperBO;
import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;
import lk.ijse.serenitymentalhealth.dto.TherapySessionDTO;
import lk.ijse.serenitymentalhealth.entity.Therapist;
import lk.ijse.serenitymentalhealth.entity.TherapySession;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TherapistBO extends SuperBO {

    public boolean saveTherapist(TherapistDTO therapistDTO) throws SQLException;

    public boolean updateTherapist(TherapistDTO therapistDTO) throws SQLException;

    public boolean deleteTherapist(int id) throws SQLException;

    public String showNextId() throws SQLException;

    public List<TherapistDTO> loadTherapistTable() throws SQLException;

    public boolean assignTherapistToProgram(String programId, int therapistId) throws SQLException;

    public boolean removeTherapistFromProgram(String programId, int therapistId) throws SQLException;

    public List<TherapySessionDTO> getSessionsByTherapistId(int therapistId);
}
