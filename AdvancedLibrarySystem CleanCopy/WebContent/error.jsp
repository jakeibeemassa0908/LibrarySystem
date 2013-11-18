<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Books4All | Error</title>
    <!-- Bootstrap core CSS -->
    <link href="<c:url value='/css/bootstrap.css'/>" rel="stylesheet">
    <link href="<c:url value='css/main.css'/>" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<c:url value='carousel.css'/>" rel="stylesheet">
    
    <style type="text/css">
    	body{
    	background-image:url('images/bookNB.jpg');
    	background-repeat:repeat-x;
    	}
    	.footer{
    	margin-top:20%;
    	}
    }
    </style>
  </head>
<!-- NAVBAR
================================================== -->
  <body>
  	<c:choose>
  		<c:when test="${sessionScope.admin!=null}">
  			<%@ include file="includes/nav_admin.jsp" %>
  		</c:when>
  		<c:otherwise>
  			<%@ include file="includes/nav.jsp" %>
  		</c:otherwise>
  	</c:choose>
  	
    
    <br><br><br><br><br>
    <div class="container">
    	<div class="row">
    		<div class="col-lg-3"></div>
    		<div class="col-lg-6">
				<div class="container well alert alert-danger">
						
						<c:choose>
					  		<c:when test="${sessionScope.admin!=null}">
					  			<center><h5>URL not valid or Data not Found</h5></center>
					  		</c:when>
					  		<c:otherwise>
					  			<center><h5>URL not valid or not Allowed, check your permissions.</h5></center>
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
  function register(){
    $('#myModal2').modal('hide');
  }
  </script>
  </body>
</html>
