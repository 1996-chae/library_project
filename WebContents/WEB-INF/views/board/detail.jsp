<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<script src="${contextPath}/resources/js/board/detail.js"></script>
<script src="${contextPath}/resources/js/reply/replyService.js"></script>

<div class="container">
	<div class="jumbotron">
		<h1>글상세</h1>
	</div>
	<form id="viewForm" enctype="multipart/form-data">
	<table class="table">
		<tr>
			<th>제목</th>
			<td colspan="5">
				${board.title}
			</td>
		</tr>
		
		<tr>
			<th>작성자</th>
			<td>
				${board.writerNickname} 
				(${board.writerId})
			</td>
			<th>최신수정일</th>
			<td>${board.modifyDate}</td>
			<th>최초게시일</th>
			<td>${board.writeDate}</td>
		</tr>
		<tr>
			<th>글번호</th>
			<td>
				${board.bno}
				<input type="hidden" name="bno" value="${board.bno}">
			</td>
			<th>조회수</th>
			<td>${board.hitsCount}</td>
			<th>댓글수</th>
			<td>${board.replyCount}</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td colspan="5">
				<div class="my-2">
					<a href="${contextPath}/fileDownload?no=${board.bno}&fileName=${board.fileName}&path=board">${board.fileName}</a>
				</div>
			</td>
		</tr>	
		<tr>
			<th>내용</th>
			<td colspan="5">
				<textarea rows="10" name="content" class="form-control" readonly="readonly" >${board.content}</textarea>
			</td>
		</tr>
		<tr>
			<th>이미지</th>
			<td colspan="5">
				<c:if test="${not empty board.fileName}">
					<input type="hidden" name="originFileName" value="${board.fileName}">
					<div class="preview">
						<img class="originImg" src="${contextPath}/fileDownload?no=${board.bno}&fileName=${board.fileName}&path=board">
					</div>
				</c:if>
				<c:if test="${empty board.fileName}">
					<div class="preview">
						<p>등록된 이미지가 없습니다.</p>
					</div>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="6" class="text-center">
				<c:if test="${auth.id eq board.writerId or auth.grade eq 'ROLE_ADMIN'}">	
				<button type="button" class="btn btn-info toModForm">수정하기</button>
				<button type="button" class="btn btn-secondary remove">삭제</button>
				</c:if>
				<button type="button" class="btn btn-success toList">목록</button>
			</td>
		</tr>
	</table>
	</form>
	
	<form class="replyForm">
		<table class="table">
			<tr>
				<td>
					<textarea rows="6" class="form-control reply_content"></textarea>
				</td>
			</tr>
			<tr class="text-right">
				<td colspan="2"><button class="btn btn-primary reply_write">댓글등록</button></td>
			</tr>
		</table>
	</form>
	<div class="replyList card">
		<ul class="card-body">
			댓글 작업용 칸
		</ul>
	</div>
</div>


<%@ include file="../layout/footer.jsp" %>