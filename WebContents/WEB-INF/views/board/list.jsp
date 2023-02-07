<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<script src="${contextPath}/resources/js/board/list.js"></script>

<input type="hidden" name="writeType" value="${param.writeType}"/>

<div class="container my-3">
	<h1>게시물 목록</h1>
	<a href="${contextPath}/board/writeForm" class="btn btn-primary float-right">글쓰기</a>
	<ul class="pagination d-flex justify-content-center">
		<li class="page-item ${t.writeType==param.writeType ? 'active' : ''}">
			<a class="page-link writeType" href="${contextPath }/board/list/">전체</a>
		</li>
		<c:forEach items="${typeAll}" var="t">
		<li class="page-item ${t==param.writeType ? 'active' : ''}">
			<a class="page-link writeType" href="${contextPath }/board/list/?writeType=${t}">${t}</a>
		 </li>
		 </c:forEach>
	</ul>
	
	
	
	<form id="listForm">
	
		<table class="table">
			<tr>
				<th>분류</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>날짜</th>
				<th>조회</th>
				<th>댓글</th>
			</tr>
			<c:forEach items="${board}" var="b">
			<tr>
				<td>
					${b.writeType }
				</td>
				<td>
					<a href="${b.bno}" class="title">
					${b.title } 
					 </a>
				</td>
				<td>${b.writerNickname }</td>
				<td>${b.writeDate }</td>
				<td>${b.hitsCount }</td>
				<td>${b.replyCount }</td>
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
	<a href="${contextPath}/board/writeForm" class="btn btn-primary float-right">글쓰기</a>
	
</div>
      


<%@ include file="../layout/footer.jsp" %>