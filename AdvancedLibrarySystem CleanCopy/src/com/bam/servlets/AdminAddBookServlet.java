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

import com.bam.services.AddBookService;
import com.bam.services.HelperClass;
import com.bam.services.RegisterService;

/**
 * Servlet implementation class AdminAddBookServlet
 */
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
		//get all the parameters from the request and validate them
		Map<String, String[]> map = (Map<String, String[]>)request.getParameterMap();
		ArrayList<String> error = new ArrayList<String>();
		//send back the parameters to the request so that they will stay on the form 
		for(Map.Entry<String, String[]> entry : map.entrySet()){
			request.setAttribute(entry.getKey(),entry.getValue()[0]);
		}
		HelperClass hc = new HelperClass();
		error = hc.validate(map);
		//if it comes back with no error, save it to the db, if it does tell the user
				if (error.isEmpty()){
					AddBookService bookService = new AddBookService();
					if(bookService.checkAvailableBook(map))
					{
						try {
							bookService.saveData(map);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
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
