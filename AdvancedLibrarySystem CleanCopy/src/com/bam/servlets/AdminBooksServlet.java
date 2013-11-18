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

import com.bam.dto.Books;

@WebServlet("/admin_books")
public class AdminBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher=null;
		String view = request.getParameter("view");
		if(view==null || view.equals("list")){
			dispatcher = request.getRequestDispatcher("admin_books_list.jsp");
		}else{
			dispatcher = request.getRequestDispatcher("admin_books_grid.jsp");
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("active_tab", "admin_books");
		
		//get all books form the database
		List<Books> books;
		SessionFactory sessionFactory =new Configuration().configure().buildSessionFactory();
		Session session1 = sessionFactory.openSession();
		session1.beginTransaction();
		
		Criteria criteria =session1.createCriteria(Books.class);
		books=criteria.list();
		if (books.isEmpty()){
			
		}
		else{
		session.setAttribute("books", books);
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
