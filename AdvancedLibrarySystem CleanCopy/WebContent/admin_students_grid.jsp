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

    <title>Students</title>

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
 		<a class="see-more" href="admin_students?view=list"><span class="glyphicon glyphicon-th-list"></span> List View</a>
 		
 		<br><br>
 		<div class="row book-row">
	 		<c:forEach items="${sessionScope.students}" var="student" varStatus="status"> 
		 		<a href="profile/${student.studentId}">
		 			<div class="col-lg-2 book well">
		 			<div class="book-img">
		 				<c:if test="${student.gender=='male'}">
		 					<img src="images/profile_empty_boy.png" height="200" width="182"/>
		 				</c:if>
		 				<c:if test="${student.gender=='female'}">
		 					<img src="images/profile_empty_girl.png" height="200" width="182"/>
		 				</c:if>	
		 			</div>
		 			<div class="book-infos">
		 				<center><b>${student.firstName} ${student.lName}</b></center>
		 				<center><small>${student.program}</small></center>
		 				
		 			</div>
		 			<c:if test="${status.index==4}"></div></a><div class="row book-row"></c:if>
		 		</div>
	 		</c:forEach>
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