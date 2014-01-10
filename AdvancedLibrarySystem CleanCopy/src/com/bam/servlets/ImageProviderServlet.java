package com.bam.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import com.bam.services.StudentService;

/**
 * Servlet implementation class ImageProviderServlet
 */
@WebServlet("/getProfileImage")
public class ImageProviderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentService sc = new StudentService();
		int student_id=Integer.parseInt(request.getParameter("id"));
		byte[] image= sc.getImage(student_id);
		response.setContentType("image/jpeg");
		response.setContentLength(image.length);
		response.getOutputStream().write(image);
	}

}
