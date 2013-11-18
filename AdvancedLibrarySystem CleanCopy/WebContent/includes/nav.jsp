    <div class="navbar-wrapper">
      <div class="container">

        <div class="navbar navbar-inverse navbar-static-top">
          <div class="container">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="<c:url value='/home'/>">Books4All</a>
          
            </div>
                         <c:if test="${sessionScope.active_tab!='lib_register'}">
                         
            <div class="navbar-collapse collapse" id="on">
                <ul class="nav navbar-nav">
                      <li <c:if test="${sessionScope.active_tab =='home'}">class="active"</c:if>><a href="<c:url value='/index.jsp'/>"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                      <c:if test="${sessionScope.user==null}">
		                     <li <c:if test="${sessionScope.active_tab =='register'}">class="active"</c:if>><a href="register"><span class="glyphicon glyphicon-user"></span> Register</a></li>
		
		                     <!-- Menu Login-->
		
		 					<li <c:if test="${sessionScope.active_tab =='login'}">class="active"</c:if>><a href="login"><span class="glyphicon glyphicon-lock" ></span> Login</a></li>
 					</c:if>
              
              </ul>
              
              <c:if test="${sessionScope.user!=null}">
              		<ul class="nav navbar-nav">
              			<li <c:if test="${sessionScope.active_tab =='books'}">class="active"</c:if>><a href="<c:url value='/books'/>"><span class="glyphicon glyphicon-book"></span> Books</a></li>
              			<li <c:if test="${sessionScope.active_tab=='message'}">class="active"</c:if>><a href="<c:url value='/message'/>"><span class="glyphicon glyphicon-envelope"></span> Messages <span class="badge">N</span></a></li>
              			<li <c:if test="${sessionScope.active_tab=='booking'}">class="active"</c:if>><a href="<c:url value='/booking'/>"><span class="glyphicon glyphicon-folder-open"></span> Booking History</a></li>
              			<li <c:if test="${sessionScope.active_tab =='profile'}">class="active"</c:if>><a href="<c:url value='/profile/${sessionScope.user.studentId}'/>"><span class="glyphicon glyphicon-user"></span> <c:out value="${sessionScope.user.firstName}"></c:out> </a></li>
              		</ul>
              		<ul class="nav navbar-nav" id="logout">
              			<li class="dropdown">
		                  <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-cog"></span></a>
		                  <ul class="dropdown-menu">
		                    <li><a href="settings">Settings</a></li>
		                    <li class="divider"></li>
		                    <li><a href="<c:url value='/logout'/>">Logout</a></li>
		                  </ul>
		                </li>
              			
              		</ul>
              </c:if>
              
            </div>
            </c:if>
          </div>
        </div>

      </div>
    </div>
  <script src="<c:url value='/js/jquery.js'/>"></script>
  <script src="<c:url value='/js/bootstrap.min.js'/>"></script>
  <script src="<c:url value='/js/holder.js'/>"></script>