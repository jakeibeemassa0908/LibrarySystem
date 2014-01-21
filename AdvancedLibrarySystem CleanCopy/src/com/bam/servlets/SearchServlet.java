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

import com.bam.dto.Books;
import com.bam.dto.Students;
import com.bam.services.BookService;
import com.bam.helper.*;
import com.bam.services.StudentService;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/result")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("search_result.jsp");
		String query=(String) request.getParameter("search_query");
		query=HelperClass.escapeHtml(query);
		BookService abs = BookService.getInstance();
		List<Books> books=abs.searchBooks(query,null);
		
		if(session.getAttribute("admin")!=null){
			StudentService sc= StudentService.getInstance();
			List<Students> students=sc.searchStudent(query);
			if(!students.isEmpty()){
				request.setAttribute("students", students);
			}
		}
		if(books.isEmpty()){
			books=null;
		}
		request.setAttribute("books", books);
		request.setAttribute("search_query", query);
		rd.forward(request, response);
	}

}
