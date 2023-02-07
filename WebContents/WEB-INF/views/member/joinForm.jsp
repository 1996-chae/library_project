<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<script src="${contextPath}/resources/js/member/joinForm.js"></script> 

<div class="container">
	<div class="jumbotron">
		<h1>회원가입</h1>	
	</div>
	
	<div class="form-group">
		아이디 : <input type="text" class="form-control" name="id">
		<button type="button" class="id_ch">아이디 중복 체크</button>
		<div id="id_ch_yes">사용가능한 아이디</div>
		<div id="id_ch_no">사용불가능한 아이디</div>
	</div>
	<div class="form-group">
		비밀번호 : <input type="password" class="form-control" name="pwd">
	</div>
	<div class="form-group">
		닉네임 : <input type="text" class="form-control" name="nickname">
		<button type="button" class="nick_ch">닉네임 중복 체크</button>
		<div id="nick_ch_yes">사용가능한 닉네임</div>
		<div id="nick_ch_no">사용할 수 없는 닉네임</div>
	</div>
	<div class="form-group">
		이름 : <input type="text" class="form-control" name="name">
	</div>
	<div class="form-group">
		이메일 : <input type="text" class="form-control" name="email">
	</div>
	<button class="btn btn-primary join_ok">회원가입</button>
</div>

<%@ include file="../layout/footer.jsp" %>
