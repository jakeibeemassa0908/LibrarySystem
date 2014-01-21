<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<c:set var="active_tab" value="home" scope="session" />
<c:if test="${sessionScope.user!=null}">
	<c:redirect url="home"></c:redirect>
</c:if>
<c:if test="${sessionScope.admin!=null}">
	<c:redirect url="admin_home"></c:redirect>
</c:if>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome to Books4All</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value='/css/bootstrap.css'/>" rel="stylesheet">
    <link href="<c:url value='css/main.css'/>" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<c:url value='carousel.css'/>" rel="stylesheet">
    
    <style type="text/css">
    #on {
      margin-left: 35%;
    }
    </style>
  </head>
<!-- NAVBAR
================================================== -->
  <body>
	<%@ include file="includes/nav.jsp" %>


    <!-- Carousel
    ================================================== -->
    <div id="myCarousel" class="carousel slide">
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner">
        <div class="item active">
          <img src="images/book.jpg" alt="First slide">
          <div class="container">
            <div class="carousel-caption">
              <h1>Knowledge is everywhere around you</h1>
              <p>.</p>
              <p><a class="btn btn-large btn-primary" href="register">Sign up today</a></p>
            </div>
          </div>
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
      <a class="right carousel-control" href="#myCarousel" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
    </div><!-- /.carousel -->



    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

    <div class="container marketing">

      <!-- Three columns of text below the carousel -->
      <div class="row">
        <div class="col-lg-4">
          <img class="img-circle" src="images/android.gif" alt="Generic placeholder image">
          <h2>Check out our Android App</h2>
          <p>Our Android app is made will let you know about the books you are expecting, telling you when it's time to take them or give them back
          You can use the Codebar scanner to view the comments and popularity of physical book before you take them.</p>
          <p><a class="btn btn-default" href="#">View details &raquo;</a></p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
          <img class="img-circle" src="data:image/png;base64," data-src="holder.js/140x140" alt="Generic placeholder image">
          <h2>Register Your Library</h2>
          <p>Transform the way things are done in your Library, Join Our community and make things easier and faster in Your Library</p>
          <p><a class="btn btn-default" href="libregister">View details &raquo;</a></p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
          <img class="img-circle" src="data:image/png;base64," data-src="holder.js/140x140" alt="Generic placeholder image">
          <h2>Download Our Desktop App</h2>
          <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
          <p><a class="btn btn-default" href="#">View details &raquo;</a></p>
        </div><!-- /.col-lg-4 -->
      </div><!-- /.row -->


      <!-- START THE FEATURETTES -->
      <!-- /END THE FEATURETTES -->


      <!-- FOOTER -->
      <%@ include file="includes/footer.jsp" %>

    </div><!-- /.container -->





  <script type="text/javascript">
  function register(){
    $('#myModal2').modal('hide');
  }

  </body>
</html>
    