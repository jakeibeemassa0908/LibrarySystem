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
	 			<div class="col-lg-3">
	 				<h4 style="color:lightgreen">Your Outbox</h4>
	 			</div>
	 			<div class="col-lg-6-offset-1">
	 				<a href="message" class="btn btn-default btn" type="button">Inbox(n)</a>
 					<a href="message_compose" class="btn btn-primary btn" type="button">Compose</a>
	 			</div>
	 		</div>
	 		
 		<br><br>
 		<div class="row">
 			<div class="col-lg-1"></div>
 			<div class="col-lg-10">
 			<c:if test="${sessionScope.messages==null}">
 				<center><h4>You have no messages in your Outbox</h4></center>
 			</c:if>
 			<c:if test="${sessionScope.messages!=null}">
 				<table class="table table-bordered table-hover">
					<tr class="success">
						<th>Subject</th>
						<th>To</th>
						<th>Date</th>
						<th>Content</th>
					</tr>
					<c:forEach items= "${sessionScope.messages}" var="message"> 
						<tr>
							<td>${message.messageSubject}</td>
							<c:if test="${sessionScope.user!=null }">
								<td><c:if test="${message.messageTo==0}">Admin</c:if></td>
							</c:if>
							<c:if test="${sessionScope.admin!=null }">
								<td>${message.messageToString}</td>
							</c:if>
							<td>${message.messageDate}</td>
							<td><a href="<c:url value='/message_detail?id=${message.messageId}'/>">${message.messageContent}</a></td>
						</tr>
					</c:forEach>
				</table>
 			</div>
 			</c:if>
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
