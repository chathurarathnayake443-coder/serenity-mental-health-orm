package lk.ijse.serenitymentalhealth.dao.custom;

import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dao.CrudDAO;
import lk.ijse.serenitymentalhealth.entity.Therapist;
import lk.ijse.serenitymentalhealth.entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;

public interface TherapistDAO extends CrudDAO<Therapist> {

    public String getNameById(int id) throws SQLException;

    public boolean assignProgramToTherapist(String programId, int therapistId) throws SQLException;

    public boolean removeTherapistFromProgram(String programId, int therapistId) throws SQLException;
}
