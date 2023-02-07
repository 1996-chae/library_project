<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<script src="${contextPath}/resources/js/member/modForm.js"></script> 

<div class="container">
	<div class="jumbotron">
		<h1>내정보</h1>	
	</div>
	
	<div class="form-group">
		아이디 : ${auth.id}
		<input type="hidden" name="id" value="${auth.id}">
	</div>
	<div class="form-group">
		현재 비밀번호 : <input type="password" class="form-control" name="pwd">
	</div>
	<div class="form-group">
		이름 : <input type="text" class="form-control" name="name">
	</div>
	<div class="form-group">
		이메일 : <input type="text" class="form-control" name="email">
	</div>
	<button class="btn btn-primary mod_ok">수정하기</button>
	<button class="btn btn-primary Form_back">취소</button>
</div>

<%@ include file="../layout/footer.jsp" %>
