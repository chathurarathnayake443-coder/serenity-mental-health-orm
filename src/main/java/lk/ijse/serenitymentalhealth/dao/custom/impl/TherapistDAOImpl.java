package lk.ijse.serenitymentalhealth.dao.custom.impl;

import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dao.custom.TherapistDAO;
import lk.ijse.serenitymentalhealth.entity.Patient;
import lk.ijse.serenitymentalhealth.entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class TherapistDAOImpl implements TherapistDAO {
    @Override
    public boolean save(Therapist entity) throws SQLException {
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
    public boolean update(Therapist entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Therapist oldTherapist = session.find(Therapist.class,entity.getTherapistId());
            oldTherapist.setTherapistName(entity.getTherapistName());
            oldTherapist.setTherapistEmail(entity.getTherapistEmail());
            oldTherapist.setTherapistPhone(entity.getTherapistPhone());
            oldTherapist.setTherapistAddress(entity.getTherapistAddress());
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
    public boolean delete(int id) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Therapist entity = (Therapist)session.get(Therapist.class,id);
            session.remove(entity);
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
    public String showNextId() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Integer maxId = session.createQuery("SELECT MAX(t.therapistId) FROM Therapist t", Integer.class)
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
    public List<Therapist> getAll() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            List<Therapist> therapistList = session.createQuery("from Therapist",Therapist.class).list();
            transaction.commit();
            return therapistList;
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

    @Override
    public Therapist find(String name) throws SQLException {
        return null;
    }

    public String getNameById(int id) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            String name = session.createQuery("SELECT t.therapistName FROM Therapist t WHERE t.therapistId = :therapist_id", String.class).setParameter("therapist_id",id).setMaxResults(1).uniqueResult();
            transaction.commit();
            return name;
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
