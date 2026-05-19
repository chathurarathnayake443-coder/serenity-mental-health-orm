package lk.ijse.serenitymentalhealth.dao.custom;

import lk.ijse.serenitymentalhealth.dao.CrudDAO;
import lk.ijse.serenitymentalhealth.entity.TherapyProgram;
import lk.ijse.serenitymentalhealth.entity.TherapySession;
import org.hibernate.Session;

import java.time.LocalDate;
import java.time.LocalTime;

public interface TherapySessionDAO extends CrudDAO<TherapyProgram> {

    public boolean save(TherapySession therapySession, Session session);

    public boolean isTherapistAvailable(int therapistId, LocalDate date,
                                        LocalTime startTime, LocalTime endTime,
                                        Session session);
}
