package com.bam.servlets;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.bam.services.RegisterService;
import com.bam.services.HelperClass;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(description = "Server for the Signup/Register page", urlPatterns = { "/register" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
		HttpSession session= request.getSession();
		session.setAttribute("active_tab", "register");
		dispatcher.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		Map<String, String[]> map = (Map<String, String[]>)request.getParameterMap();
		ArrayList<String> error = new ArrayList<String>();
		//send back the parameters to the request so that they will stay on the form 
		for(Map.Entry<String, String[]> entry : map.entrySet()){
			request.setAttribute(entry.getKey(),entry.getValue()[0]);
		}
		HelperClass hc = new HelperClass();
		error = hc.validate(map);
		boolean isMatch=(hc.matchPasswords(map.get("password")[0], map.get("re-password")[0]));
		if (isMatch==false){
			error.add("Passwords didn't match");
		}
		if (error.isEmpty()){
			RegisterService rs = new RegisterService();
			if(rs.checkAvailableUser(request.getParameter("email"))){
				String[]encrypted=new String[1];
				try{
					encrypted[0]=hc.toSHA1(map.get("password")[0].getBytes());
				}
				catch (ArrayIndexOutOfBoundsException e){
					e.printStackTrace();
				}
				rs.saveData(map);
				response.sendRedirect("login");
				return;
			}
			else{
				error.add("This email is already registered");
				request.setAttribute("error", error.get(0));
				RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
				rd.forward(request, response);
				return;
			}
		}
		else{
			request.setAttribute("error", error.get(0));
			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);
			return;
		}
	}

}
