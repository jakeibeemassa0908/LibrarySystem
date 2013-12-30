package com.bam.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bam.dto.BookPieces;
import com.bam.dto.Bookings;
import com.bam.dto.Books;
import com.bam.dto.Students;
import com.bam.services.AddBookService;
import com.bam.services.BookingService;

/**
 * Servlet implementation class BookingServlet
 */
@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("booking_history.jsp");
		HttpSession session = request.getSession();
		String error=request.getParameter("error");
		if (error!=null){
			request.setAttribute("error", "You have already reached the Limit of 4 Books");
		}
		BookingService bs= new BookingService();
		AddBookService abs = new AddBookService();
		if(session.getAttribute("user")!=null){
			//Cancel Booking from user
			if(request.getParameter("cancel")!=null){
				int Id=Integer.parseInt(request.getParameter("cancel"));
				bs.delete(Id);
				response.sendRedirect("booking");
				return;
			}
			Students student = (Students) session.getAttribute("user");
			List<Bookings> bookings=bs.getBookings(student, null);
			
			if(bookings.isEmpty()){
			
				session.setAttribute("bookings", null);
			}else{
				session.setAttribute("bookings", bookings);
			}
			
			
		}else if(session.getAttribute("admin")!=null){
			//Issue book from admin
			if(request.getParameter("issue")!=null){
				int bookToIssue= Integer.parseInt(request.getParameter("issue"));
				bs.issueBooking(bookToIssue);
				
				response.sendRedirect("booking");
				return;
			}else if (request.getParameter("return")!=null){
				//return book from admin
				int bookToReturn= Integer.parseInt(request.getParameter("return"));
				
				abs.returnBookPiece(bookToReturn);
				response.sendRedirect("booking");
				return;
			}
			List<Bookings> bookings=bs.getBookings(null, null);
			
			if(bookings.isEmpty()){
				session.setAttribute("bookings", null);
			}else{
				session.setAttribute("bookings", bookings);
			}
		}
		session.setAttribute("now", new Date());
		session.setAttribute("active_tab", "booking");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		Students student= (Students)session.getAttribute("user");
		BookingService bs = new BookingService();
		AddBookService abs = new AddBookService();
		boolean userCanBook=bs.checkUserCanBook(student);
		
		if(!userCanBook){
			
			String error="limit";
			response.sendRedirect("booking?error="+error);
			return;
			}
		else{
		//get all informations about the booking
		Date bookingDate= new Date();
		Date dateOfCollection= new Date(bookingDate.getTime() + 60*60*48*1000);
		Date dateOfReturn= new Date(bookingDate.getTime()+ 60*60*120*1000);
		String bookId= request.getQueryString();
		
		int bookIdInt=0;
		Books book=null;
		BookPieces bookPiece=null;
		try {
			bookIdInt = Integer.parseInt(bookId.substring("bookId=".length()));
			//get the book,  the book piece and the student
			book=abs.getbooks(bookIdInt, null, null, null, null, null).get(0);
			bookPiece=abs.getBookPiece(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		bs.saveBooking(bookingDate, dateOfReturn, bookPiece, book, student);
		
		response.sendRedirect("booking");
		return;
			}
	}

}
