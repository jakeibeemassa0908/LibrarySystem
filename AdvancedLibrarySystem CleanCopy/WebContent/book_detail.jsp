<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
<c:if test="${sessionScope.user==null and sessionScope.admin==null}">
	<c:redirect url=""></c:redirect>
</c:if>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">


    <title>Book Detail</title>
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
    
    <%@ include file="includes/top_message.jsp" %>
 	<div class="container">
 		<div class="col-lg-2  book_detail_image"></div>
 		<div class="col-lg-3  book_detail_image">
 		<c:if test="${sessionScope.books.book_cover_link == null}">
 			<img alt="" src="<c:url value='/images/book_cover_${sessionScope.books.bookId}.jpg'/>">
 		</c:if>
 		<c:if test="${sessionScope.books.book_cover_link != null}">
 			<img src="${sessionScope.books.book_cover_link }" width=250"/>
 		</c:if>
 			<hr>
 			<c:if test="${sessionScope.user!=null and sessionScope.books.availableOne gt 1}">
 				<button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#myModal2">Make reservation</button>
 			</c:if>
 			 <c:if test="${sessionScope.books.availableOne lt 1 and sessionScope.user!=null}">
	  				<span>Book Not available, Come back later</span>
   			 </c:if>
 			<c:if test="${sessionScope.admin !=null}">
 				<a type="button" class="btn btn-primary btn-block" href="<c:url value='/edit_book?bookId=${sessionScope.books.bookId}'/>">Edit Book infos</a>
 				<a type="button" class="btn btn-danger btn-block" href="<c:url value='/delete?bookId=${sessionScope.books.bookId}' />">Delete Book</a>
 			</c:if>
 		</div>
 		<div class="col-lg-7  book_detail_image">
 		<h4><small>Title: </small>${sessionScope.books.title}</h4>
 		<h5><small>Author: </small>${sessionScope.books.author}</h5>
 		<h5><small>Published year: </small>${sessionScope.books.year}</h5>
 		<h5><small>Publisher: </small>${sessionScope.books.publisher}</h5>
 		<h5><small>Category: </small>${sessionScope.books.category}</h5>
 		<h5><small>ISBN: </small><span id="isbn">${sessionScope.books.ISBN}</span></h5>
 		<hr>
 			<button type="button" class="btn btn-default">Section and Row</button>
 			<button type="button" 
 			<c:if test="${sessionScope.books.availableOne  lt 1}">
 			 class="btn btn-danger"
 			</c:if>
 			<c:if test="${sessionScope.books.availableOne gt 1}">
 			 class="btn btn-success"
 			 </c:if>>Available (${sessionScope.books.availableOne} / ${sessionScope.books.stock_number})</button>
 			<c:if test="${sessionScope.user !=null}">
 				<button type="button" class="btn btn-infos" data-toggle="modal" data-target="#myModal">Make a review</button>
 			</c:if>
 		<hr>
 		<!-- Description 
	=========================================== -->
 		<h3>Description</h3>
 		<p>${sessionScope.books.description}</p>
 		</div>
	</div>
	<hr>
	<!-- Comments 
	=========================================== -->
	<div class="container">
		<div class="row">
		 	<div class="col-lg-2"></div>
		 	<div class="col-lg-10">
		 		<div class="row">
		 		<h3>Comments and Reviews</h3>
		 	</div> 	 	 
		</div>
		</div>
			<div class="row">
			<div class="col-lg-2"></div>
			<div class="col-lg-10">
				<c:if test="${requestScope.reviews!=null}">
				<c:forEach items="${requestScope.reviews}" var="review">
					<div class="row comment">
						<div class="col-lg-12">
							<div class="comment-title"><h4>${review.commentTitle}
								<c:if test="${sessionScope.admin!=null or review.student.studentId == sessionScope.user.studentId}">
									<small class="see-more"><a href="<c:url value='/delete?commentId=${review.commentID}'/>">X</a></small>
								</c:if>
								</h4>
								<h5><small>${review.commentDate}</small> by ${review.commentUserName}</h5>
							</div>
							<div>${review.commentContent} </div>
							<!--  <a href="#" class="see-more">See more</a>-->
						</div>
					</div>
				</c:forEach>
				
			</c:if>
			<c:if test="${requestScope.reviews==null}">
				<center><small>Nobody has reviews this book yet.</small></center>
			</c:if>
			</div>
			 	<c:if test="${requestScope.review_size >3}">
			 		<a href="<c:url value='/book_reviews?bookId=${sessionScope.books.bookId}'/>" class="see-more">See more comments>>></a> 
			 	</c:if>
			</div>
		
	</div>
	<hr>
	
		<!-- Preview 
	=========================================== -->
	<div class="container">
		<div class="row">
		 	<div class="col-lg-2"></div>
		 	<div class="col-lg-10">
		 		<div class="row">
		 		<h3>Preview</h3>
		 		<br>
		 			<div class="well" id="viewerCanvas" style="width: 550px; height: 500px"></div>
		 		</div> 	 	 
		</div>
		</div>
		
	</div><br>
	<hr>
	<!-- Suggestions
	=========================================== -->
	<c:if test="${sessionScope.user !=null}">
	<div class="container">
		<div class="row">
		 <div class="col-lg-2"></div>
		 	<div class="col-lg-10">
		 		<div class="row">
		 		<h3>Books Suggestions</h3>
		 	</div> 	 	 
		</div>
		</div>
	</div>
	<div class="container">
			<div class="row book-row">
				<div class="col-lg-2"></div>
				<c:forEach items="${sessionScope.suggestions}" var="book">
					<a href="<c:url value='/book_detail/${book.bookId}'/>">
						<div class="col-lg-2 book well">
					
			 			<div class="book-img">
			 				<img src='${book.book_cover_link}' height="200" width="182"/>	
			 			</div>
			 			<div class="book-infos">
			 				<center><b>${book.title}<br>
			 				by ${book.author}</b></center>
			 			</div>
			 		</div>
			 	</a>
				</c:forEach>
				
			</div>
			<a href="#" class="see-more">See more sugegstions>>></a>
		</div>
	</c:if>		
	<c:if test="${sessionScope.admin !=null }">
		<div class="container">
		<div class="row">
		 <div class="col-lg-2"></div>
		 	<div class="col-lg-10">
		 		<div class="row">
		 		<h3>Book Pieces</h3>
		 		</div> 	 	 
			</div>
		</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-lg-2"></div>
				<div class="col-lg-6">
			 	<table class="table table-bordered table-hover">
					<tr class="success">
						<th>Piece ID</th>
						<th>Piece State</th>
					</tr>
					<c:forEach items="${sessionScope.pieces}" var="piece">
						<tr>
							<td>${piece.bookPieceId}</td>
							<td>
								<c:if test="${piece.available == true}">
									<span class="label label-success">Available</span>
								</c:if>
								<c:if test="${piece.available == false}">
									<span class="label label-danger">Not Available</span>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>
	</c:if>


	<!-- Footer 
	=========================================== -->
    <div class="container">
      <!-- FOOTER -->
        <%@ include file="includes/footer.jsp" %>

    </div><!-- /.container -->
   
   
   
        <!-- Modal Comment-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <small class="modal-title" id="myModalLabel">${sessionScope.books.title }</small>
      </div>
      <div class="modal-body">
      	<form action="<c:url value='/book_detail?id=${sessionScope.books.bookId}'/>" method="post">
      		<input type="text" class="form-control" placeholder="Review title" name="comment_title" required/><br>
       		<textarea class="form-control" rows="5" placeholder="Write your review here" name="comment_content" required></textarea>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Make your review</button>
        </form>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal --> 


        <!-- Modal Book Preview  -->
<div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <small class="modal-title" id="myModalLabel">${sessionScope.books.title }</small>
      </div>
      <div class="modal-body">
      	
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Make your review</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal --> 

<!-- Modal Booking-->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
	<c:set var="now" value="<%=new Date()%>"/>
	<c:set var="now2" value="<%=new Date(new Date().getTime() + 60*60*48*1000)%>"/>
	<c:set var="now5" value="<%=new Date(new Date().getTime() + 60*60*120*1000)%>"/>
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <small class="modal-title" id="myModalLabel">${sessionScope.books.title } | Booking.</small>
      </div>
      <div class="modal-body">
      <form method="post" action ="<c:url value='/booking?bookId=${sessionScope.books.bookId}'/>" id="formBooking">
        <h5>Reservation Date: <span style="color:brown"><fmt:formatDate type="date" 
             value="${now}" /></span></h5>
        <h5>You are expected to get the book by: <span style="color:brown"><fmt:formatDate type="date" 
            value="${now2}" /></span></h5>
        <h5>Book Return Date: <span style="color:brown"><fmt:formatDate type="date" 
            value="${now5}" /></span></h5>
      </div>
      <small><a href="#">(*)Terms and Conditions</a> applied</small>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="cancel_button">Cancel</button>
        <button type="button" class="btn btn-primary" onclick="formSubmit();">Make Reservation</button>
        </form>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script type="text/javascript">
function formSubmit()
{
	document.getElementById("cancel_button").click();
	document.getElementById("formBooking").submit();
}
</script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
    var text = $("#isbn").contents().filter(function() {
    	  return this.nodeType == 3;
    	}).text();
    
      google.load("books", "0");
      function initialize() {
        var viewer = new google.books.DefaultViewer(document.getElementById('viewerCanvas'));
        viewer.load('ISBN:'+text);
      }

      google.setOnLoadCallback(initialize);
    </script>
  </body>
</html>
