package com.bam.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bam.services.AddBookService;
import com.bam.services.StudentService;

@WebServlet("/admin_home")
public class AdminHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentService sc = new StudentService();
		AddBookService abs = new AddBookService();
		RequestDispatcher dispatcher = request.getRequestDispatcher("home_admin.jsp");
		HttpSession session =request.getSession();
		session.setAttribute("active_tab", "admin_home");
		long student_number,book_number,book_pieces_number;
		student_number=book_number=book_pieces_number=0;
		try{
			student_number=sc.student_count();
			book_number=abs.book_count();
			book_pieces_number=abs.book_piece_count();
			request.setAttribute("student_number", student_number);
			request.setAttribute("book_number", book_number);
			request.setAttribute("book_pieces_number", book_pieces_number);
		}catch(Exception e){
			e.printStackTrace();
		}
		dispatcher.forward(request, response);
		return;
	}

}
