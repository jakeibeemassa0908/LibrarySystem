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

    <title>Books4All | Login</title>
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
    		<div class="col-lg-4">
				<div class="container well">
						<c:if test="${requestScope.error != null}"> 
							<div class="alert alert-danger"><c:out value="${requestScope.error}"></c:out></div>
						</c:if>
						 <form class="form-signin" method="post" action="login">
			        <h2 class="form-signin-heading">Please sign in</h2>
			        <input type="text" class="form-control" placeholder="Email address / User Name" autofocus name="email" required>
			        <input type="password" class="form-control" placeholder="Password" name="password" required>
			        <label class="checkbox">
			          <input type="checkbox" value="remember-me"> Remember me
			        </label>
			        
			        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
			        
			        <a id="forgetpass" href="forget_password.jsp">forget password?</a>
			      </form>
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
  function register(){
    $('#myModal2').modal('hide');
  }
  </script>
  </body>
</html>
