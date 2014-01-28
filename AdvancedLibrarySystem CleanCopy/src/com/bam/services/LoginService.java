package com.bam.services;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.bam.dto.Library;
import com.bam.dto.Students;
import com.bam.helper.DBConnection;
import com.bam.helper.HelperClass;

public class LoginService {
	
	private static LoginService loginService= null;
	
	private LoginService(){}
	
	public  static LoginService getInstance(){
		if(loginService == null){
			loginService= new LoginService();
		}
		return loginService;
	}
	
	public List<Students> logStudentIn(String email,String password){
		Session session=null;
		List<Students> users=null;
		DBConnection connection =DBConnection.getInstance();
		PasswordService ps = PasswordService.getInstance();
		
		try {
			session = connection.getSession();
			session.beginTransaction();
				Criteria criteria =session.createCriteria(Students.class)
						.add(Restrictions.like("email", email))
						.add(Restrictions.like("password",HelperClass.generateSaltedEncrypted(password, ps.getPasswordSalt(email))));
				users=criteria.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return users;
	}
	
	public List<Library> logAdminIn(String email, String password){
		Session session=null;
		List<Library> admin=null;
		DBConnection connection =DBConnection.getInstance();
		try {
			session = connection.getSession();
			session.beginTransaction();
				Criteria criteria2 = session.createCriteria(Library.class)
						.add(Restrictions.like("libraryUserName", email))
						.add(Restrictions.like("libraryPassword",HelperClass.toSHA1(password.getBytes())));
				admin= criteria2.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return admin;
	}
}
