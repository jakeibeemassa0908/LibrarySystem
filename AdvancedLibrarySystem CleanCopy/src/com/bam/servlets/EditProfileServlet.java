package com.bam.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bam.dto.Students;
import com.bam.services.StudentService;

@WebServlet("/edit_profile")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Students student=null;
		try{
			student=(Students) session.getAttribute("user");
			int id=Integer.parseInt(request.getParameter("id"));
			if(student.getStudentId()== id){
				request.setAttribute("student_to_edit", student);
				RequestDispatcher dispatcher = request.getRequestDispatcher("edit_profile.jsp");
				dispatcher.forward(request, response);
				return;
			}else{
				String path=request.getContextPath();
				response.sendRedirect(path+"/error");
				return;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String[]> map=null;
		HttpSession session = request.getSession();
		Students student= (Students) session.getAttribute("user");
		try{
			StudentService sc = new StudentService();
			map=request.getParameterMap();
			sc.edit(map,student.getStudentId());
			student=(Students) sc.getStudent(student.getStudentId()).get(0);
			session.setAttribute("user", student);
			response.sendRedirect("profile/"+student.getStudentId());
			return;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
