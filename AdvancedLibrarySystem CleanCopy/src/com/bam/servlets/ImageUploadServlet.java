package com.bam.servlets;

import java.util.*;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

import com.bam.dto.Students;
import com.bam.services.StudentService;



@WebServlet("/profileUpload")
public class ImageUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("error");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentService sc =StudentService.getInstance();
		HttpSession session = request.getSession();
		Students student = (Students)session.getAttribute("user");
		int student_id=student.getStudentId();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart){
				byte[] picture=null;
			try {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
				    FileItem item = iter.next();
				    if (!item.isFormField()) {
				        picture=item.get();
				    } 
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			sc.upload_picture(picture, student_id);
			String path=request.getContextPath();
			response.sendRedirect(path+"/profile/"+student_id);
			return;
		}
		else{
			response.sendRedirect("/error");
			return;
		}
	}

}
