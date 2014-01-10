package com.bam.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bam.services.HelperClass;
import com.bam.services.LibraryRegisterService;
import com.bam.services.RegisterService;

@WebServlet("/libregister")
public class LibraryRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher= request.getRequestDispatcher("registerlibrary.jsp");
		HttpSession session= request.getSession();
		session.setAttribute("active_tab", "lib_register");
		dispatcher.forward(request, response);
		return;
		
	}

	@SuppressWarnings("finally")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> map = (Map<String, String[]>)request.getParameterMap();
		ArrayList<String> error = new ArrayList<String>();
		for(Map.Entry<String, String[]> entry : map.entrySet()){
			request.setAttribute(entry.getKey(),entry.getValue()[0]);
		}
		HelperClass hc = new HelperClass();
		error = hc.validate(map);
		boolean isMatch=(hc.matchPasswords(map.get("libraryPassword")[0], map.get("re-password")[0]));
		if (isMatch==false){
			error.add("Passwords didn't match");	
		}
		if (error.isEmpty()){
			LibraryRegisterService rs = new LibraryRegisterService();
			if(rs.checkAvailableUser(request.getParameter("libraryEmail")))
			{
				String[]encrypted=new String[1];
				try{
					encrypted[0]=hc.toSHA1(map.get("libraryPassword")[0].getBytes());
				}
				catch (ArrayIndexOutOfBoundsException e){
					e.printStackTrace();
				}
				finally{
				rs.saveData(map);
				String message= "Thank you for Registering with us, Our team will contact you shortly for verification purpose.";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("registerlibrary.jsp");
				dispatcher.forward(request, response);
				return;
				}
			}
			else{
				error.add("This email is already registered");
				request.setAttribute("error", error.get(0));
				RequestDispatcher rd = request.getRequestDispatcher("registerlibrary.jsp");
				rd.forward(request, response);
				return;
			}
		}
		else{
			request.setAttribute("error", error.get(0));
			RequestDispatcher rd = request.getRequestDispatcher("registerlibrary.jsp");
			rd.forward(request, response);
			return;
		}
	}

}
