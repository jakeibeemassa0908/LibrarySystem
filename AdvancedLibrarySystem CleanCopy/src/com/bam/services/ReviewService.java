package com.bam.services;

import com.bam.dto.BookPieces;
import com.bam.dto.Books;
import com.bam.dto.Comments;
import com.bam.dto.Students;
import com.bam.dto.Library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
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

public class ReviewService {
	DBConnection connection = new DBConnection();
	
	public List<Comments> getReview(Integer id, Books book, Students student, Integer number){
		Session session1=null;
		List<Comments> reviews=null;
		try {
			session1 =connection.getSession();
			session1.beginTransaction();
			Criteria criteria =session1.createCriteria(Comments.class);
			if(id!=null){
				criteria.add(Restrictions.like("commentID", id));
			}
			if(book!=null){
				criteria.add(Restrictions.like("book", book));
			}
			if(student!=null){
				criteria.add(Restrictions.like("student", student));
			}
			if(number!=null){
				criteria.setMaxResults(number);
			}
			criteria.addOrder(Order.desc("commentID"));
			reviews = (List<Comments>) criteria.list();
			session1.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			session1.close();
		}
		return reviews;
	}
	
	
	public void saveReview(Map<String, String[]> map,Books book, Students student){	
		Session session=null;
		try {
			session = connection.getSession();
			session.beginTransaction();
			Comments comment = new Comments();
			comment.setBook(book);
			comment.setCommentContent(map.get("comment_content")[0]);
			comment.setCommentTitle(map.get("comment_title")[0]);
			comment.setCommentDate(new Date());
			comment.setCommentUserName(student.getFirstName() + " "+ student.getlName());
			comment.setStudent(student);
			session.save(comment);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public void deleteReview(int reviewId){
		Session session = null;
		try{
			session=connection.getSession();
			session.beginTransaction();		
			Comments comment= (Comments)session.load(Comments.class,reviewId);
			session.delete(comment);
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public void deleteReviews(Books book){
		Session session = null;
		List<Comments> comments=null;
		try{
			session=connection.getSession();
			session.beginTransaction();		
			comments=getReview(null,book, null,null);
			if(!comments.isEmpty()){
				for(Comments comment:comments){
					session.delete(comment);
				}
			}
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public long getReviewSize(Books book){
		Session session=null;
		long number=0;
		try{
			session = connection.getSession();
			session.beginTransaction();		
			number = (Long) session.createCriteria(Comments.class)
					.add(Restrictions.like("book", book))
					.setProjection(Projections.count("commentID"))
					.uniqueResult();
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return number;
	}
}