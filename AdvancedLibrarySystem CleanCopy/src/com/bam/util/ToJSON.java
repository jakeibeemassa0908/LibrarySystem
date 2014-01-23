package com.bam.util;

import java.util.List;

import com.bam.dto.Books;
import com.bam.dto.Messages;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 * This utility will convert a Object List data into JSON format.
 * Note:  this java class requires the ESAPI 1.4.4 jar file
 * ESAPI is used to encode data
 * 
 * @author JakeIbeeMassa
 */

public class ToJSON {
	   /**
     * This will convert database records into a JSON Array
     * Simply pass in a ResultSet from a database connection and it
     * loop return a JSON array.
     * 
     * 
     * @param books -  List of Books Objects
     * @return - JSON array
     * @throws Exception
     */
	
	public JSONArray booksToJSON(List<Books> books) throws Exception{
		JSONArray json = new JSONArray(); //JSON array that will be returned
		
		try{
			//loop through the books
			for(Books book: books){
				
				//each book will be converted into a JSON object
				JSONObject obj= new JSONObject();
				
				obj.put("bookId", book.getBookId());
				obj.put("title", book.getTitle());
				obj.put("author", book.getAuthor());
				obj.put("year", book.getYear());
				obj.put("year", book.getYear());
				obj.put("stock_number", book.getStock_number());
				obj.put("availableOne", book.getAvailableOne());
				obj.put("book_cover_link", book.getBook_cover_link());
				obj.put("ISBN", book.getISBN());
				
				json.put(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return json;	
	}
	
	public JSONArray messagesToJSON(List<Messages> messages) throws Exception{
		JSONArray json = new JSONArray(); //JSON array that will be returned
		try{
			for (Messages message: messages){
				//each message will be converted into a JSON object
				JSONObject obj= new JSONObject();
				obj.put("messageId",message.getMessageId());
				obj.put("messageContent",message.getMessageContent());
				obj.put("messageDate", message.getMessageDate());
				obj.put("messageSubject", message.getMessageSubject());
				obj.put("messageFromString", message.getMessageFromString());
				obj.put("openFlag", message.isOpenFlag());
				obj.put("messageToString", message.getMessageToString());
				json.put(obj);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return json;
		
	}
}
