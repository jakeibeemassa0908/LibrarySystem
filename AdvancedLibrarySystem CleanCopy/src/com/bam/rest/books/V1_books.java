package com.bam.rest.books;


import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.bam.services.BookService;
import com.bam.dto.Books;
import com.bam.util.ToJSON;

import org.codehaus.jettison.json.JSONArray;

@Path("/v1/books")
public class V1_books {
	List<Books> books= null;
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBooks(@QueryParam("id") String bookIdString) throws Exception{
		BookService abs = BookService.getInstance();
		ToJSON toJson = new ToJSON();
		Response rb = null;
		String returnString=null;
		
		JSONArray array = new JSONArray(); //JSON array to return
		
		try{
		//call the getbooks function from the Hibernate function
		if(bookIdString != null){
			int bookId=Integer.parseInt(bookIdString);
			books=abs.getbooks(bookId, null, null, null, null, null);
		}else{
			books = abs.getbooks(null, null, null, null, null, null);
		}
		array= toJson.booksToJSON(books);
		
		
		returnString=array.toString();
		rb=Response.ok(returnString).build();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return rb;
	}
	
	
}
