<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<c:if test="${sessionScope.user!=null || sessionScope.admin!=null }">
	<c:redirect url=""></c:redirect>
</c:if>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Forgot password</title>

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
    	margin-top:150px;
    	}
    </style>
  </head>
<body>
	<%@ include file="includes/nav.jsp" %>
	<div class="container">
		<div class="row">
			<div class="col-lg-2"></div>
			<div class="col-lg-8 well">
				<c:if test="${requestScope.successMessage ==null}">
					<c:if test="${requestScope.error != null}"> 
								<div class="alert alert-danger"><c:out value="${requestScope.error}"></c:out>
								 Do you want to <a href="register">Register?</a></div>
							</c:if>
					 <form class="form-signin" method="post" action="forget">
				        <h2 class="form-signin-heading">Enter your email address</h2>
				        <hr>
				        <div class="col-lg-12">
				        	<div class="input-group">
						      <input type="email" class="form-control" placeholder="Email address" autofocus name="email">
						      <span class="input-group-btn ">
						        <button class="btn btn-primary" type="submit">Send email</button>
						      </span>
						    </div><!-- /input-group -->
						    </div>
				 </form>
				      <hr>
				      <small>A link with will be sent to you email, follow it to retrieve your password</small>
				</c:if>
				<c:if test="${requestScope.successMessage !=null}">
					<div class="alert alert-success"><c:out value="${requestScope.successMessage}"></c:out></br></br>
						<a href='<c:out value="${requestScope.linkToResetPassowrd}"></c:out>'><c:out value="${requestScope.linkToResetPassowrd}"></c:out></a>
					</div>
				</c:if>
			</div>
		</div>
	</div>
	<div class="container footer">
		<%@ include file="includes/footer.jsp" %>
	</div>
</body>
</html>