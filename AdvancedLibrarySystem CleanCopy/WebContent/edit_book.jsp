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

    <title>Edit Book</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value='/css/bootstrap.css'/>" rel="stylesheet">
    <link href="<c:url value='css/main.css'/>" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<c:url value='carousel.css'/>" rel="stylesheet">
    <style type="text/css">
    #upload_button{
    display: block;
    visibility: hidden;
    width: 0;
    height: 0;
    }
    </style>
  </head>
  <body>
  <!-- NAVBAR
================================================== -->
    <%@ include file="includes/nav_admin.jsp" %>
    
    
 	<%@ include file="includes/top_message.jsp" %>
 	<div class="container">
 		<div class="row">
 			<div class="col-lg-2"></div>
    		<div class="col-lg-8 well">
    		<c:if test="${requestScope.error != null}"> 
							<div class="alert alert-danger"><center><c:out value="${requestScope.error}"></c:out>.</center></div>
						</c:if>
 		<form class="form-horizontal" method="post" action="edit_book">
						  <center><h2 class="form-signin-heading">Edit Book | <small><c:out value='${requestScope.book.title}'></c:out></small></h2></center><br>
						
  <div class="form-group">
    <div class="col-lg-offset-1 col-lg-10">
      <input type="text" class="form-control" placeholder="Book Title" name="title" required value="<c:out value='${requestScope.book.title}'></c:out>">
    </div>
  </div>
  <div class="form-group">
    <div class="col-lg-offset-1 col-lg-10">
      <input type="text" class="form-control"  placeholder="Book Author" name="author" required value="<c:out value='${requestScope.book.author}'></c:out>">
    </div>
  </div>
  <div class="form-group">
 
    <div class="col-lg-offset-1 col-lg-10">
      <input type="text" class="form-control"  placeholder="Book Release Year" name="year" required value="<c:out value='${requestScope.book.year}'></c:out>">
    </div>
  </div>
  <div class="form-group">
    <div class="col-lg-offset-1 col-lg-10">
      <input type="text" class="form-control" placeholder="Category" name="category"  value='${requestScope.book.category}' required/>
     
    </div>
  </div>
  <div class="form-group">

    <div class="col-lg-offset-1 col-lg-10">
      <input type="text" class="form-control"  placeholder="Book Publisher" name="publisher" required value="<c:out value='${requestScope.book.publisher}'></c:out>">
    </div>
  </div>
  
   <div class="form-group">
       <div class="col-lg-offset-1 col-lg-10">
      <input type="text" class="form-control" placeholder="Number of copy available" name="stock_number" required value="<c:out value='${requestScope.book.stock_number}'></c:out>">
    </div>
  </div>
  <div class="form-group">
  	<div class="col-lg-offset-1 col-lg-10">
       <label>Image cover</label>
       	<div class="fileupload-preview thumbnail" style="width: 250px; height: 350px;">
       		<img alt="" src="<c:url value='${requestScope.book.book_cover_link}.jpg'/>">
       	</div><br>
		<input type="file" name="cover_image" id="upload_button">
		<button class="btn btn-primary btn-sm" id="upload_button_styled">Chose Cover Picture</button>
     </div>
  </div>
  <div class="col-lg-offset-1 col-lg-10">
      <textarea class="form-control" rows="9" placeholder="Book Description" name="description" ><c:out value='${requestScope.book.description}'></c:out>
      </textarea>
      <br><br>
  </div>
  <input style="display:none" type="text" value="${requestScope.book.bookId}" name="id">
  <div class="form-group">
       <div class="col-lg-offset-1 col-lg-10">
       <a class="btn btn-lg btn-danger see-more" href="<c:url value='/book_detail/${requestScope.book.bookId}'/>">cancel</a>
      <button class="btn btn-lg btn-success see-more" type="submit">Save</button>
    </div>
  </div>

   
</form>
	</div>
	</div>
	</div>



	<!-- Footer 
	=========================================== -->
    <div class="container">
      <!-- FOOTER -->
        <%@ include file="includes/footer.jsp" %>

    </div><!-- /.container -->
  <script type="text/javascript">
  $('#upload_button_styled').click(function(){
	    $('#upload_button').click();
	});
  </script>  
  </body>
</html>