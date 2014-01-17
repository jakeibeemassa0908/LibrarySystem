package com.bam.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.bam.dto.BookPieces;
import com.bam.dto.Books;
import com.bam.dto.Comments;
import com.bam.dto.Students;
import com.bam.services.AddBookService;
import com.bam.services.HelperClass;
import com.bam.services.ReviewService;

/**
 * Servlet implementation class BookDetailServlet
 */
public class BookDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("book_detail.jsp");
		int id=0;
		try {
			id = Integer.parseInt((String) request.getAttribute("Id"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		HttpSession session =request.getSession();
		session.setAttribute("active_tab", "books");
		AddBookService abs = new AddBookService();
		List<Books> book= abs.getbooks(id, null, null, null, null, null);
		if (book.isEmpty()){
			String path=request.getContextPath();
			response.sendRedirect(path+"/error");
		}
		else{
			ReviewService rs = new ReviewService();
			List <Comments>reviews= rs.getReview(null, book.get(0),null,3);
			List<Books> suggestions=abs.searchBooks(book.get(0).getCategory(), 4);
			if(!suggestions.isEmpty())
				session.setAttribute("suggestions", suggestions);
			if(session.getAttribute("admin")!=null){
				List <BookPieces>bookPieces=abs.getBookPieces(book.get(0));
				session.setAttribute("pieces", bookPieces);
			}
			if (!reviews.isEmpty()){
				long review_size=rs.getReviewSize(book.get(0));
				request.setAttribute("reviews", reviews);
				request.setAttribute("review_size", review_size);
			}
			session.setAttribute("books", book.get(0));
			dispatcher.forward(request, response);
			return;
		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				Map<String, String[]> map = (Map<String, String[]>)request.getParameterMap();
				HttpSession session = request.getSession();
				for(Map.Entry<String, String[]> entry : map.entrySet()){
					request.setAttribute(entry.getKey(),entry.getValue()[0]);
				}
				String id= request.getParameter("id");
				int idInt=Integer.parseInt(id);
				HelperClass hc = new HelperClass();
				ArrayList<String>error = hc.validate(map);
				if (error.isEmpty()){
					AddBookService abs = new AddBookService();
					Books book=abs.getbooks(idInt, null, null, null, null, null).get(0);
					Students student= (Students) session.getAttribute("user");
					ReviewService rs= new ReviewService();
					rs.saveReview(map, book, student);
					response.sendRedirect("book_detail/"+id);
					return;
				}
	}

}
