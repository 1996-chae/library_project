<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<c:set var="auth" value="${sessionScope.auth}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
	const contextPath = "${contextPath}"
	let auth = {
		id : "${auth.id}",
		nickname : "${auth.nickname}",
		grade : "${auth.grade}"
	};
	let	pageNum = "${param.pageNum}";
	let	writeType = "${param.writeType}";
	
</script>

<script src="${contextPath}/resources/js/common.js"></script>

</head>
<body>

<nav class="container navbar navbar-expand-sm bg-dark navbar-dark text-center">
	<div class="container">
	  <ul class="nav nav-tabs">
	    <li class="nav-item">
	      <a class="nav-link" href="${contextPath}/board/list">게시판</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${contextPath}/inquiry">문의함</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${contextPath}/book/">책 찾기</a>
	  </ul>
	</div>

	<form class="loginForm">
		<c:if test="${empty auth }">
		<form>
			<div class="span-4 float-left" >
				<input type="text" class="form-control-sm" name="userId">
				<input type="password" class="form-control-sm" name="userPwd"><br>
				<button class="btn btn-success user_login">로그인</button>
			</div>
		</form>
		<div class="span-4  float-left login_off" >
			<a class="btn btn-info" href="${contextPath}/member/joinForm">회원가입</a>
			<a class="btn btn-info" href="${contextPath}/member/modForm">id,패스워드 찾기</a>
		</div>
		</c:if>
		<c:if test="${not empty auth }">
		<div class="span-4  float-right">
			<h3 class="navbar-brand">${auth.nickname}</h3>
			<input type="hidden" name="id" value="${auth.id}">
		  	<button type="button" class="user_fage">내정보</button>
		  	<button type="button" class="user_logout">로그아웃</button>
		</div>
		</c:if>
	</form>
</nav>
