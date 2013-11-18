<%@ include file="includes/html_top.jsp" %>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Message</title>

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
 		<div class="row">
 			<div class="col-lg-5"></div>
 			<div class="col-lg-4">
 				<a href="message" class="btn btn-default btn" type="button">All Messages</a>
 				<a href="message_compose?to=${sessionScope.messagesdet[0].messageFrom}&sub=Re:${sessionScope.messagesdet[0].messageSubject}" class="btn btn-success btn" type="button">Reply</a>
 				<a href="" class="btn btn-danger btn" type="button">Delete</a>
 				
 			</div>
 		</div>
 		<br><br>
 		<div class="row">
 			<div class="col-lg-4"></div>
 			<div class="col-lg-4"><center class="well">
 				<h4><small>Subject:</small> ${sessionScope.messagesdet[0].messageSubject}</h4>
 				<h5><small>From :</small>${sessionScope.messagesdet[0].messageFromString}</h5>
 				<h5><small>To :</small>${sessionScope.messagesdet[0].messageToString}</h5>
 				<h5><small>Date:</small> ${sessionScope.messagesdet[0].messageDate}</h5>
 				<hr>
 				<p>${sessionScope.messagesdet[0].messageContent}</p>
 				</center>
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
