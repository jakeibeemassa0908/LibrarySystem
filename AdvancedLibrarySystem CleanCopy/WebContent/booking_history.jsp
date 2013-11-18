<%@ include file="includes/html_top.jsp" %>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Booking History</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value='/css/bootstrap.css'/>" rel="stylesheet">
    <link href="<c:url value='css/main.css'/>" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<c:url value='carousel.css'/>" rel="stylesheet">
  </head>

  <body>
  <!-- NAVBAR
================================================== -->
	<c:choose>
		<c:when test="${sessionScope.user!=null}">
	    	<%@ include file="includes/nav.jsp" %>
	    </c:when>
	    <c:otherwise>
	    	<%@ include file="includes/nav_admin.jsp" %>
	    </c:otherwise>
    </c:choose>
 	<%@ include file="includes/top_message.jsp" %>
 	<div class="container">
 		<div class="row">
	 			<c:if test="${requestScope.error!=null}">
	 				<div class="col-lg-3"></div>
 					<div class="col-lg-4">
	 				<div class="alert alert-danger"><c:out value="${requestScope.error}"></c:out></div>
	 			</c:if>
 			</div>
 			<c:choose>
 			<c:when test="${sessionScope.bookings!=null}">
 			<div class="row">
 			<div class="col-lg-12">
 				<table class="table table-bordered table-hover">
					<tr class="success">
						<th>Booking ID</th>
						<th>Book Name</th>
						<c:choose>
							 <c:when test="${sessionScope.user!=null}">
								<th>Author</th>
							</c:when>
							<c:otherwise>
								<th>Book No.</th>
								<th>Student</th>
							</c:otherwise>
						</c:choose>
						<th>Booking Date</th>
						<th>Book Issued on</th>
						<th>Return Date</th>
					</tr>
					<c:forEach items="${sessionScope.bookings}" var="booking">
						<tr class="<c:if test='${booking.returned == false}'>warning</c:if>">
							<td>${booking.bookingId}</td>
							<td><a href="book_detail/${booking.book.bookId}">${booking.book.title}</a></td>
							<c:choose>
								<c:when test="${sessionScope.user!=null}">
									<td>${booking.book.author}</td>
								</c:when>
								<c:otherwise>
									<td>${booking.bookPiece.bookPieceId}</td>
									<td><a href="<c:url value='profile/${booking.student.studentId}'/>">${booking.student.firstName} (${booking.student.email})</a></td>
								</c:otherwise>
							</c:choose>
							<td><span class="label label-default">${booking.bookingDate}</span></td>
							<c:if test="${sessionScope.user!=null}">
								<td>
									<c:if test="${booking.issued== true}"><span class="label label-success">${booking.issuedDate}</span></c:if>
									<c:if test="${booking.issued== false}"><a class="btn btn-primary btn-sm" href="booking?cancel=${booking.bookingId}">Cancel Booking</a></c:if>
								</td>
							</c:if>
							<c:if test="${sessionScope.admin!=null}">
								<c:if test="${booking.issued== false}"><td><a class="btn btn-primary btn-sm" href="booking?issue=${booking.bookingId}">Issue Book</a></td></c:if></td>
								<c:if test="${booking.issued== true}"><td><span class="label label-success">${booking.issuedDate}</span></td></c:if>
							</c:if>
							
							<td>
								<span class="label label-warning">${booking.bookingReturnDate }</span>
								<c:if test='${booking.returned == true}'>
									<span class="glyphicon glyphicon-ok"></span>
								</c:if>
								
								<br>
								<c:if test="${booking.issued==true && sessionScope.admin!=null && booking.returned == false}">
									<a class="btn btn-info btn-xs" href="<c:url value='/booking?return=${booking.bookingId}'/>">Return Now</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
 			</div>
 			</div>
 			</c:when>
 		<c:otherwise>		
 				<center><h6>No booking available</h6></center>
 		</c:otherwise>
 		</c:choose>
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
