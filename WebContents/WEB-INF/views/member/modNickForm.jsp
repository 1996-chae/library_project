<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<script src="${contextPath}/resources/js/member/modNickForm.js"></script> 

<div class="container">
	<div class="jumbotron">
		<h1>닉네임 변경</h1>	
	</div>
	<div class="form-group">
		닉네임 : <input type="text" class="form-control" name="nickname">
		<button type="button" class="nick_ch">닉네임 중복 체크</button>
		<div id="nick_ch_yes">사용가능한 닉네임</div>
		<div id="nick_ch_no">사용할 수 없는 닉네임</div>
		<div>
		<b>조건 : 2글자 이하의 닉네임, 중복된 닉네임은 사용 불가능합니다.</b>
		</div>
	</div>
	
	<button class="btn btn-primary mod_nick_ok">완료</button>
	<button class="btn btn-primary Form_back">취소</button>
</div>

<%@ include file="../layout/footer.jsp" %>
