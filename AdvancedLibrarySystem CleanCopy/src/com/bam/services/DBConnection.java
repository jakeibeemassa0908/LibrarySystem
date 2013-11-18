package com.bam.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBConnection {

    private static SessionFactory factory;
    static {
          factory = new Configuration().configure().buildSessionFactory();
    }

    public Session getSession() {
          return factory.openSession();
    }

    public void doWork() {
         Session session = getSession();
         // do work.
         session.close();
    }

   // Call this during shutdown
   public static void close() {
        factory.close();
   }
}
