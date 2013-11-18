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
import org.hibernate.criterion.Order;

import com.bam.dto.Books;
import com.bam.dto.Students;

/**
 * Servlet implementation class AdminStudents
 */
@WebServlet("/admin_students")
public class AdminStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher= null;
		String view= request.getParameter("view");
		if(view ==null ||view.equals("list")){
			dispatcher = request.getRequestDispatcher("admin_students.jsp");
		}else{
			dispatcher = request.getRequestDispatcher("admin_students_grid.jsp");
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("active_tab", "admin_students");
		//get all books form the database
		List<Students> students;
		SessionFactory sessionFactory =new Configuration().configure().buildSessionFactory();
		Session session1 = sessionFactory.openSession();
		session1.beginTransaction();
		
		Criteria criteria =session1.createCriteria(Students.class);
		
		boolean flag=false;
		String q=request.getQueryString();
		if(q!=null && q.equals("sort='id'")){
			if (flag==false){
			criteria =session1.createCriteria(Students.class)
					.addOrder(Order.desc("StudentId"));
			flag=true;
			}else{
				criteria =session1.createCriteria(Students.class)
						.addOrder(Order.asc("StudentId"));
				flag=false;
			}
		}
		
		students=criteria.list();
		if (students.isEmpty()){
			
		}
		else{
		session.setAttribute("students",students);
		}
		
		session1.getTransaction().commit();
		session1.close();
		
		dispatcher.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
