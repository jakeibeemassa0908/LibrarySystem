package com.bam.servlets;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.bam.dto.Students;
import com.bam.helper.*;
import com.bam.services.PasswordService;

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
		final String SUCCESSMESSAGE = "Click on this link to reset Your password. This link is valid for 2 hours only";;
		String generatedToken=null;
		PasswordService ps = PasswordService.getInstance();
		if (email.isEmpty()){
			error="Please enter an email address";
			RequestDispatcher rd= request.getRequestDispatcher("forget_password.jsp");
			request.setAttribute("error", error);
			rd.forward(request, response);
			return;
		}
		else{
			List<Students>  users=null;
			Session session = null;
			try{
				DBConnection connection =DBConnection.getInstance();
				session=connection.getSession();
				session.beginTransaction();
				Criteria criteria =session.createCriteria(Students.class);
				criteria.add(Restrictions.like("email", email));
				users= criteria.list();
			}catch(HibernateException e){
				e.printStackTrace();
			}finally{
				session.close();
			}
			if (users.isEmpty()){
				error="There is no such email registered in our site.";
				RequestDispatcher rd = request.getRequestDispatcher("forget_password.jsp");
				request.setAttribute("error", error);
				rd.forward(request, response);
				return;
			}
			else{
				Date expirationDate =HelperClass.getDateAhead(2);
				generatedToken=HelperClass.generateRandomWord()+expirationDate.toString()+email;
				generatedToken=HelperClass.toSHA1(generatedToken.getBytes());
				String linkToResetPassowrd=Defaults.PATH+"reset?token="+generatedToken+"&email="+email;
				RequestDispatcher rd = request.getRequestDispatcher("forget_password.jsp");
				request.setAttribute("successMessage", SUCCESSMESSAGE);
				request.setAttribute("linkToResetPassowrd", linkToResetPassowrd);
				ps.saveToken(generatedToken, expirationDate, users.get(0).getStudentId());
				rd.forward(request, response);
				return;
			}
		}
	}

}
