package com.bam.services;

import com.bam.dto.Students;
import com.bam.dto.Library;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class LibraryRegisterService {
	HelperClass hc = new HelperClass();
	DBConnection connection = new DBConnection();
	public boolean checkAvailableUser(String email){
		List<Library> users=null;
		Session session=null;
		try {
			session = connection.getSession();
			session.beginTransaction();
			Criteria criteria =session.createCriteria(Library.class);
			criteria.add(Restrictions.like("libraryEmail", email));
			users= (List<Library>)criteria.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		if (users.isEmpty()){
			return true;
		}
		return false;
	}
	
	public void saveData(Map<String, String[]> map){
		Session session=null;	
		try {
			session = connection.getSession();
			session.beginTransaction();
			Library lib = new Library();
			lib.setLibraryUserName(map.get("libraryUserName")[0]);
			lib.setLibraryEmail(map.get("libraryEmail")[0]);
			lib.setLibraryName(map.get("libraryName")[0]);
			lib.setLibraryPhoneNumber(Long.parseLong(map.get("libraryPhoneNumber")[0]));
			String passwordEncrypted= hc.toSHA1(map.get("libraryPassword")[0].getBytes());
			lib.setLibraryPassword(passwordEncrypted);
			session.save(lib);
			session.getTransaction().commit();
		} catch (NumberFormatException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (HibernateException e) {
			System.out.println(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
}
