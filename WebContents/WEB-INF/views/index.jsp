<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp" %>

<div class="container">
	<div class="row">
		<div class="col-sm-6 text-center">
			
			<h3>책 검색</h3>
			<p>
				<input type="text" name="대출검색기관임">
				<button>검색</button>
			</p>
			
		</div>
		<div class="col-sm-6 text-center">
			<a class="nav-link" href="${contextPath}/board">
				<h1>
					홈페이지 이용하기
				</h1>
			</a>
		</div>
	</div>
</div>

<%@ include file="layout/footer.jsp" %>