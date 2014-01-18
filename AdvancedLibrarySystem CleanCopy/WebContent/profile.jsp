<%@ include file="includes/html_top.jsp" %>
<%@ page import="java.util.Date" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Profile</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value='/css/bootstrap.css'/>" rel="stylesheet">
    <link href="<c:url value='/css/main.css'/>" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<c:url value='/carousel.css'/>" rel="stylesheet">
    
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
    
 	<%@include file="includes/top_message.jsp" %>
 	<div class="container">
 		<div class="col-lg-2  book_detail_image"></div>
 				<c:choose>
 					<c:when test="${fn:length(sessionScope.student.profile_picture) gt 0}">
 						<div class="col-lg-3  book_detail_image"><img alt="" height="240" width="240" id="image_frame" src="<c:url value='/getProfileImage?id=${sessionScope.student.studentId}'/>">
 					</c:when>
 					<c:otherwise>
 						<c:if test="${sessionScope.student.gender=='male'}">
				 			<div class="col-lg-3  book_detail_image"><img alt="" height="240" width="240" id="image_frame" src="<c:url value='/images/profile_empty_boy.png'/>">
				 		</c:if>
				 		<c:if test="${sessionScope.student.gender=='female'}">
				 			<div class="col-lg-3  book_detail_image"><img alt="" src="<c:url value='/images/profile_empty_girl.png'/>">
				 		</c:if>
 					</c:otherwise>
 				</c:choose>
 			<c:if test="${sessionScope.user!=null}">
 				<a href="#" onclick="upload_image();"><center><span class="label label-danger">edit profile picture </span></center></a>
 				<a href="#" onclick="send_image()" style="visibility:hidden" id="save_button"><center><span class="label label-primary">save image</span></center></a>
 			</c:if>
 			<form method="post" action="<c:url value='/profileUpload'/>" enctype="multipart/form-data">
 				<input type="file" name="profile_picture" id="upload_button" style="visibility:hidden">
 				<input type="submit" id="send_image" style="visibility:hidden"/>
 			</form>
 			<hr>
 			<c:if test="${sessionScope.user!=null}">
 				<a type="button" class="btn btn-primary btn-block" href="<c:url value='/edit_profile?id=${sessionScope.student.studentId}'/>">Edit Profile</a>
 			</c:if>
 			<c:if test="${sessionScope.admin!=null}">
 				<a type="button" class="btn btn-primary btn-block" href="<c:url value='/message_compose?to=${sessionScope.student.studentId}'/>">Send Message</a><br>
 				<a type="button" class="btn btn-danger btn-block" href="#">Delete account</a>
 			</c:if>
 		</div>
 		<div class="col-lg-7  book_detail_image">
 		<c:if test="${sessionScope.user!=null}">
 		<h4 style="color:brown;"><c:out value="${sessionScope.user.firstName}"></c:out> <c:out value="${sessionScope.user.lName}"></c:out> </h4>
 		<h5><c:out value="${sessionScope.user.department}"></c:out> -- <c:out value="${sessionScope.user.program}"></c:out> </h5>
 		<h5><c:out value="${sessionScope.user.phoneNumber}"></c:out> </h5>
 		<h5><c:out value="${sessionScope.user.email}"></c:out> </h5>
 		</c:if>
 		
 		<c:if test="${sessionScope.admin!=null}">
 		<h4 style="color:brown;"><c:out value="${sessionScope.student.firstName}"></c:out>  <c:out value="${sessionScope.student.lName}"></c:out> </h4>
 		<h5><c:out value="${sessionScope.student.department}"></c:out> -- <c:out value="${sessionScope.student.program}"></c:out> </h5>
 		<h5><c:out value="${sessionScope.student.phoneNumber}"></c:out> </h5>
 		<h5><c:out value="${sessionScope.student.email}"></c:out> </h5>
 		</c:if>
 		
 		<hr>
 			<button type="button" class="btn btn-default" disabled="disabled">Number of Books read</button> 
 			<button type="button" class="btn btn-success" disabled="disabled">Number of book having</button>
 		<hr>
 		<c:if test="${sessionScope.user!=null}">
	 		<c:forEach items="${sessionScope.bookings}" var="booking">
	 			<p style="<c:if test='${booking.returned == false}'>color:green</c:if>">${booking.book.title} (<span style="color:red">${booking.bookingReturnDate }</span>)</p>
	 		</c:forEach>
	 	</c:if>
	</div>

</div>
<c:if test="${sessionScope.admin!=null}">
<div class="container">
	<div class="row">
	<div class="col-lg-2"></div><hr>
	
	 		<center><b>Booking History</b></center>
	
</div><br>
</div>
<div class="container">

	<div class="row">
		<c:choose>
 			<c:when test="${sessionScope.bookings!=null}">
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
							<td><a href="<c:url value='/book_detail/${booking.book.bookId}'/>">${booking.book.title}</a></td>
							<c:choose>
								<c:when test="${sessionScope.user!=null}">
									<td>${booking.book.author}</td>
								</c:when>
								<c:otherwise>
									<td>${booking.bookPiece.bookPieceId}</td>
									<td>${booking.student.firstName} (${booking.student.email})</td>
								</c:otherwise>
							</c:choose>
							<td><span class="label label-default">${booking.bookingDate}</span></td>
							<c:if test="${booking.issued== false}"><td><a class="btn btn-primary btn-sm" href="<c:url value='/booking?issue=${booking.bookingId}'/>">Issue Book</a></td></c:if>
							<c:if test="${booking.issued== true}"><td><span class="label label-success">${booking.issuedDate}</span></td></c:if>
							<td>
								<c:choose>
									<c:when test="${sessionScope.now.time gt booking.bookingReturnDate.time && booking.returned==false}">
										<span class="label label-danger"> 
									</c:when>
									<c:otherwise>
										<span class="label label-warning"> 
									</c:otherwise>
								</c:choose>
								
								${booking.bookingReturnDate }</span>
								<c:if test='${booking.returned == true}'>
									<span class="glyphicon glyphicon-ok"></span>
								</c:if>
								<br>
								<c:if test="${booking.issued==true && sessionScope.admin!=null  && booking.returned == false}">
									<a class="btn btn-info btn-xs" href="<c:url value='/booking?return=${booking.bookingId}'/>">Return Now</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
 			</div>
 			</c:when>
 			<c:otherwise>
 				
 				<center><h6>No booking available</h6></center>
 			</c:otherwise>
 		</c:choose>
 			
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
  	<script type="text/javascript">
  	function upload_image(){
  		$("#upload_button").click();
  	}
  	
  	function send_image(){
  		$("#send_image").click();
  	}
  	
  	//function to display image in the browser after the selection.
  	(function() {

  	    var URL = window.URL || window.webkitURL;

  	    var input = document.querySelector('#upload_button');
  	    var preview = document.querySelector('#image_frame');
  	    
  	    // When the file input changes, create a object URL around the file.
  	    input.addEventListener('change', function () {
  	        preview.src = URL.createObjectURL(this.files[0]);
  	        $("#save_button").css('visibility','visible');
  	    });
  	    
  	    // When the image loads, release object URL
  	    preview.addEventListener('load', function () {
  	        URL.revokeObjectURL(this.src);
  	        
  	    });
  	})();
	</script>
</html>
