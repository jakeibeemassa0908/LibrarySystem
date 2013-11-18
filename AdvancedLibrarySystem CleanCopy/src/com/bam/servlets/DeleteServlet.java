package com.bam.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bam.dto.Books;
import com.bam.dto.Comments;
import com.bam.dto.Students;
import com.bam.services.AddBookService;
import com.bam.services.ReviewService;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getParameter("bookId")!=null){
			if(session.getAttribute("admin")!=null){
				
				int bookId=Integer.parseInt(request.getParameter("bookId"));
				AddBookService abs = new AddBookService();
				
				Books book=abs.getbooks(bookId, null, null, null, null, null).get(0);
				abs.deleteBook(book);
				response.sendRedirect("admin_books");
				
			}else{
				String path=request.getContextPath();
				response.sendRedirect(path+"/error");
			}
		}else if(request.getParameter("commentId")!=null){
			ReviewService rs = new ReviewService();
			int bookId=0;
			int id=Integer.parseInt(request.getParameter("commentId"));
			try {
				Comments comment= (Comments) rs.getReview(id, null, null,1).get(0);
				bookId=comment.getBook().getBookId();
				Students student=(Students) session.getAttribute("user");
				if(session.getAttribute("admin")!=null || comment.getStudent().getStudentId()==student.getStudentId()){
					rs.deleteReview(id);
					response.sendRedirect("book_detail/"+bookId);
				}
				
			} catch (Exception e) {
				
				e.printStackTrace();
				String path=request.getContextPath();
				response.sendRedirect(path+"/error");
			}
			
		}else{
			String path=request.getContextPath();
			response.sendRedirect(path+"/error");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
