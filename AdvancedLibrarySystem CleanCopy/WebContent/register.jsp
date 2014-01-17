<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
 <html lang="en">
<c:if test="${sessionScope.user!=null}">
	<c:redirect url=""></c:redirect>
</c:if>
<c:if test="${sessionScope.admin!=null}">
	<c:redirect url="admin_home"></c:redirect>
</c:if>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Books4All | Register</title>

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
				<div class="container">
						<c:if test="${requestScope.error != null}"> 
							<div class="alert alert-danger"><center><c:out value="${requestScope.error}"></c:out>. Try again!</center></div>
						</c:if>
						<form class="form-horizontal" role="form" method="post" action="register">
						  <h2 class="form-signin-heading">Please Register</h2><br>
						
  <div class="form-group">
    <!--<label for="inputEmail1" class="col-lg-3 control-label">First Name</label>-->
    <div class="col-lg-offset-1 col-lg-5">
      <input type="text" class="form-control" id="inputEmail1" placeholder="First Name" name="fName" required value="<c:out value='${requestScope.fName}'></c:out>">
    </div>
    <div class="col-lg-5">
      <input type="text" class="form-control" id="inputEmail2" placeholder="Last Name" name="lName" required value="<c:out value='${requestScope.lName}'></c:out>">
    </div>
  </div>
  <div class="form-group">
    <!--<label for="inputEmail1" class="col-lg-3 control-label">Last Name </label>-->
    <div class="col-lg-offset-1 col-lg-10">
      <input type="email" class="form-control" id="inputEmail3" placeholder="Email" name="email" required value="<c:out value='${requestScope.email}'></c:out>">
    </div>
  </div>
  <div class="form-group">
    <!--<label for="inputPassword1" class="col-lg-3 control-label">Password</label>-->
    <div class="col-lg-offset-1 col-lg-10">
      <input type="password" class="form-control" id="inputPassword1" placeholder="Password" name="password" required>
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
      <input type="text" class="form-control" id="inputEmail4" placeholder="Phone Number" name="phoneNumber" required value="<c:out value='${requestScope.phoneNumber}'></c:out>">
    </div>
  </div>
    <div class="form-group">
      <label class="col-lg-3 control-label">Library</label>
         <select class="col-lg-4" class="form-control default" name="library" id="library_select" onchange="library_changed();">
          <option value="None">Select your Library</option>
          <c:forEach items="${requestScope.libraries}" var="library">
          	<option value="${library.libraryName}">${library.libraryName}</option>
          </c:forEach>
          
        </select>
  </div>
  <div class="form-group">
      <label class="col-lg-3 control-label">Faculty</label>
         <select class="col-lg-4" class="form-control default" name="faculty" id="faculty_select" onchange="faculty_changed();">
          <option value="None">Select your faculty</option>
          <option value="Engineering and Technology">Engineering and Technology</option>
          <option value="International Programmes">International Programs</option>
          <option value="Media Studies">Media Studies</option>
        </select>
  </div>
   <div class="form-group">
      <label class="col-lg-3 control-label">Program</label>
         <select class="col-lg-4" class="form-control default" name="program" id="program_select">
          	<option value="None">Select your program</option>
          </select>
  </div>
  
  <div class="form-group">
      <label class="col-lg-3 control-label">Gender</label>
         <select class="col-lg-3" class="form-control input-sm" name="gender">
          <option value="male"> Male</option>
          <option value="female">Female</option>
        </select>
  </div>
   <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
</form>
				</div>
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
