package com.bam.services;

import com.bam.dto.Books;
import com.bam.dto.Messages;
import com.bam.dto.Students;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class StudentService {
	DBConnection connection = new DBConnection();
	public List getStudent(Integer id){
		List<Students> students=null;
		Session session1=null;
		try {
			session1 = connection.getSession();
			session1.beginTransaction();
			Criteria criteria =session1.createCriteria(Students.class);
			if (id!=null){
				criteria.add(Restrictions.like("StudentId", id));
			}
			students=criteria.list();
			session1.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println(e);
		}finally{
			session1.close();
		}
		return students;
	}
	
	public List<Students> searchStudent(String query){
		List<Students> students=null;
		Session session= null;
		try{
			session= connection.getSession();
			session.beginTransaction();		
			Criteria criteria = session.createCriteria(Students.class);
			if(query!=null){
				criteria.add(Restrictions.disjunction()
						.add(Restrictions.ilike("firstName", "%"+ query +"%"))
						.add(Restrictions.ilike("lName", "%"+query+"%"))
						);
			}
			students= (List<Students>)criteria.list();
			session.getTransaction().commit();
		}catch(HibernateException e){
			System.out.println(e);
		}finally{
			session.close();
		}
		return students;
	}
	
	public void edit(Map<String, String[]> map, int studentId){
		Session session= null;
		Students student=null;
		try{
			session=connection.getSession();
			session.beginTransaction();
			student=(Students) session.load(Students.class, studentId);
			student.setFirstName(map.get("fName")[0]);
			student.setlName(map.get("lName")[0]);
			student.setDepartment(map.get("faculty")[0]);
			student.setProgram(map.get("program")[0]);
			student.setPhoneNumber(Long.parseLong(map.get("phoneNumber")[0]));
			student.setGender(map.get("gender")[0]);
			session.update(student);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public long student_count(){
		Session session=null;
		long student_number=0;
		try{
			session = connection.getSession();
			session.beginTransaction();
			student_number=(long) session.createCriteria(Students.class)
					.setProjection((Projections.count("StudentId"))).uniqueResult();
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return student_number;
	}
	
	public void upload_picture(byte[] picture,int student_id){
		Session session = null;
		Students student=null;
		try{
			session=connection.getSession();
			session.beginTransaction();
			student=(Students)session.load(Students.class, student_id);
			student.setProfile_picture(picture);
			session.update(student);
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public byte[] getImage(int student_id){
		List<Students> students = getStudent(student_id);
		Students student=students.get(0);
		return student.getProfile_picture();
	}
}
