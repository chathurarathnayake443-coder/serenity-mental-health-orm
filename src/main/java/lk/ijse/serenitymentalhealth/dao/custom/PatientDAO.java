package lk.ijse.serenitymentalhealth.dao.custom;

import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dao.CrudDAO;
import lk.ijse.serenitymentalhealth.entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;

public interface PatientDAO extends CrudDAO<Patient> {

    public boolean save(Patient entity);

    public boolean update(Patient entity);
}
