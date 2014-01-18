package com.bam.services;

import com.bam.dto.Students;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.bam.helper.*;

public class RegisterService {
	DBConnection connection = new DBConnection();
	public boolean checkAvailableUser(String email){
		List<Students> users=null;
		Session session=null;
		try {
			session =connection.getSession();
			session.beginTransaction();
			Criteria criteria =session.createCriteria(Students.class);
			criteria.add(Restrictions.like("email", email));
			users= (List<Students>)criteria.list();
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
		String salt=null;
		try {
			session = connection.getSession();
			session.beginTransaction();
			Students st = new Students();
			st.setFirstName(HelperClass.escapeHtml(map.get("fName")[0]));
			st.setlName(HelperClass.escapeHtml(map.get("lName")[0]));
			st.setDepartment(HelperClass.escapeHtml(map.get("faculty")[0]));
			st.setProgram(HelperClass.escapeHtml(map.get("program")[0]));
			st.setPhoneNumber(Long.parseLong(HelperClass.escapeHtml(map.get("phoneNumber")[0])));
			st.setGender(HelperClass.escapeHtml(map.get("gender")[0]));
			st.setEmail(HelperClass.escapeHtml(map.get("email")[0]));
			st.setRegisterDate(new Date());
			st.setLibrary(HelperClass.escapeHtml(map.get("library")[0]));
			byte[] pic={};
			st.setProfile_picture(pic);
			salt=HelperClass.generateRandomWord();
			String passwordEncrypted= HelperClass.generateSaltedEncrypted(map.get("password")[0],salt);
			st.setPassword_salt(salt);
			st.setPassword(passwordEncrypted);
			session.save(st);
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
