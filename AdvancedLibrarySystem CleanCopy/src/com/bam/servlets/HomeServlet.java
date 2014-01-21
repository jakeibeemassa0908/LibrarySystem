/** My Final Year Project, Started on October 15 2013
 * Advanced Library System.
 * By.  Jake Ibee Massa
 * All Right Reserved.
 * 
 * Servlet that renders the home page of the user and the admin.
 * **/
package com.bam.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bam.dto.Bookings;
import com.bam.dto.Students;
import com.bam.services.BookingService;

@WebServlet(description = "")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			RequestDispatcher dispatcher=null;
			if(session.getAttribute("user")!=null){
				dispatcher = request.getRequestDispatcher("home.jsp");
				List<Bookings>bookingsToLoad=new ArrayList<Bookings>();
				BookingService bs = BookingService.getInstance();
				List<Bookings>bookings=bs.getBookings((Students)session.getAttribute("user"), null);
				for(int i=0;i<bookings.size();i++){
					if(bookings.get(i).isReturned()==false){
						bookingsToLoad.add(bookings.get(i));
					}
				}
				bookings=bookingsToLoad;
				session.setAttribute("bookingsHome", bookings);
				request.setAttribute("now", new Date());
				
			}else if(session.getAttribute("admin")!=null){
				dispatcher = request.getRequestDispatcher("home_admin.jsp");
			}else{
				dispatcher = request.getRequestDispatcher("index.jsp");
			}
			dispatcher.forward(request, response);
			return;
	}

}
