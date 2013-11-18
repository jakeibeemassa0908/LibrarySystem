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

    <title>Home</title>

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
		
 		<form class="form-horizontal" method="post" action="add_book">
						  <h2 class="form-signin-heading">Add a New Book </h2><br>
						   <div class="form-group"> 
						 <div class="col-lg-offset-1 col-lg-5">
						 	<div class="input-group">
						      	<input type="text" class="form-control" value="" placeholder="Enter ISBN" id="ISBN" name="isbn">
						      	<span class="input-group-btn ">
						        	<button class="btn btn-primary" id="isbn">Go!</button>
						      </span>
						    </div><!-- /input-group -->
						   </div>
						  If ISBN is Not Found Fill Manually </div>
  <div class="form-group">
  <hr>
    <div class="col-lg-offset-1 col-lg-10">
      <input type="text" class="form-control" placeholder="Book Title" name="title" required value="<c:out value='${requestScope.title}'></c:out>" id="title">
    </div>
  </div>
  <div class="form-group">
    <div class="col-lg-offset-1 col-lg-10">
      <input type="text" class="form-control"  placeholder="Book Author" name="author" id="author" required value="<c:out value='${requestScope.author}'></c:out>">
    </div>
  </div>
  <div class="form-group">
 
    <div class="col-lg-offset-1 col-lg-10">
      <input type="text" class="form-control"  placeholder="Book Release Year" id="year" name="year" required>
    </div>
  </div>
  <div class="form-group">
    <div class="col-lg-offset-1 col-lg-10">
      <input type="text" class="form-control" placeholder="Category" name="category" id="category" required>
      <button class="btn btn-primary btn-xs see-more">Add New Category</button>
    </div>
  </div>
  <div class="form-group">

    <div class="col-lg-offset-1 col-lg-10">
      <input type="text" class="form-control"  placeholder="Book Publisher" id="publisher" name="publisher" required value="<c:out value='${requestScope.publisher}'></c:out>">
    </div>
  </div>
  
   <div class="form-group">
       <div class="col-lg-offset-1 col-lg-10">
      <input type="text" class="form-control" placeholder="Number of copy available" name="stock_number" required>
    </div>
  </div>
  <div class="form-group">
  	<div class="col-lg-offset-1 col-lg-10">
       <label>Image cover</label>
       	<div class="fileupload-preview thumbnail">
       		<img alt="" src="" id="img">
       	</div><br>
		<input type="file" name="book_cover" id="upload_button">
		<button class="btn btn-primary btn-sm" id="upload_button_styled">Chose Cover Picture</button>
     </div>
  </div>
  <input type="text" class="form-control" placeholder="Book URL" name="url" required value="" id="url">
  <div class="col-lg-offset-1 col-lg-10">
      <textarea class="form-control" rows="9" placeholder="Book Description" id="description" name="description"></textarea>
      <br><br>
  </div>
  
  <div class="form-group">
       <div class="col-lg-offset-1 col-lg-10">
      <button class="btn btn-lg btn-success btn-block" type="submit">Add book</button>
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
  var API="?key=AIzaSyA-d1ksBxxfgHFcWHfXdwlTPlSK0B51XPg";
  $('#isbn').click(function(){
	  var titleForm=document.getElementById("title");
	  var authorForm=document.getElementById("author");
	  var releaseDateForm=document.getElementById("year");
	  var publisherForm=document.getElementById("publisher");
	  var titleDescriptionForm=document.getElementById("description");
	  var imageForm=document.getElementById("img");
	  var categoryForm=document.getElementById("category");
	  var urlForm=document.getElementById("url");
	  
	  
	  var isbn=document.getElementById("ISBN").value;
	  var requestURL= "https://www.googleapis.com/books/v1/volumes?q=isbn:"+isbn;
	  $.getJSON(requestURL, function (data) {
		  var item=data.items[0].volumeInfo;
		  var author=item.authors;
		  var title=item.title;
		  var description=item.description;
		  var pubDate=item.publishedDate;
		  var rating=item.averageRating;
		  var imageLink=item.imageLinks.thumbnail;
		  imageLink=imageLink.replace("zoom=0", "zoom=2");
		  imageLink=imageLink.replace("zoom=1", "zoom=2");
		  var publisher=item.publisher;
		  var category=item.categories;
		  urlForm.value=imageLink;
		  
		  titleForm.value=title;
		  authorForm.value=author;
		  releaseDateForm.value=pubDate.substring(0,4);
		  publisherForm.value=publisher;
		  titleDescriptionForm.value=description;
		  imageForm.src=imageLink;
		  categoryForm.value=category;
	  });
	  
	  
  });
  </script>  
  </body>
</html>