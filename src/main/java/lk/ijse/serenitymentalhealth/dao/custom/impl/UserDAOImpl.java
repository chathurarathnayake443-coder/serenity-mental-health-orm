package lk.ijse.serenitymentalhealth.dao.custom.impl;

import com.google.protobuf.Enum;
import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dao.custom.UserDAO;
import lk.ijse.serenitymentalhealth.entity.Patient;
import lk.ijse.serenitymentalhealth.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User entity) throws SQLException {
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

    @Override
    public boolean update(User entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            User oldUser = session.find(User.class,entity.getUserName());
            oldUser.setName(entity.getName());
            oldUser.setUserPassword(entity.getUserPassword());
            oldUser.setUserContact(entity.getUserContact());
            oldUser.setUserAddress(entity.getUserAddress());
            oldUser.setUserType(entity.getUserType());
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
            User entity = (User)session.get(User.class,id);
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
    public List<User> getAll() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            List<User> userList = session.createQuery("from User",User.class).list();
            transaction.commit();
            return userList;
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
    public User find(String name) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            User user = session.find(User.class,name);
            transaction.commit();
            return user;
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
