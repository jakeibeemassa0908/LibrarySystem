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

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.bam.dto.Bookings;
import com.bam.dto.Books;
import com.bam.dto.Students;
import com.bam.services.BookingService;
import com.bam.services.StudentService;


public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
				HttpSession session =request.getSession();	
				StudentService sc= StudentService.getInstance();
				Students student=null;
				int id=0;
				try {
					id = Integer.parseInt((String) request.getAttribute("Id"));
					student = (Students) sc.getStudent(id).get(0);
				} catch (Exception e) {
					String path=request.getContextPath();
					response.sendRedirect(path+"/error");
					return;
				}
					session.setAttribute("student", student);
						if(session.getAttribute("user")!=null){
							Students studentLoggedIn= (Students) session.getAttribute("user"); 
							//avoid student poking around student db by changing ids.
							if(student.getStudentId()!= studentLoggedIn.getStudentId()){
								String path=request.getContextPath();
								response.sendRedirect(path+"/error");
								return;
							}
						}
						BookingService bs=BookingService.getInstance();
						List<Bookings> bookings=bs.getBookings(student, null);
						if(!bookings.isEmpty()){
							session.setAttribute("bookings", bookings);
						}else{
							session.setAttribute("bookings", null);
						}
					session.setAttribute("active_tab", "profile");
					session.setAttribute("now", new Date());
					dispatcher.forward(request, response);
					return;
	}

}
