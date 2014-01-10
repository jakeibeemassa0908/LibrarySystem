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
import com.bam.services.AddBookService;

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
		AddBookService abs = new AddBookService();
		List<Books> books=abs.getbooks(null, null, null, null, null, null);
		if (!books.isEmpty()){	
			session.setAttribute("books", books);
		}
		dispatcher.forward(request, response);
		return;
	}

}
