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
 		<a class="see-more" href="admin_students?view=grid"><span class="glyphicon glyphicon-th"></span> Grid View</a>
 		
 		<br><br>
 		<div class="row">
 			<div class="col-lg-12">
 				<c:if test="${sessionScope.students!=null}">
 				<table class="table table-bordered table-hover">
					<tr class="warning">
						<th><a href="?sort='id'">ID</a></th>
						<th><a href="#">First Name</a></th>
						<th><a href="#">Last Name</a></th>
						<th><a href="#">Department</a></th>
						<th><a href="#">Program</a></th>
						<th><a href="#">Email</a></th>
						<th><a href="#">Phone Number</a></th>
						<th><a href="#">Gender</a></th>
						
					</tr>
					<c:forEach items="${sessionScope.students}" var="student"> 

						<tr>
							<td>${student.studentId}</td>
							<td><a href="<c:url value='/profile/${student.studentId}'/>">${student.firstName}</a></td>
							<td>${student.lName}</td>
							<td>${student.department}</td>
							<td>${student.program}</td>
							<td>${student.email}</td>
							<td>${student.phoneNumber}</td>
							<td>${student.gender}</td>
						</tr>
					</c:forEach>
				</table>
				</c:if>
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