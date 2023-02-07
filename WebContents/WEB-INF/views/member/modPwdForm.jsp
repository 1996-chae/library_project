<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<script src="${contextPath}/resources/js/member/modPwdForm.js"></script> 

<div class="container">
	<div class="jumbotron">
		<h1>닉네임 변경</h1>	
	</div>
	<div class="form-group">
		현재 비밀번호 : <input type="text" class="form-control" name="pwd">
		<div id="password_check">
			<b>비밀번호가 틀렸습니다.</b>
		</div>
	</div><br>
	<div>
		<b>영어와 숫자를 썩어 4자리 이상의 비밀번호를 완성해주세요</b><br>
		변경할 비밀번호 : <input type="text" class="form-control" name="pwd_new">
		<div id="id_ch_no">사용불가능한 비밀번호 입니다.</div>
	</div><br>
	<div>
		비밀번호 확인 : <input type="text" class="form-control" name="pwd_check">
		<div id="id_ch_yes">비밀번호 확인이 틀렸습니다.</div>
	</div>
	<input type="text">
	<button class="btn btn-primary mod_nick_ok">완료</button>
	<button class="btn btn-primary Form_back">취소</button>
</div>

<%@ include file="../layout/footer.jsp" %>
