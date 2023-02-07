<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<script src="${contextPath}/resources/js/board/modifyForm.js"></script>

<div class="container">
	<div class="jumbotron">
		<h1>글수정</h1>	
	</div>
	
	<form action="${contextPath}/board/modify" method="post" enctype="multipart/form-data">
		<input type="hidden" name="bno" value="${board.bno}">
		<div class="form-group">
			게시판 분류 : 
			<select name="writeType" class="form-control" >
				<option value="none" disabled>=== 선택 ===</option>
			<c:forEach items="${typeAll}" var="t">
				<option value="${t}" ${board.writeType==t ? 'selected' : ''} ${"공지"==t ? 'hidden' : ''}>${t}</option>
			</c:forEach>
			</select>
		</div>
		
		<div class="form-group">
			제목 : <input type="text" class="form-control" name="title" value="${board.title}">
		</div>
		<div class="form-group">
			내용 : <textarea rows="10" class="form-control" name="content">${board.content}</textarea>
		</div>
		<div class="form-group">
			첨부파일 :
			<input type="file" class="form-control" name="fileName">
			<input type="hidden" name="originFileName" value="${board.fileName}">
			<div class="preview">
				<img class="originImg" src="${contextPath}/fileDownload?no=${board.bno}&fileName=${board.fileName}&path=board">
			</div>
		</div>
		<button class="btn btn-primary">수정완료</button>
		<button type="button" class="btn btn-secondary backDetail">취소하기</a>
	</form>
</div>




<%@ include file="../layout/footer.jsp" %>