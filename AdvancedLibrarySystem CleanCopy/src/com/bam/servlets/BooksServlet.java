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
import com.bam.services.AddBookService;

@WebServlet("/books")
public class BooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("active_tab", "books");
		RequestDispatcher dispatcher = request.getRequestDispatcher("books.jsp");
		AddBookService abs = new AddBookService();
		List<Books> books = abs.getbooks(null, null, null, null, null, null);
		session.setAttribute("books", books);
		dispatcher.forward(request, response);
		return;
	}
}
