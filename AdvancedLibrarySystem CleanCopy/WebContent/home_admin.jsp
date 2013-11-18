<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<c:if test="${sessionScope.admin==null}">
	<c:redirect url=""></c:redirect>
</c:if>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Home</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value='/css/bootstrap.css'/>" rel="stylesheet">
    <link href="<c:url value='css/main.css'/>" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<c:url value='carousel.css'/>" rel="stylesheet">
  </head>

  <body>
  <!-- NAVBAR
================================================== -->
    <%@ include file="includes/nav_admin.jsp" %>
    
    
 	<%@ include file="includes/top_message.jsp" %>
 	
 	<div class="container">
 		<div class="row">
 			<div class="col-lg-7 ">
 				<center><h4>Books due today</h4></center>
 			</div>
 			<div class="col-lg-1"></div>
 			<div class="col-lg-4 ">
 				<h4>Library's Statistics</h4>
 			</div>
 				
 		</div>
	</div>
 	<div class="container">
 		<div class="row">
 			
 			<div class="col-lg-7 well">
 			
 			</div>
 			<div class="col-lg-1"></div>
 			<div class="col-lg-4 well">
 			<h5>Number of Students: <span class="label label-success">${requestScope.student_number}</span></h5>
 			<h5>Number of Books:  <span class="label label-success">${requestScope.book_number}</span></h5>
 			<h5>Number of Book Pieces:  <span class="label label-success">${requestScope.book_pieces_number}</span></h5>
 			</div>
 				
 		</div>
	</div>



	<!-- Footer 
	=========================================== -->
    <div class="container">
      <!-- FOOTER -->
        <%@ include file="includes/footer.jsp" %>

    </div><!-- /.container -->
   
   
   
         
  </body>
</html>