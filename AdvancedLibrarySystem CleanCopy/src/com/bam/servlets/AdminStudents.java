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
import com.bam.services.StudentService;

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
		List<Students> students;
		StudentService sc = new StudentService();
		students=sc.getStudent(null);
		if (!students.isEmpty()){
			session.setAttribute("students",students);
		}
		dispatcher.forward(request, response);
		return;
	}

}
