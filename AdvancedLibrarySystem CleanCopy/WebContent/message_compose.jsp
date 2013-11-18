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
 			<div class="col-lg-4"></div>
 			<div class="col-lg-3">
 				<h4 style="color:lightblue">Compose Your  Message</h4>
 			</div>
 			<div class="col-lg-6-offset-1">
 				<a href="message" class="btn btn-default btn" type="button">Inbox(n)</a>
 				<a href="message_outbox" class="btn btn-success btn" type="button">Outbox</a>
 			</div>
 		</div>
 		<br><br>
 		<div class="row">
 			<div class="col-lg-2"></div>
 			<div class="col-lg-8">
					<form class="form-horizontal" role="form" method="post" action="message">
						<c:if test="${sessionScope.admin!=null}">
							<div class="form-group">
							<div class="col-lg-offset-1 col-lg-10">
							<small>In case you don't know the email of the student, you can send a message by going to his/her <a href="<c:url value='/admin_students'/>">profile</a>.</small>
							</div>
							    <div class="col-lg-offset-1 col-lg-10">
							      <input type="text" class="form-control"  placeholder="Student email" name="message_to" value="${sessionScope.to.email}"required>
							    </div>
							  </div>
					  </c:if> 							
					  <div class="form-group">
					    <!--<label for="inputEmail1" class="col-lg-3 control-label">Last Name </label>-->
					    <div class="col-lg-offset-1 col-lg-10">
					    <c:if test="${sessionScope.user!=null}">
					    	<small>The message will be sent to the Library Administrator.</small>
					    </c:if>
					      <input type="text" class="form-control" id="inputEmail3" placeholder="Subject of your message" name="subject" value="${requestScope.msg_subject}" required>
					    </div>
					  </div> 
					  <div class="form-group">
					    <!--<label for="inputEmail1" class="col-lg-3 control-label">Last Name </label>-->
					    <div class="col-lg-offset-1 col-lg-10">
					      <textarea class="form-control" rows="10" placeholder="Write Your Message" name="content"></textarea>
					    </div>
					  </div>
					  <div class="form-group"> 
						  <div class="col-lg-offset-1 col-lg-10">
						      <button class="btn btn-primary see-more" type="submit">Send Message</button>
						   </div>
					</div>
					</form>
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
