<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<script src="${contextPath}/resources/js/member/infoFage.js"></script> 

<div class="container">
	<div class="jumbotron">
		<h1>내정보</h1>	
	</div>
	
	<div class="form-group">
		아이디 : ${m.id}
	</div>
	<div class="form-group">
		닉네임 : ${m.nickname}
	</div>
	<div class="form-group">
		이름 : ${m.name }
	</div>
	<div class="form-group">
		이메일 : ${m.email }
	</div>
	<div class="form-group">
		가입일 : ${m.joinDate }
	</div>
	<a href="${contextPath}/member/modForm" class="btn btn-primary">내정보 수정하기</a>
	<a href="${contextPath}/member/modNickForm" class="btn btn-primary">닉네임 변경 수정하기</a>
	<a href="${contextPath}/member/modPwdForm" class="btn btn-primary">비밀번호 변경 수정하기</a>
</div>


<%@ include file="../layout/footer.jsp" %>
