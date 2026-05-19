package lk.ijse.serenitymentalhealth.dao.custom.impl;

import com.mysql.cj.xdevapi.SessionFactory;
import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dao.custom.QueryDAO;
import lk.ijse.serenitymentalhealth.entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    public List<Therapist> getTherapistsByProgramId(String programId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            List<Therapist> therapists = session.createQuery(
                            "SELECT t FROM Therapist t JOIN t.therapyPrograms tp WHERE tp.therapyProgramId = :programId",
                            Therapist.class)
                    .setParameter("programId", programId)
                    .list();
            transaction.commit();
            return therapists;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return null;
    }
}
