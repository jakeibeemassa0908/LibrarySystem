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
import com.bam.services.LoginService;
import com.bam.services.PasswordService;

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
			String email= (String)request.getParameter("email");
			String password=(String) request.getParameter("password");
			LoginService login = LoginService.getInstance();
			if(email.isEmpty() || password.isEmpty()){
				String errorString = "User Name or Password cannot be left blank";
				RequestDispatcher dispatcher= request.getRequestDispatcher("login.jsp");
				request.setAttribute("error", errorString);
				dispatcher.forward(request, response);
				return;
			}
			else{
				List<Students> users=null;
				List<Library> admin=null;
				try {
					if(HelperClass.validateEmail(email)){
						users=login.logStudentIn(email, password);
						if(users.isEmpty())users=null;
					}else{
						admin = login.logAdminIn(email, password);
						if(admin.isEmpty())admin=null;
					}
				} catch (HibernateException e) {
					e.printStackTrace();
				}
					if(users==null && admin==null){
						String errorString = "User Name or Password is incorect";
						RequestDispatcher dispatcher= request.getRequestDispatcher("login.jsp");
						request.setAttribute("error", errorString);
						dispatcher.forward(request, response);
						return;
					}
					else{
						HttpSession httpSession = request.getSession();
						if(users!=null){
							httpSession.setAttribute("user", users.get(0));
							response.sendRedirect("");
							return;
						}
						else if (admin!=null){
							httpSession.setAttribute("admin", admin.get(0));
							response.sendRedirect("admin_home");
							return;
						}
					}
				
			}
	}

}
