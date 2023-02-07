console.log('common.js')

$(function(){

	// 파일 불러오기
	$('input[type="file"]').on('change',function(){
		if(this.files[0]){
			let reader = new FileReader(); // 파일읽기 객체 
			reader.onload = function(e){ // 파일을 읽으면 이벤트 발생 
				let value = e.target.result
				if(value.startsWith("data:image/")){ // 이미지파일인경우
					let imgTag = `<img src="${value}" alt="다운로드 이미지">`;
					$('.preview').html(imgTag);
				} else { // 이미지파일이 아닌경우
					alert('이미지 파일만 등록하세요');	
					$('input[name="fileName"]').val('');
					$('.preview').html('');
				}
			}
			reader.readAsDataURL(this.files[0]); // 파일 읽기 메소드 호출
		}
	}); // 파일 종료
	
	// 로그인 작업
	$('.user_login').on('click',function(e){
		e.preventDefault();
		$.ajax({
			type : 'post',
			url : `${contextPath}/member/login`,
			data : {
				id : $('input[name=userId]').val(),
				pwd : $('input[name=userPwd]').val()
			},
			success : function(result){
				location.reload(true);
			}, 
			error : function(){
				alert('아이디 혹은 비밀번호가 틀림');
				location.reload(true);
			}
		});	// ajax end	
	}); // 로그인 작업 종료
	
	// 로그 아웃
	$('.user_logout').on('click',function(){
		$.ajax({
			type : 'post', 
			url : `${contextPath}/member/logout`,
			success : function(result){
				location.reload(true);
			}, 
			error : function(){
				alert('로그아웃 실패');
				location.reload(true);
			}
		});	// ajax end	
	}); // 로그인하는것 종료
	
	// 나의 페이지
	$('.user_fage').on('click',function(e){
		e.preventDefault();
		let pageForm = $('.loginForm');
		pageForm
			.attr("method","post")
			.attr('action',`${contextPath}/member/infoFage`)
			.appendTo('body')
			.submit();
		
	}); // 내정보 가기
	
}); // 시작 종료