package lk.ijse.serenitymentalhealth.dao.custom.impl;

import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dao.custom.TherapyProgramDAO;
import lk.ijse.serenitymentalhealth.entity.Patient;
import lk.ijse.serenitymentalhealth.entity.Therapist;
import lk.ijse.serenitymentalhealth.entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class TherapyProgramDAOImpl implements TherapyProgramDAO {
    @Override
    public boolean save(TherapyProgram entity) throws SQLException {
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
    public boolean update(TherapyProgram entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            TherapyProgram oldProgram = session.find(TherapyProgram.class,entity.getTherapyProgramId());
            oldProgram.setTherapyProgramId(entity.getTherapyProgramId());
            oldProgram.setTherapyProgramName(entity.getTherapyProgramName());
            oldProgram.setDuration(entity.getDuration());
            oldProgram.setCost(entity.getCost());
            oldProgram.setDescription(entity.getDescription());
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
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            TherapyProgram entity = (TherapyProgram) session.get(TherapyProgram.class,id);
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
        return "";
    }

    @Override
    public List<TherapyProgram> getAll() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            List<TherapyProgram> therapyProgramList = session.createQuery("from TherapyProgram ",TherapyProgram.class).list();
            transaction.commit();
            return therapyProgramList;
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
    public TherapyProgram find(String name) throws SQLException {
        return null;
    }

    public String getIdByName(String name) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            String id = session.createQuery("SELECT t.therapyProgramId FROM TherapyProgram t WHERE t.therapyProgramName = :program_name", String.class).setParameter("program_name",name).setMaxResults(1).uniqueResult();
            transaction.commit();
            return id;
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

    public String getNameById(String id) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            String name = session.createQuery("SELECT t.therapyProgramName FROM TherapyProgram t WHERE t.therapyProgramId = :program_id", String.class).setParameter("program_id",id).setMaxResults(1).uniqueResult();
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

    public double getPriceById(String id) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            double price = session.createQuery("SELECT t.cost FROM TherapyProgram t WHERE t.therapyProgramId = :program_id", Double.class).setParameter("program_id",id).setMaxResults(1).uniqueResult();
            transaction.commit();
            return price;
        }
        catch(Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        finally{
            session.close();
        }
        return 0;
    }

    public List<Therapist> loadProgramTherapists(String name) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            List<Therapist> therapistList = session.createQuery("select t.therapists from TherapyProgram t where t.therapyProgramName = :program_name", Therapist.class).setParameter("program_name",name).list();
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
}
