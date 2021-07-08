	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">
	
	  
	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
	
		<h1>PUBHUB <small>Tag</small></h1>
		<hr class="book-primary">

		<p>Add a Tag by Isbn</p>		
		<form action="AddTag" method="post" class="form-horizontal" >
		  <div class="form-group">
		    <label for="isbn13" class="col-sm-4 control-label">ISBN 13</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="isbn13" name="isbn13" placeholder="ISBN 13" required="required" value="${param.isbn13 }" />
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="tag" class="col-sm-4 control-label">TAG</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="tag" name="tag" placeholder="TAG" required="required" value="${param.tag }" />
		    </div>
		  </div>
		 
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button type="submit" class="btn btn-info">Add</button>
		    </div>
		  </div>
		</form>	
		
		</div>
	</header>
	<body>
		<nav>
		<a href="http://localhost:8080/PubHub/DeleteTag">Click Here to Delete a Tag</a>
		</nav>
	</body>



	<!-- Footer -->
	<jsp:include page="footer.jsp" />