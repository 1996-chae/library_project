<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<script src="${contextPath}/resources/js/board/writeForm.js"></script>

<div class="container">
	<div class="jumbotron">
		<h1>글작성</h1>	
	</div>
	
	<form action="${contextPath}/board/write" method="post" enctype="multipart/form-data">
		
		<div class="form-group">
			게시판 분류 : 
			<select name="writeType" class="form-control" >
				<option value="none" disabled>=== 선택 ===</option>
			<c:forEach items="${typeAll}" var="t">
				<option value="${t}" ${"공지"==t ? 'hidden' : ''}>${t}</option>
			</c:forEach>
			</select>
		</div>
		
		<div class="form-group">
			제목 : <input type="text" class="form-control" name="title">
		</div>
		<div class="form-group">
			내용 : <textarea rows="10" class="form-control" name="content"></textarea>
		</div>
		<div class="form-group">
			첨부파일 : <input type="file" class="form-control" name="fileName">
		</div>
		<button class="btn btn-primary">글쓰기</button>
	</form>
</div>




<%@ include file="../layout/footer.jsp" %>