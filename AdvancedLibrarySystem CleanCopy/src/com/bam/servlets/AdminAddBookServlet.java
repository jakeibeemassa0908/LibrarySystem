package com.bam.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bam.services.BookService;
import com.bam.helper.*;

@WebServlet("/add_book")
public class AdminAddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher= request.getRequestDispatcher("add_book.jsp");
		HttpSession session = request.getSession();
		session.setAttribute("active_tab", "admin_books");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> map = (Map<String, String[]>)request.getParameterMap();
		ArrayList<String> error = new ArrayList<String>();
		for(Map.Entry<String, String[]> entry : map.entrySet()){
			request.setAttribute(entry.getKey(),entry.getValue()[0]);
		}
		error = HelperClass.validate(map);
				if (error.isEmpty()){
					BookService bookService = BookService.getInstance();
					if(bookService.checkAvailableBook(map))
					{
						try {
							bookService.saveData(map);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						response.sendRedirect("admin_books");
					}
					else{
						error.add("This Books is already In Our database");
						request.setAttribute("error", error.get(0));
						RequestDispatcher rd = request.getRequestDispatcher("add_book.jsp");
						rd.forward(request, response);
					}
				}
				else{
					request.setAttribute("error", error.get(0));
					RequestDispatcher rd = request.getRequestDispatcher("add_book.jsp");
					rd.forward(request, response);
				}
	}

}
