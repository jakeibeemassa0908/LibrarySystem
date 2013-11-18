<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<c:if test="${sessionScope.user!=null}">
	<c:redirect url=""></c:redirect>
</c:if>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Carousel Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value='/css/bootstrap.css'/>" rel="stylesheet">
    <link href="<c:url value='css/main.css'/>" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<c:url value='carousel.css'/>" rel="stylesheet">
    
    <style type="text/css">
    	body{
    	background-image:url('images/book.jpg');
    	background-repeat:repeat-x;
    	}
    	.footer{
    	margin-top:100px;
    	}
    	#on {
      margin-left: 35%;
    }
    </style>
  </head>
<!-- NAVBAR
================================================== -->
  <body>
    <%@ include file="includes/nav.jsp" %>
    
    <div class="container">
    	<div class="row">
    		<div class="col-lg-3"></div>
    		<div class="col-lg-6 well">
    		<c:if test="${requestScope.message == null}">
				<div class="container">
						<c:if test="${requestScope.error != null}"> 
							<div class="alert alert-danger"><center><c:out value="${requestScope.error}"></c:out>. Try again!</center></div>
						</c:if>
						<form class="form-horizontal" role="form" method="post" action="libregister">
						  <h2 class="form-signin-heading">Register Your Library</h2><br>
	
  <div class="form-group">
    <div class="col-lg-offset-1 col-lg-10">
      <input type="text" class="form-control" id="inputEmail1" placeholder="Library User Name" name="libraryUserName" required value="<c:out value='${requestScope.libraryName}'></c:out>">
    </div>
  </div>						
  <div class="form-group">
    <div class="col-lg-offset-1 col-lg-10">
      <input type="text" class="form-control" id="inputEmail1" placeholder="Library Name" name="libraryName" required value="<c:out value='${requestScope.libraryName}'></c:out>">
    </div>
    
  </div>
  
  <div class="form-group">
    <!--<label for="inputEmail1" class="col-lg-3 control-label">Last Name </label>-->
    <div class="col-lg-offset-1 col-lg-10">
      <input type="email" class="form-control" id="inputEmail3" placeholder="Library Email" name="libraryEmail" required value="<c:out value='${requestScope.email}'></c:out>">
    </div>
  </div>
  <div class="form-group">
    <!--<label for="inputPassword1" class="col-lg-3 control-label">Password</label>-->
    <div class="col-lg-offset-1 col-lg-10">
      <input type="password" class="form-control" id="inputPassword1" placeholder="Password" name="libraryPassword" required>
    </div>
  </div>
  <div class="form-group">
    <!--<label for="inputPassword1" class="col-lg-3 control-label">Password</label>-->
    <div class="col-lg-offset-1 col-lg-10">
      <input type="password" class="form-control" id="inputPassword2" placeholder="Confirm Password" name="re-password" required>
    </div>
  </div>
  <div class="form-group">
    <!--<label for="inputEmail1" class="col-lg-3 control-label">Last Name </label>-->
    <div class="col-lg-offset-1 col-lg-10">
      <input type="text" class="form-control" id="inputEmail4" placeholder="Library Phone Number" name="libraryPhoneNumber" required value="<c:out value='${requestScope.phoneNumber}'></c:out>">
    </div>
  </div>
  
   <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
</form>
				</div>
				</c:if>
				<c:if test="${requestScope.message !=null}">
					<div class="alert alert-success"><c:out value="${requestScope.message}"></c:out> Go to <a href="login">login</a> after Verification</div>
				</c:if>
			</div>
    	
    	</div>
    </div>
    	
    	


    


    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

    <div class="container">
    	 
      <!-- FOOTER -->
      <%@ include file="includes/footer.jsp" %>

    </div><!-- /.container -->
   
          


  <script type="text/javascript">
  function faculty_changed(){
    var value= $("#faculty_select").val();
    if(value=="Engineering and Technology"){
    	var html="<option value='None'>Select your program</option>"+
    				"<option value='Aeronautical Engineering'>Aeronautical Engineering</option>"+
    				"<option value='Automobile Engineering'>Automobile Engineering</option>"+
    				"<option value='Civil Engineering'>Civil Engineering</option>"+
    				"<option value='Computer Science Engg'>Computer Science Engg</option>"+
    				"<option value='Electrical & Electronics Engg'>Electrical & Electronics Engg</option>"+
    				"<option value='Electronics & Communication Engg'>Electronics & Communication Engg</option>"+
    				"<option value='Humanities & Management'>Humanities & Management</option>"+
    				"<option value='Information Technology'>Information Technology</option>"+
    				"<option value='Mechanical Engineering'>Mechanical Engineering</option>"+
    				"<option value='Department Of Chemistry'>Department Of Chemistry</option>"+
    				"<option value='Department Of Physics'>Department Of Physics</option>"+
    				"<option value='Department Of Mathematics'>Department Of Mathematics</option>";
    	
    	$("#program_select").html(html);
    }
    else if(value=="International Programmes"){
    	var html="<option value='None'>Select your program</option>"+
    				"<option value='Information Technology'>Information Technology</option>"+
    				"<option value='International Business'>International Business</option>"+
    				"<option value='Interior Design'>Interior Design</option>";
    	$("#program_select").html(html);
    }
    if(value=="Media Studies"){
    	var html="<option value='Journalism & Mass Communication'>Journalism & Mass Communication</option>";
    	
    	$("#program_select").html(html);
    }
  }
  </script>
  </body>
</html>
