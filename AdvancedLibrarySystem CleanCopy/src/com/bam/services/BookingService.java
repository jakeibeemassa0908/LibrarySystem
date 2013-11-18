package com.bam.services;

import com.bam.dto.BookPieces;
import com.bam.dto.Bookings;
import com.bam.dto.Books;
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

public class BookingService {
	DBConnection connection = new DBConnection();
	public List<Bookings> getBookings(Students std,Books book){
		Session session1= null;
		List<Bookings> bookings=null;
		
		try {
			session1 = connection.getSession();
			session1.beginTransaction();
			
			Criteria criteria =session1.createCriteria(Bookings.class);
			if(std!=null){
				criteria.add(Restrictions.like("student", std));
			}
			if(book!=null){
				criteria.add(Restrictions.like("book", book));
			}
			criteria.addOrder(Order.asc("returned"));
			
			bookings = (List<Bookings>) criteria.list();
			
			session1.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println(e);
			e.printStackTrace();
		}finally{
			session1.close();
			
		}
		return bookings;
	}
	
	public void saveBooking(Date bookingDate, Date bookingReturnDate,BookPieces bookPiece,Books book,Students student){
		Session session=null;
		try {
			session = connection.getSession();
			session.beginTransaction();
			Bookings booking = new Bookings();
			
			booking.setBook(book);
			booking.setBookingDate(bookingDate);
			booking.setBookingReturnDate(bookingReturnDate);
			booking.setBookPiece(bookPiece);
			booking.setReturned(false);
			booking.setStudent(student);
			
			session.save(booking);
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println(e);
			e.printStackTrace();
		}finally{
			session.close();
			
		}
	}
	public void issueBooking(int bookingNumber){
		Session session = null;
		try{
			session=connection.getSession();
			session.beginTransaction();
			
			Bookings bookings = (Bookings) session.load(Bookings.class, bookingNumber);
			
			bookings.setIssued(true);
			bookings.setIssuedDate(new Date());
			
			session.update(bookings);
			session.getTransaction().commit();
			
		}catch(HibernateException e){
			System.out.println(e);
		}finally{
			session.close();
			
		}
	}

	public boolean checkUserCanBook(Students student){
		Session session= null;
		List<Bookings> bookings=null;
		try{
			session= connection.getSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Bookings.class)
					.add(Restrictions.disjunction())
					.add(Restrictions.like("student", student))
					.add(Restrictions.like("returned", false));
			
			bookings=criteria.list();
			
			session.getTransaction().commit();
			
		}catch(HibernateException e){
			System.out.println(e);
		}finally{
			session.close();
			
		}
		
		if (bookings.size()>3){
			return false;
		}else{
			return true;
		}
	}
	public void delete(int id){
		AddBookService abs = new AddBookService();
		abs.returnBookPiece(id);
		Session session= null;
		try{
			session= connection.getSession();
			session.beginTransaction();
			
			Bookings booking = new Bookings();
			booking=(Bookings)session.load(Bookings.class, id);
			session.delete(booking);
			
			session.getTransaction().commit();
			
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
			
		}
	}
	
	public void deleteBookings(Books book){
		List<Bookings> bookings=null;
		Session session=null;
		try{
			bookings=getBookings(null,book);
			session=connection.getSession();
			session.beginTransaction();
			
			for(Bookings booking:bookings){
				session.delete(booking);
			}
			session.getTransaction().commit();
			
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
}
