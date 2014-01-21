package com.bam.helper;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBConnection {
	private  static DBConnection connection = null;
	
	private DBConnection(){}
	
	public  static DBConnection getInstance(){
		if(connection==null){
			connection = new DBConnection();
		}
		return connection;
	}
	
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
