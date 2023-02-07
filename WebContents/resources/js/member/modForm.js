console.log('member/modForm.js');

$(function(){
	
	if(`${auth.id}` == null || `${auth.id}` == '' ){
		alert('로그인 후 이용해 주세요');
		let pageForm = $('<form/>');
		pageForm
			.attr("method","post")
			.attr('action',`${contextPath}/board`)
			.appendTo('body')
			.submit();
	}
	

	
	// 수정완료
	$('.mod_ok').on('click',function(e){
		e.preventDefault();
		
		$.ajax({
			type : 'post', 
			url : `${contextPath}/member/pwdCheck`,
			data : {
				id : auth.id,
				pwd : $('input[name=pwd]').val()
			},
			success : function(result){
				if(result) {
					let name = $('input[name=name]').val();
					let email = $('input[name=email]').val();
					
					let pageForm = $('<form/>');
					let nameData = '<input type="hidden" name="name" value="'+name+'">';
					let emailData = '<input type="hidden" name="email" value="'+email+'">';
					let idData = `<input type="hidden" name="id" value="${auth.id}">`;
					
					pageForm
						.attr("method","post")
						.attr('action',`${contextPath}/member/mod`)
						.append(nameData).append(emailData).append(idData).appendTo('body')
						.submit();
					
				} else {
					alert('비밀번호가 틀렸습니다.');
				}
			}, 
			error : function(){
				alert('비밀번호 확인 작업 에러');
			}
		});	// ajax end	
	});// join_ok 종료
	
	// 취소
	$('.Form_back').on('click',function(e){
		e.preventDefault();
		let pageForm = $('.loginForm');
		let idData = `<input type="hidden" name="id" value="${auth.id}">`;
		pageForm
			.attr("method","post")
			.append(idData)
			.attr('action',`${contextPath}/member/infoFage`)
			.appendTo('body')
			.submit();
	});
	
	
});