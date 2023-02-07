<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<script src="${contextPath}/resources/js/book/main.js"></script>

<div class="container mt-3">
	<h2>검색기</h2>
	이름<input type="checkbox" class="bno_ckbox" value="name" placeholder="이름">      
	저자<input type="checkbox" class="bno_ckbox" value="writer" placeholder="저자">
	출판사<input type="checkbox" class="bno_ckbox" value="shop" placeholder="출판사">     
	<p>당신이 원하는 책을 검색 해 주세요</p>
	<input class="form-control" id="myInput" type="text" placeholder="원하는 검색어를 입려하세요.."><br>
	<table class="table table-bordered bookList">
		<thead>
			<tr>
				<th>이름</th>
		    	<th>저자</th>
		    	<th>출판사</th>
		    	<th>위치<th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>



<%@ include file="../layout/footer.jsp" %>