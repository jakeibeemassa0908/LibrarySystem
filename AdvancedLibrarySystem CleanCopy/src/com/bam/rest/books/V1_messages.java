package com.bam.rest.books;


import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.bam.services.BookService;
import com.bam.services.MessageService;
import com.bam.dto.Books;
import com.bam.dto.Messages;
import com.bam.util.ToJSON;

import org.codehaus.jettison.json.JSONArray;

@Path("/v1/messages")
public class V1_messages {
	List<Messages> messages= null;
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBooks(@QueryParam("msgId") String msgIdString) throws Exception{
		
		MessageService ms = MessageService.getInstance();
		ToJSON toJson = new ToJSON();
		Response rb = null;
		String returnString=null;
		
		JSONArray array = new JSONArray(); //JSON array to return
		
		try{
		//call the getbooks function from the Hibernate function
			if(msgIdString !=null){
				int msgId = Integer.parseInt(msgIdString);
				messages = ms.getMessages(null, null, msgId, null);
			}
			array = toJson.messagesToJSON(messages);
		
		returnString=array.toString();
		rb=Response.ok(returnString).build();
		}catch(Exception e){
			e.printStackTrace();
		}
		return rb;
	}
	
	
}
