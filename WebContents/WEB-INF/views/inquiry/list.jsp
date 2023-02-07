<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<script src="${contextPath}/resources/js/inquiry/list.js"></script>

<div class="container my-3">
	<h1>문의함</h1>
	<a href="${contextPath}/inquiry/writeForm" class="btn btn-primary float-right">글쓰기</a>
	<a href="${contextPath}/inquiry/myList" class="btn btn-primary float-right">내가 글 확인</a>
	<form id="listForm">
	
		<table class="table">
			<tr>
				<th>답변상태</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>답변</th>
				<th>날짜</th>
			</tr>
			<c:forEach items="${board}" var="b">
			<tr>
				<td>
					<c:if test="${b.response == 'STANDBY'}">
					답변대기
					</c:if>
					<c:if test="${b.response == 'COMPLETION'}">
					답변완료
					</c:if>
				</td>
				<td>
					<a href="${b.ino}" class="title">
					${b.title} 
					 </a>
				</td>
				<td>${b.writer}</td>
				<td>${b.writeDate}</td>
			</tr>
			</c:forEach>
		</table>
	</form>
	
	<form id="pageForm" method="get">
		<ul class="pagination d-flex justify-content-center">
		  <c:if test="${p.prev}">
		  	<li class="page-item">
		  		<a class="page-link pagePrev" href="${p.startPage-1}">이전페이지</a>
		  	</li>
		  </c:if>
		  <c:forEach begin="${p.startPage}" end="${p.endPage}" var="pageBtn">
		  	<li class="page-item ${pageBtn==p.criteria.pageNum ? 'active' : ''}">
		  		<a class="page-link pageMove" href="${pageBtn}">${pageBtn}</a>
		  	</li>
		  </c:forEach>
		  <c:if test="${p.next}">
		  	<li class="page-item">
		  		<a class="page-link pageNext" href="${p.endPage+1}">다음</a>
		  	</li>
		  </c:if>
		</ul>
	</form>	
</div>
      


<%@ include file="../layout/footer.jsp" %>