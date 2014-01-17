package com.bam.services;

import com.bam.dto.BookPieces;
import com.bam.dto.Bookings;
import com.bam.dto.Books;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.bam.helper.*;
public class BookService {
	HelperClass hc = new HelperClass();
	DBConnection connection = new DBConnection();

	public boolean checkAvailableBook(Map<String, String[]> map){
		List<Books> book=null;
		Session session=null;
		try {
			session = connection.getSession();
			session.beginTransaction();
			
			Criteria criteria=null;
				criteria = session.createCriteria(Books.class);
				criteria.add(Restrictions.like("title",map.get("title")[0]))
						.add(Restrictions.like("author",map.get("author")[0]))
						.add(Restrictions.like("publisher",map.get("publisher")[0]))
						.add(Restrictions.like("year",Integer.parseInt(map.get("year")[0])));
			book=(List<Books>)criteria.list();
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
		if (book.isEmpty()){
			return true;
		}
		return false;
	}
	
	public void saveData(Map<String, String[]> map) throws ParseException{	
		Session session=null;
		try {
			session=connection.getSession();
			session.beginTransaction();
			Books book = new Books();
			book.setAuthor(map.get("author")[0]);
			book.setTitle(map.get("title")[0]);
			book.setCategory(map.get("category")[0]);
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");      
			 Date dateWithoutTime = sdf.parse(sdf.format(new Date()));
			book.setDate_added(dateWithoutTime);
			book.setDescription(map.get("description")[0]);
			book.setPublisher(map.get("publisher")[0]);
			book.setStock_number(Integer.parseInt(map.get("stock_number")[0]));
			book.setYear(Integer.parseInt(map.get("year")[0]));
			book.setAvailableOne(Integer.parseInt(map.get("stock_number")[0]));
			book.setBook_cover_link(map.get("url")[0]);
			book.setISBN(map.get("isbn")[0]);
			session.save(book);
			session.getTransaction().commit();
			addBookPieces(book.getStock_number(),book.getBookId());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (HibernateException e) {
			System.out.println(e);
			e.printStackTrace();
		}finally{
		session.close();
		}
	}
	
	//function to get a book in the database
	public List<Books> getbooks(Integer id, String title, String author,String category,String publisher, Integer year){
		Session session1=null;
		List<Books> book=null;
		try {
			session1=connection.getSession();
			session1.beginTransaction();
			Criteria criteria =session1.createCriteria(Books.class);
			if(id!=null){
				criteria.add(Restrictions.like("bookId", id));
			}
			if(title!=null){
				criteria.add(Restrictions.like("title", id));
			}
			if(author!=null){
				criteria.add(Restrictions.like("author", author));
			}
			if(category!=null){
				criteria.add(Restrictions.like("category", category));
			}
			if(publisher!=null){
				criteria.add(Restrictions.like("publisher", publisher));
			}
			if(year!=null){
				criteria.add(Restrictions.like("year", year));
			}
			book = (List<Books>) criteria.list();
			session1.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println(e);
			e.printStackTrace();
		}finally{
		session1.close();
		}
		return book;
	}
	
	public void addBookPieces(int number, int bookId){	
		Session session1= null;
		try {
			session1 = connection.getSession();
			session1.beginTransaction();
			Criteria criteria =session1.createCriteria(Books.class)
					.add(Restrictions.like("bookId", bookId));
			List<Books> book= (List<Books>) criteria.list();
			BookPieces[] o = new BookPieces[number];
			for(int n=0;n<number;n++){
				System.out.println(o[n]);
				o[n]=new BookPieces();
				o[n].setAvailable(true);
				o[n].setBook(book.get(0));
				book.get(0).getBookPieces().add(o[n]);	
				}
			Books books=(Books) session1.load(Books.class,bookId);
			books.setAvailableOne(number);
			session1.update(books);
			session1.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println(e);
			e.printStackTrace();
		}finally{
		session1.close();
		}
	}

	public BookPieces getBookPiece(Books book){
		Session session1=null;
		BookPieces bookPiece=null;		
		try {
			session1 = connection.getSession();
			session1.beginTransaction();
			Criteria criteria =session1.createCriteria(BookPieces.class)
					.add(Restrictions.like("available", true))
					.add(Restrictions.like("book", book))
					.addOrder(Order.asc("bookPieceId"))
					.setMaxResults(1);
			bookPiece = (BookPieces) criteria.uniqueResult();
			bookPiece=(BookPieces)session1.load(BookPieces.class, bookPiece.getBookPieceId());
			bookPiece.setAvailable(false);
			Books books=(Books) session1.load(Books.class,bookPiece.getBook().getBookId());
			books.setAvailableOne(books.getAvailableOne()-1);
			session1.update(books);
			session1.update(bookPiece);
			session1.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println(e);
			e.printStackTrace();
		}finally{
			session1.close();
		}
		return bookPiece;
	}
	
	public List<Books> searchBooks(String query, Integer limit){
		Session session1=null;
		List<Books> books=null;
		try {
			session1 = connection.getSession();
			session1.beginTransaction();
			Criteria criteria =session1.createCriteria(Books.class)
					.add(Restrictions.disjunction()
					.add(Restrictions.ilike("title", "%"+query+"%"))
					.add(Restrictions.ilike("author", "%"+query+"%"))
					.add(Restrictions.ilike("category", "%"+query+"%"))
							);
			if( limit!=null){
					criteria.setMaxResults(limit);
			}
			books = (List<Books>) criteria.list();
			session1.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println(e);
			e.printStackTrace();
		}finally{
		session1.close();
		}
		return books;
	}
	
	public void returnBookPiece(int bookingId){
		Session session = null;
		Bookings booking=null;
		BookPieces bookPiece = new BookPieces();
		Books book = new Books();
		try{
			session= connection.getSession();
			session.beginTransaction();
			booking=(Bookings) session.load(Bookings.class, bookingId);
			bookPiece= (BookPieces) session.load(BookPieces.class, booking.getBookPiece().getBookPieceId());
			book=(Books)session.load(Books.class, booking.getBook().getBookId());
			booking.setReturned(true);
			bookPiece.setAvailable(true);
			book.setAvailableOne(book.getAvailableOne()+1);
			session.update(book);
			session.update(bookPiece);
			session.update(booking);
			session.getTransaction().commit();
		}catch(HibernateException e){
			System.out.println(e);
		}finally{
			session.close();
		}
	}
	
	public void editBook(Map<String, String[]> map,int bookId){
		Session session = null;
		Books book=null;
		try{
			session=connection.getSession();
			session.beginTransaction();
			book=(Books) session.load(Books.class, bookId);
			book.setAuthor(map.get("author")[0]);
			book.setTitle(map.get("title")[0]);
			book.setCategory(map.get("category")[0]);
			book.setDescription(map.get("description")[0]);
			book.setPublisher(map.get("publisher")[0]);
			book.setYear(Integer.parseInt(map.get("year")[0]));
			//get the number of book that are on reservation.
			int bookOnLeave=book.getStock_number()-book.getAvailableOne();
			book.setStock_number(Integer.parseInt(map.get("stock_number")[0]));
			//computer the book available by taking the new stock_number - books on reservation.
			book.setAvailableOne(Integer.parseInt(map.get("stock_number")[0])-bookOnLeave);
			session.update(book);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	
	public List<BookPieces> getBookPieces(Books book){
		Session session1=null;
		List<BookPieces> bookPieces=null;
		try {
			session1 = connection.getSession();
			session1.beginTransaction();
			Criteria criteria =session1.createCriteria(BookPieces.class)
					.add(Restrictions.like("book", book))
					.addOrder(Order.asc("bookPieceId"));
			bookPieces = criteria.list();
			session1.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println(e);
			e.printStackTrace();
		}finally{
			session1.close();
		}
		return bookPieces;
	}
	
	public void deleteBook(Books book){
		Session session=null;
		Books bookToDelete=null;
		try{
		deleteBookPieces(book);
		BookingService bs= new BookingService();
		bs.deleteBookings(book);
		ReviewService rs= new ReviewService();
		rs.deleteReviews(book);
		session=connection.getSession();
		session.beginTransaction();
		bookToDelete=(Books) session.load(Books.class, book.getBookId());
		session.delete(bookToDelete);
		session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public void deleteBookPieces(Books book){
		List<BookPieces> bookPieces=null;
		Session session=null;
		try{
		bookPieces=getBookPieces(book);
		session=connection.getSession();
		session.beginTransaction();
		for(BookPieces bookPiece:bookPieces){
			session.delete(bookPiece);
		}
		session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public long book_count(){
		Session session=null;
		long book_number=0;
		try{
			session = connection.getSession();
			session.beginTransaction();
			book_number=(long) session.createCriteria(Books.class)
					.setProjection((Projections.count("bookId"))).uniqueResult();
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return book_number;
	}
	
	public long book_piece_count(){
		Session session=null;
		long book_pieces=0;
		try{
			session = connection.getSession();
			session.beginTransaction();
			book_pieces=(long) session.createCriteria(BookPieces.class)
					.setProjection((Projections.count("bookPieceId"))).uniqueResult();
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return book_pieces;
	}
}
