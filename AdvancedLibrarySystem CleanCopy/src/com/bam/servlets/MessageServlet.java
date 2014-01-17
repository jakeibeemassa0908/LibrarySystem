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

import com.bam.dto.Messages;
import com.bam.dto.Students;
import com.bam.helper.*;
import com.bam.services.MessageService;
import com.bam.services.StudentService;

@WebServlet("/message")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int ADMIN_CODE=0;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer requestURL = request.getRequestURL();
		String requestName=requestURL.toString();
		int start=requestName.lastIndexOf("message");
		String goToMessage=requestName.substring(start);
		String name=goToMessage+".jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(name);
		HttpSession session = request.getSession();
		session.setAttribute("active_tab", "message");
		MessageService msgServ = new MessageService();
		List<Messages> messagesin, messagesout,message;
		Students student= new Students();
		StudentService sc= new StudentService();
		if (session.getAttribute("user")!=null){
		//get current user session ID
		student=(Students) session.getAttribute("user");
		}
		Integer from =null;
		Integer to=null;
		
		if (goToMessage.equals("message_outbox")){
			if(session.getAttribute("admin")!=null){
				from=ADMIN_CODE;
				to=null;
			}else{
				from=student.getStudentId();
			}
			int asker;
			if(from==ADMIN_CODE)asker=ADMIN_CODE;
			else asker=2;
			//get all messages form the database
			messagesout=msgServ.getMessages(from, to, null,asker);
			if (messagesout.isEmpty()){
				
			}
			else{
			session.setAttribute("messages_out",messagesout);
			}
			dispatcher.forward(request, response);
			return;
		}
		else if (goToMessage.equals("message")){
			if(session.getAttribute("admin")!=null){
				to=ADMIN_CODE;
				from=null;
			}else{
			to=student.getStudentId();
			from=ADMIN_CODE;
			}
			int asker;
			if (to==ADMIN_CODE)asker=0;
			else asker=2;
			//get all messages form the database
			messagesin=msgServ.getMessages(from, to, null,asker);
			if (messagesin.isEmpty()){
				
			}
			else{
			session.setAttribute("messages_in",messagesin);
			}
			dispatcher.forward(request, response);
			return;
		}
		
		else if (goToMessage.equals("message_compose")){
			if(session.getAttribute("admin")!=null && request.getQueryString()!=null){
				List<Students> students=null;
				try {
					int ID= Integer.parseInt(request.getParameter("to"));
					students=sc.getStudent(ID);
					session.setAttribute("to", students.get(0));
					request.setAttribute("msg_subject", request.getParameter("sub"));
				} catch (Exception e) {
					e.printStackTrace();
					String path=request.getContextPath();
					response.sendRedirect(path+"/error");
					return;
				}
			}else{
				request.setAttribute("msg_subject", request.getParameter("sub"));
				session.setAttribute("to", null);
			}
		}
		//Delete message
		else if (goToMessage.equals("message_delete")){
			String path=request.getContextPath();
			int messageId=Integer.parseInt(request.getParameter("id"));
			if(session.getAttribute("admin")!=null && request.getQueryString()!=null){
				msgServ.setMessageDeleted(messageId, 0);
			}
			else if(session.getAttribute("user")!=null){
				student=(Students)session.getAttribute("user");
				int studentId=student.getStudentId();
				message=msgServ.getMessages(null, null, messageId,null);
				if(message.get(0).getMessageFrom()==studentId || message.get(0).getMessageTo()==studentId){
					msgServ.setMessageDeleted(messageId, 2);
				}else{
					response.sendRedirect(path+"/error");
					return;
				}
			}
			response.sendRedirect(path+ "/message");
			return;	
		}
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
				//check who is sending the message, user or admin
				int to =-1;
				int from = -1;
				String fromString="";
				String toString="";
				HttpSession session = request.getSession();
				Students std= new Students();
				
				if(session.getAttribute("user")!=null){
					std= (Students) session.getAttribute("user");
					from= std.getStudentId();
					to =ADMIN_CODE;
					fromString=std.getFirstName() +" "+ std.getlName()+" ("+ std.getEmail()+")";
					toString="Admin";
				}
				else if (session.getAttribute("admin")!=null){
					from =ADMIN_CODE;
					fromString="Admin";
					std=(Students) session.getAttribute("to");
					to=std.getStudentId();
					toString=std.getFirstName() +" "+ std.getlName()+" ("+ std.getEmail()+")";
	
				}
				//pass the parameters to the helper class for validation
				error = HelperClass.validate(map);
				//if it comes back with no error, save it to the db, if it does tell the user
				if (error.isEmpty()){
					MessageService message = new MessageService();
					try {
						message.saveData(map,from, to,fromString,toString);
						response.sendRedirect("message_outbox");
						return;
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else{
					request.setAttribute("error", error.get(0));
					return;
				}
				
	}

}
