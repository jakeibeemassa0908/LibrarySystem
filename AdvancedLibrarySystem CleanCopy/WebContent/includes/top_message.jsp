<div class="container">
 	<div class="row">
 		<div class="col-lg-2 ">
 		</div>
 		<div class="col-lg-8">
 			<div class="row">
 				<div class="col-lg-12">
 				<form method="get" action="<c:url value='/result'/>">
				        <div class="col-lg-11">
				        	<div class="input-group">
						      	<input type="text" class="form-control" value="${requestScope.search_query}" placeholder="Search Book by Title, Author or Category <c:if test='${sessionScope.admin!=null }'> or Search Student</c:if>"  name="search_query" required>
						      	<span class="input-group-btn ">
						        	<button class="btn btn-primary" type="submit">Go!</button>
						      </span>
						    </div><!-- /input-group -->
						    </div>
				 </form><br><br><p><b>You don't know the Title? you can either <a href="#">browse</a> 
				 our books, use the <a href="#">advanced search</a> <c:if test="${sessionScope.admin==null}">or see the <a href="#">suggestions.</a></c:if></b></p>
				 </div>
			</div>
 		</div>
 		</div><hr>
 	</div>