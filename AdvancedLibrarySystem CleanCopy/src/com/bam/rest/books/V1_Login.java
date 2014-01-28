package com.bam.rest.books;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.bam.dto.Students;
import com.bam.services.LoginService;


@Path("/v1/login")
public class V1_Login {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response StudentLogin(String incomingString) throws Exception{
		 String returnString = null;
         JSONArray jsonArray = new JSONArray();
         JSONObject jsonObject = new JSONObject();
         
         try{
        	 JSONObject partData = new JSONObject(incomingString);
        	 String email=partData.optString("email");
        	 String password=partData.optString("password");
        	 if(email!=null && password!=null){
        		 LoginService login = LoginService.getInstance();
        		 List<Students> students=login.logStudentIn(email, password);
        		 if(students.isEmpty()){
        			 jsonObject.put("status", "404");
        		 }else{
        			 jsonObject.put("status", "200");
        			 Students student = students.get(0);
        			 jsonObject.put("studentId", student.getStudentId());
        			 jsonObject.put("name", student.getFirstName());
        		 }
        		 returnString =jsonArray.put(jsonObject).toString();
        	 }
         }catch(Exception e){
        	 
         }
		return Response.ok(returnString).build();
		
	}

}
