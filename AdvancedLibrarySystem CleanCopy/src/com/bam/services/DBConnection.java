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
   // Call this during shutdown
   public static void close() {
        factory.close();
   }
}
