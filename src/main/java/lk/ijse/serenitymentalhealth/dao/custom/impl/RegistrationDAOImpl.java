package lk.ijse.serenitymentalhealth.dao.custom.impl;

import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dao.custom.RegistrationDAO;
import lk.ijse.serenitymentalhealth.entity.Registration;
import lk.ijse.serenitymentalhealth.entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public boolean save(Registration entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            session.persist(entity);
            transaction.commit();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        finally{
            session.close();
        }
        return false;
    }

    @Override
    public boolean update(Registration entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public String showNextId() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Integer maxId = session.createQuery("SELECT MAX(r.registrationId) FROM Registration r", Integer.class)
                    .uniqueResult();
            transaction.commit();
            return (maxId == null) ? "1" : String.valueOf(maxId + 1);
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return "1";
    }

    @Override
    public List<Registration> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public Registration find(String name) throws SQLException {
        return null;
    }

    public List<Registration> loadRegisterationData(String name){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            List<Registration> regData = session.createQuery("FROM Registration r WHERE r.therapyProgram.therapyProgramName = :program_name", Registration.class).setParameter("program_name",name).list();
            transaction.commit();
            return regData;
        }
        catch(Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        finally{
            session.close();
        }
        return null;
    }
}
