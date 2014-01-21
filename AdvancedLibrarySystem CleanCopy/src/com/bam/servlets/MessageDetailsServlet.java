package com.bam.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bam.dto.Messages;
import com.bam.dto.Students;
import com.bam.services.MessageService;

@WebServlet("/message_detail")
public class MessageDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int ADMIN_CODE=0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("message_details.jsp");
		HttpSession session = request.getSession();
		MessageService ms = MessageService.getInstance();
		int whoOpen;
		List<Messages> messages=null;
		Integer from, to, ID;
		from=to=ID=null;
		String query= request.getParameter("id");
		try {
			ID=Integer.parseInt(query);
			if(session.getAttribute("admin")!=null){
				from=null;
				to=null;
				whoOpen=ADMIN_CODE;
				
			}else{
				from=null;
				to=null;
				Students std= new Students();
				std=(Students) session.getAttribute("user");
				std.getStudentId();
				whoOpen=std.getStudentId();   
				messages=ms.getMessages(from, to, ID,null);
				if ((messages.get(0).getMessageFrom()!=std.getStudentId() && messages.get(0).getMessageTo()!=std.getStudentId())
						||(messages.isEmpty() || messages==null)){
						String path=request.getContextPath();
						response.sendRedirect(path+"/error");
						return;
				}
			}
			messages=ms.getMessages(from, to, ID,null);
			if (messages.get(0).getMessageTo()==whoOpen){
				ms.updateMessage(ID);
			}
			messages=ms.getMessages(from, to, ID,null);
			
			if (messages.isEmpty()){
				String path=request.getContextPath();
				response.sendRedirect(path+"/error");
				return;
			}
			else{
			session.setAttribute("messagesdet",messages);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			String path=request.getContextPath();
			response.sendRedirect(path+"/error");
			return;
		}
		dispatcher.forward(request, response);
		return;
	}
}
