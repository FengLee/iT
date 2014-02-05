<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <title>Issue Tracker</title>
	    <link type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	  	
	</head>
	<body>
			<header>
			    <nav class="navbar navbar-default navbar-static-top" role="navigation">
			        <div class="container navbar-left">
			           <h3>Issue Tracker</h3>
			        </div>
			    </nav>
			</header>
			<div class="container bs-docs-container">
			<div class="row">
			<div class="col-md-2">
			
			</div>
			<div class="col-md-9">
				<div class="panel panel-primary">
					<div class="panel-heading">		    		
		    		</div>			
			  		<div class="panel-body">		  			
			      		<c:if test="${not empty error}">
							<div class="errorblock">
								Your login attempt was not successful, try again.<br /> Caused :
								${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
							</div>
						</c:if>
						<div class="container">
							<form class="form-horizontal" id="command" action="<c:url value='j_spring_security_check' />" method='POST'>
							    <div class="form-group">
							        <label for="username" class="col-sm-2 control-label">Username</label>
							        <div class="col-sm-10">
							            <input class="form-control" id="username" name='j_username'>
							        </div>
							    </div>
							    <div class="form-group">
							        <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
							        <div class="col-sm-10">
							            <input type="password" class="form-control" id="inputPassword3" placeholder="Password" name='j_password'>
							        </div>
							    </div>
							    <div class="form-group">
							        <div class="col-sm-offset-2 col-sm-10">
							            <button type="submit" class="btn btn-default" value="Submit">Sign in</button>
							        </div>
							    </div>
							</form>
						</div>
			      	</div>
			  	</div>
			  	</div>
			  	</div>
		  	</div>
			
	</body>
	<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.10.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</html>