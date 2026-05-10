package lk.ijse.serenitymentalhealth.config;

import lk.ijse.serenitymentalhealth.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {

        sessionFactory = new Configuration()
//                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Patient.class)
                .addAnnotatedClass(PatientSession.class)
                .addAnnotatedClass(Registration.class)
                .addAnnotatedClass(Therapist.class)
                .addAnnotatedClass(TherapyProgram.class)
                .addAnnotatedClass(TherapySession.class)
                .buildSessionFactory();

    }

    public static FactoryConfiguration getInstance(){
        return factoryConfiguration == null ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

}

