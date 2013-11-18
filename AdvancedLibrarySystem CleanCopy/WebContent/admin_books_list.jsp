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

    <title>Books</title>

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
 				<center><a  href="add_book" class="btn btn-success btn" type="button"><span class="glyphicon glyphicon-plus"></span> <b>Add New Book</b></a></center>
 				<a class="see-more" href="admin_books?view=grid"><span class="glyphicon glyphicon-th"></span> Grid View</a>
 		</div>
 		
 		<br><br>
 		<div class="row">
 			<div class="col-lg-12">
 				<c:if test="${sessionScope.books!=null}">
 				<table class="table table-bordered table-hover">
					<tr class="warning">
						<th><a href="#">ID</a></th>
						<th><a href="#">Title</a></th>
						<th><a href="#">Author</a></th>
						<th><a href="#">Year</a></th>
						<th><a href="#">Category</a></th>
						<th><a href="#">Publisher</a></th>
						<th><a href="#">Date added</a></th>
						<th><a href="#">N.available</a></th>
						
					</tr>
					<c:forEach items="${sessionScope.books}" var="book"> 

						<tr>
							<td>${book.bookId}</td>
							<td><a href="book_detail/${book.bookId}">${book.title}</a></td>
							<td>${book.author}</td>
							<td>${book.year}</td>
							<td>${book.category}</td>
							<td>${book.publisher}</td>
							<td>${book.date_added}</td>
							<td style="<c:if test='${book.availableOne > 0}'>color:green</c:if>"><span class="label label-success">${book.availableOne}/${book.stock_number}</span></td>
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