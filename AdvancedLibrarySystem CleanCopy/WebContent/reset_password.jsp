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

    <title>Books4All | Reset Password</title>
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
    		<div class="col-lg-4"></div>
    		<div class="col-lg-5">
				<div class="container well">
					<c:choose>
						<c:when test="${requestScope.error != null}">
							<div class="alert alert-danger"><c:out value="${requestScope.error}"></c:out>  <a href="<c:url value='/forget_password.jsp'/>">here</div>
						</c:when>
						<c:otherwise>
						 	<form id="form_reset_pass" method="post" action="reset">
						        <h2 class="form-signin-heading">Enter Your new password</h2>
						        <input type="password" class="form-control" id="pass" placeholder="Enter Password" autofocus name="password" required></br>
						        <input type="password" class="form-control" id ="re_pass" placeholder="Re Enter Password" name="re_password" required></br>
						        <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
			      			</form>
						</c:otherwise>
					</c:choose> 
				</div>
			</div>
    	
    	</div>
    </div>
    	
    	


    


    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

    <div class="container footer">
      <!-- FOOTER -->
		<%@ include file="includes/footer.jsp" %>
    </div><!-- /.container -->
   
          


  <script type="text/javascript">
	$("#form_reset_pass").on("submit",function(){
		//alert("hello");
	});
  </script>
  </body>
</html>
