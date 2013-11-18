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
 				<center><a href="add_book" class="btn btn-success btn" type="button"><span class="glyphicon glyphicon-plus"></span> <b>Add New Book</b></a></center>
 				<a class="see-more" href="admin_books?view=list"><span class="glyphicon glyphicon-th-list"></span> List View</a>
 		</div>
 		
 		<br><br>
 		<div class="row book-row">
	 		<c:forEach items="${sessionScope.books}" var="book" varStatus="status"> 
		 		<a href="book_detail/${book.bookId}"><div class="col-lg-2 book well">
		 			<div class="book-img">
		 				<c:if test="${book.book_cover_link == null}">
		 					<img src="images/book_cover_${book.bookId}.jpg" height="200" width="182"/>	
		 				</c:if>
		 				<c:if test="${book.book_cover_link != null}">
		 					<center><img src="${book.book_cover_link}" height="200" width="182"/></center>
		 				</c:if>
		 			</div>
		 			<div class="book-infos">
		 				<b><center>${book.title}<br>
		 				by ${book.author}</center></b>
		 			</div>
		 			<c:if test="${status.index==4}"></div> </a><div class="row book-row"></c:if>
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