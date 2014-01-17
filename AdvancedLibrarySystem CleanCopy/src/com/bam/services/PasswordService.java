package com.bam.services;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.bam.dto.PasswordChange;
import com.bam.helper.*;

public class PasswordService {
	DBConnection connection = new DBConnection();
	StudentService sc = new StudentService();
	
	public void saveToken(String token,Date expireDate,int idNumber){
		Session session = null;
		try{
			session = connection.getSession();
			session.beginTransaction();
			PasswordChange pc = new PasswordChange();
			pc.setToken(token);
			pc.setEmail(sc.getStudent(idNumber).get(0).getEmail());
			pc.setTokenExpirationTime(expireDate);
			pc.setIdNumber(idNumber);
			session.save(pc);
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public boolean isTokenValidAndMatch(String token,String email){
			Session session = null;
			List<PasswordChange> tokens=null;
			try{
				session = connection.getSession();
				session.beginTransaction();
				Criteria criteria = session.createCriteria(PasswordChange.class)
										.add(Restrictions.like("token", token))
										.add(Restrictions.like("email", email));
				
				tokens=criteria.list();
				session.getTransaction().commit();
			}catch(HibernateException e){
				e.printStackTrace();
			}finally{
				session.close();
			}
			if(tokens.isEmpty() || tokens == null){
				return false;
			}else{
				return true;
			}
		}
	
	public PasswordChange getToken(String token){
		Session session =null;
		PasswordChange tokenToReturn=null;
		try{
			session=connection.getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(PasswordChange.class)
					.add(Restrictions.like("token", token));
			tokenToReturn=(PasswordChange) criteria.list().get(0);
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return tokenToReturn;
	}
}
