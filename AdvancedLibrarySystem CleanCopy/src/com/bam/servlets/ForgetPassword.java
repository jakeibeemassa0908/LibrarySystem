package com.bam.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.bam.dto.Students;

/**
 * Servlet implementation class ForgetPassword
 */
@WebServlet("/forget")
public class ForgetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("forget_password.jsp");
		dispatcher.forward(request, response);
		return;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String error;
		String successMessage;
		if (email.isEmpty()){
			error="Please enter an email address";
			RequestDispatcher rd= request.getRequestDispatcher("forget_password.jsp");
			request.setAttribute("error", error);
			rd.forward(request, response);
			return;
		}
		else{
			SessionFactory sessionFactory =new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			Criteria criteria =session.createCriteria(Students.class);
			criteria.add(Restrictions.like("email", email));
			List<Students> users= (List<Students>)criteria.list();
			
			if (users.isEmpty()){
				error="There is no such email registered in our site.";
				RequestDispatcher rd = request.getRequestDispatcher("forget_password.jsp");
				request.setAttribute("error", error);
				rd.forward(request, response);
				return;
			}
			else{
				successMessage= "An email has been sent to you with the instructions to reset/retrieve your password.";
				RequestDispatcher rd = request.getRequestDispatcher("forget_password.jsp");
				request.setAttribute("successMessage", successMessage);
				rd.forward(request, response);
				return;
			}
		}
	}

}
