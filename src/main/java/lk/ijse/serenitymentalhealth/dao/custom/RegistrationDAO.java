package lk.ijse.serenitymentalhealth.dao.custom;

import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dao.CrudDAO;
import lk.ijse.serenitymentalhealth.entity.Patient;
import lk.ijse.serenitymentalhealth.entity.Registration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public interface RegistrationDAO extends CrudDAO<Registration> {

    public List<Registration> loadRegisterationData(String name);

    public List<Patient> getPatientsByProgramId(String programId);
}
