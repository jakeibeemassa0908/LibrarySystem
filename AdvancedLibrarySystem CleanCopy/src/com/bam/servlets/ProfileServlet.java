package com.bam.servlets;

import java.io.IOException;
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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
				//convert the given id in integer
				int id=0;
				try {
					id = Integer.parseInt((String) request.getAttribute("Id"));
				} catch (NumberFormatException e) {
					
					e.printStackTrace();
				}
				HttpSession session =request.getSession();
						
				StudentService sc= new StudentService();
				
				List<Students> student= sc.getStudent(id);
				if (student.isEmpty()){
					String path=request.getContextPath();
					response.sendRedirect(path+"/error");
					return;
				}
				else{
					
					session.setAttribute("student", student.get(0));
					Students student2=null;
						//If student account is logged in
						if(session.getAttribute("user")!=null){
							Students studentLoggedIn= (Students) session.getAttribute("user"); 
							student2=(Students) session.getAttribute("user");
							//avoid student poking around student db by changing ids.
							if(student.get(0).getStudentId()!= studentLoggedIn.getStudentId()){
								String path=request.getContextPath();
								response.sendRedirect(path+"/error");
								return;
							}
						}else{
							student2=student.get(0);
							}
						BookingService bs= new BookingService();
						List<Bookings> bookings=bs.getBookings(student2, null);
						
						if(bookings.isEmpty()){
							session.setAttribute("bookings", null);
						}else{
							session.setAttribute("bookings", bookings);
						}
					}
	
					
					
					session.setAttribute("active_tab", "profile");
					dispatcher.forward(request, response);
					return;
				
				
				
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
