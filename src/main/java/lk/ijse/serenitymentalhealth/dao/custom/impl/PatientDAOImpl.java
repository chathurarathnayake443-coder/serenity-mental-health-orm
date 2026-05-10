package lk.ijse.serenitymentalhealth.dao.custom.impl;

import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dao.custom.PatientDAO;
import lk.ijse.serenitymentalhealth.entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PatientDAOImpl implements PatientDAO {

    public boolean save(Patient entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.persist(entity);
            transaction.commit();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        finally{
            session.close();
        }
        return false;
    }
}
