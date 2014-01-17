package com.bam.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bam.dto.Books;
import com.bam.services.AddBookService;

/**
 * Servlet implementation class AddBookPiecesServlet
 */
@WebServlet("/add_piece")
public class AddBookPiecesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddBookService abs= new AddBookService();
		try{
			String bookId= request.getParameter("id");
			int bookIdInt=Integer.parseInt(bookId);
			List<Books> book;
			book=abs.getbooks(bookIdInt, null, null, null, null, null);
			if(!book.isEmpty()){
				int pieces=book.get(0).getStock_number();
				abs.addBookPieces(pieces,bookIdInt);
				response.sendRedirect("admin_books");
				return;
			}
		}catch(Exception e){
			response.sendRedirect("error");
			return;
		}
	}

}
