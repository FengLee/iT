<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Issue Tracker</title>
    <link type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  	
</head>
<body>
<c:set var="userId" value="<%=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getDetails().toString()%>"/>
<header>
    <nav class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container navbar-left">
           <h3>Issue Tracker</h3>
        </div>
        <div class="navbar-right page-header-right">
		<c:if test="${pageContext['request'].userPrincipal != null}">
						
			<a href="<c:url value="/" />" class="btn btn-default navbar-btn">
				Logged:
				<b><sec:authentication property="principal" /></b>
			</a>
			<a href="<c:url value="/j_spring_security_logout" />" class="btn btn-default navbar-btn">
				<span class="glyphicon glyphicon-log-out"></span>
				Logout
			</a>
		</c:if>
		<c:if test="${pageContext['request'].userPrincipal == null}">
			<a href="<c:url value="/login" />" class="btn btn-default navbar-btn">Login</a>			
		</c:if>
    </nav>
</header>
<div class="container bs-docs-container">
		<div class="row">
		<div class="col-md-2">
		<div class="bs-sidebar hidden-print affix">
			<c:if test="${pageContext['request'].userPrincipal != null}">
			<ul class="nav bs-sidenav">							
				<li><a href="${pageContext.request.contextPath}/${userId }/projects">Projekty</a></li>
				<sec:authorize ifAllGranted="ADMIN">
				<li><a href="${pageContext.request.contextPath}/users">Uzivatele</a></li>
				</sec:authorize>				
			</ul>
			</c:if>
		</div>
		</div>
		<div class="col-md-9">
			<div class="panel panel-primary">
				<div class="panel-heading">
		    		Uvod
		    	</div>
		  		<div class="panel-body">		  			
		      		Uvodni stranka
		      	</div>
		  	</div>
		  	</div>
		  	</div>
	  	</div>
</body>
<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</html>