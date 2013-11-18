<%@ include file="includes/html_top.jsp" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Books4All | Home</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value='/css/bootstrap.css'/>" rel="stylesheet">
    <link href="<c:url value='css/main.css'/>" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<c:url value='carousel.css'/>" rel="stylesheet">
  </head>

  <body>
  <!-- NAVBAR
================================================== -->
    <%@ include file="includes/nav.jsp" %>
    
    
 	<%@ include file="includes/top_message.jsp" %>
 	<div class="container">
 		<div class="row">
 			<div class="col-lg-2"></div>
 			<div class="col-lg-4"><h4>Your Books</h4><hr></div>
 			<div class="col-lg-2"></div>
 			<div class="col-lg-4"><h4>Others</h4><hr></div>
 		</div>
 		
 	</div>
 	<div class="container">
 		<div class="row">
 			<div class="col-lg-1"></div>
 			<div class="col-lg-7 ">
 			<c:if test="${sessionScope.bookingsHome == null}">
 				<h5>You have No booking</h5>
 			</c:if>
 			<c:forEach items="${sessionScope.bookingsHome}" var="booking">
 				<div class="row">
 					<div class="col-lg-5 well">
 						<img alt="" src="${booking.book.book_cover_link}" width="250" height="150">
 					</div>
 					<div class="col-lg-7">
 						<a href="book_detail/${booking.book.bookId}"><h4>${booking.book.title}</h4></a>
 						<h5>${booking.book.author}</h5>
 						<h5><span class="label label-success">${booking.bookingDate}</span><span class="see-more label label-danger">${booking.bookingReturnDate}</span></h5>
 						<div class="progress progress-striped active">
 						<c:set var="now" value="<%new Date(); %>"/>
						  <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="" aria-valuemin="0" aria-valuemax="100" style="width: ${(requestScope.now.time-booking.bookingDate.time) /(1000*60*60*24)*20}%">
						    <span class="sr-only">20% Complete (danger)</span>
						  </div>
						</div>

						<c:set var="days" value="${(booking.bookingReturnDate.time - requestScope.now.time) /(1000*60*60*24)}"/>
						<c:choose>
							<c:when test="${days>4}">
								<h5>You have 5 more days</h5>
							</c:when>
							<c:when test="${days>3}">
								<h5>You have 4 more days</h5>
							</c:when>
							<c:when test="${days>2}">
								<h5>You have 3 more days</h5> 
							</c:when>
							<c:when test="${days>1}">
								<h5>You have 2 more days</h5>
							</c:when>
							<c:when test="${days>0}">
								<h5>You have 1 more days</h5>
							</c:when>
							<c:when test="${days<0 and days >-1}">
								<h5>You have to return the book today</h5>
							</c:when>
							<c:otherwise>
								<h5>You have exceeded the authorized time.</h5>
							</c:otherwise>
						
						</c:choose>
						<a type="button" class="btn btn-default btn-xs" href="booking">View booking informations</a>
						
 					</div>
 				</div><hr>
 			</c:forEach>
 			</div>
 			<div class="col-lg-4 well"></div>
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
