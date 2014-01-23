<%@ include file="includes/html_top.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
  <div id="wrapper">
  <div id="main">
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
	 				<h4>Your Inbox</h4>
	 			</div>
	 			<div class="col-lg-6-offset-1">
	 				<a href="message_outbox" class="btn btn-success btn" type="button">Outbox</a>
 					<a href="message_compose" class="btn btn-primary btn" type="button">Compose</a>
	 			</div>
	 		</div>
 		<br><br>
 		<div class="row">
 			<div class="col-lg-1"></div>
 			<div class="col-lg-10">
 			<c:if test="${sessionScope.messages_in==null}">
 				<center><h4>You have no messages in your Inbox</h4></center>
 			</c:if>
 			<c:if test="${sessionScope.messages_in!=null}">
 				<table class="table table-bordered table-hover">
					<tr class="warning">
						<th>Subject</th>
						<th>From</th>
						<th>Date</th>
						<th>Content</th>
					</tr>
					<c:forEach items= "${sessionScope.messages_in}" var="message"> 
						<tr class="<c:if test='${message.openFlag==false}'>success</c:if>">
							<td>${message.messageSubject}</td>
							<c:if test="${sessionScope.admin!=null }">
								<td><a href="<c:url value='/profile/${message.messageFrom}'/>">${message.messageFromString}</a></td>
							</c:if>
							<c:if test="${sessionScope.user!=null }">
								<td>${message.messageFromString}</td>
							</c:if>
							<td>${message.messageDate}</td>
							<td><a href="<c:url value='/message_detail?id=${message.messageId}'/>">${fn:substring(message.messageContent,0,60)}...</a></td>
						</tr>
					</c:forEach>
				</table>
 			</div>
 			</c:if>
 		</div>
 		
	</div>
</div>
</div>


	<!-- Footer 
	=========================================== -->
    <div class="container" id="footer">
      <!-- FOOTER -->
        <%@ include file="includes/footer.jsp" %>

    </div><!-- /.container -->
   
   
   
         
  </body>
</html>
