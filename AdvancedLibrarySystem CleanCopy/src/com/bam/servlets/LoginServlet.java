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
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.bam.dto.Library;
import com.bam.dto.Students;
import com.bam.helper.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "Servlet to handle login.jsp page", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			HttpSession session= request.getSession();
			session.setAttribute("active_tab", "login");
			dispatcher.forward(request, response);
			return;	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			DBConnection connection = new DBConnection();
			String email= (String)request.getParameter("email");
			String password=(String) request.getParameter("password");
			if(email.isEmpty() || password.isEmpty()){
				String errorString = "User Name or Password cannot be left blank";
				RequestDispatcher dispatcher= request.getRequestDispatcher("login.jsp");
				request.setAttribute("error", errorString);
				dispatcher.forward(request, response);
				return;
			}
			else{
				Session session=null;
				List<Students> users=null;
				List<Library> admin=null;
				try {
					session = connection.getSession();
					session.beginTransaction();
					Criteria criteria =session.createCriteria(Students.class)
							.add(Restrictions.like("email", email))
							.add(Restrictions.like("password",HelperClass.toSHA1(password.getBytes())));
					Criteria criteria2 = session.createCriteria(Library.class)
							.add(Restrictions.like("libraryUserName", email))
							.add(Restrictions.like("libraryPassword",HelperClass.toSHA1(password.getBytes())));
					users=criteria.list();
					admin= criteria2.list();
					session.getTransaction().commit();
				} catch (HibernateException e) {
					e.printStackTrace();
				}finally{
					session.close();
				}
					if(users.isEmpty() && admin.isEmpty()){
						String errorString = "User Name or Password is incorect";
						RequestDispatcher dispatcher= request.getRequestDispatcher("login.jsp");
						request.setAttribute("error", errorString);
						dispatcher.forward(request, response);
						return;
					}
					else{
						HttpSession httpSession = request.getSession();
						if(users.size()!=0){
							httpSession.setAttribute("user", users.get(0));
							response.sendRedirect("");
							return;
						}
						else if (admin.size()!=0){
							httpSession.setAttribute("admin", admin.get(0));
							response.sendRedirect("admin_home");
							return;
						}
					}
				
			}
	}

}
