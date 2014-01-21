<%@ include file="includes/html_top.jsp" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Books4All | Search</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value='/css/bootstrap.css'/>" rel="stylesheet">
    <link href="<c:url value='css/main.css'/>" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<c:url value='carousel.css'/>" rel="stylesheet">
  </head>

  <body>
  <!-- NAVBAR
================================================== -->
    <c:if test="${sessionScope.user!=null}">
    	<%@ include file="includes/nav.jsp" %>
    </c:if>
    <c:if test="${sessionScope.admin!=null}">
    	<%@ include file="includes/nav_admin.jsp" %>
    </c:if>
    
    
 	<%@ include file="includes/top_message.jsp" %>
 	<div class="container">
 		<h5><c:if test="${requestScope.books==null}">No </c:if>Result for your search: <span style="color:brown">${requestScope.search_query}</span></h5>
	</div><br>
	<div class="container">
	 	<div class="row book-row">
	 	<c:if test="${sessionScope.admin!=null }">
	 		<center>Books</center>
	 	</c:if>
	 		<c:forEach items="${requestScope.books}" var="book" varStatus="status"> 
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
		 				<center><b>${book.title}<br>
		 				by ${book.author}</b></center>
		 			</div>
		 			<c:if test="${status.index==4}"></div>
		 		</a>
		 		<div class="row book-row">
		 </c:if>
		 		</div>
	 		</c:forEach>
	 	</div>
	 </div>
	 <c:if test="${sessionScope.admin!=null }">
		 <div class="container">
		 	<center>Books</center>
		 	<div class="row book-row">
		 		<c:forEach items="${requestScope.students}" var="student" varStatus="status"> 
			 		<a href="profile/${student.studentId}"><div class="col-lg-2 book well">
			 			<div class="book-img">
			 				<c:choose>
		 				<c:when test="${fn:length(student.profile_picture) gt 0}">
		 					<img src="<c:url value='/getProfileImage?id=${student.studentId}'/>" height="200" width="182"/>
		 				</c:when>
		 				<c:otherwise>
		 					<c:if test="${student.gender=='male'}">
		 						<img src="images/profile_empty_boy.png" height="200" width="182"/>
		 					</c:if>
		 					<c:if test="${student.gender=='female'}">
		 						<img src="images/profile_empty_girl.png" height="200" width="182"/>
		 					</c:if>	
		 				</c:otherwise>
		 			</c:choose>
			 			</div>
			 			<div class="book-infos">
			 				<b><center>${student.firstName} ${student.lName}<br></center>
			 				<center><small>${student.program}</small></center></b>
			 			</div>
			 			<c:if test="${status.index==4}"></div></a><div class="row book-row"></c:if>
			 		</div>
		 		</c:forEach>
		 	</div>
		 </div>
	 </c:if>

	

	<!-- Footer 
	=========================================== -->
    <div class="container">
      <!-- FOOTER -->
        <%@ include file="includes/footer.jsp" %>

    </div><!-- /.container -->
   
   
   
         
  </body>
</html>
