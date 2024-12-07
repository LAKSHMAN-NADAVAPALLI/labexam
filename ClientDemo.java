package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {

    private static SessionFactory factory;

    public static void main(String[] args) {
        try {
            // Create a SessionFactory from hibernate.cfg.xml
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        // Insert a new client record
        Client newClient = new Client();
        newClient.setName("lakshman");
        newClient.setGender("Male");
        newClient.setAge(30);
        newClient.setLocation("INDIA");
        newClient.setEmailAddress("LAKSHMAN@example.com");
        newClient.setMobileNumber("1234567890");
  
        insertClient(newClient);
    }

    public static void insertClient(Client client) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(client);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
