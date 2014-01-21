package com.bam.services;

import com.bam.dto.Library;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.bam.helper.*;

public class LibraryService {
	
	private static LibraryService libraryService= null;
	
	private LibraryService(){}
	
	public  static LibraryService getInstance(){
		if(libraryService == null){
			libraryService= new LibraryService();
		}
		return libraryService;
	}
	HelperClass hc = new HelperClass();
	DBConnection connection = DBConnection.getInstance();
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
			String passwordEncrypted= HelperClass.toSHA1(map.get("libraryPassword")[0].getBytes());
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
	
	public List<Library> getLibraries(){
		Session session = null;
		List<Library> libraries = null;
		try{
			session = connection.getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Library.class);
			libraries = criteria.list();
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return libraries;
	}
}
