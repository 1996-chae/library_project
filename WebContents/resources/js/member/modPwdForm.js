console.log('member/modNickForm.js');

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
	$('.mod_nick_ok').on('click',function(e){
		e.preventDefault();
		
		$.ajax({
			type : 'post', 
			url : `${contextPath}/member/pwdCheck`,
			data : {
				id : auth.id,
				pwd : $('input[name=pwd]').val()
			},
			success : function(result){
				
				pwd_new = $('input[name=pwd_new]').val();
				pwd_check = $('input[name=pwd_check]').val();
				
				if(result && pwd_new==pwd_check) {
					
					let pageForm = $('<form/>');
					let idData = `<input type="hidden" name="id" value="${auth.id}">`;
					let pwdData = '<input type="hidden" name="pwd" value="'+pwd_new+'">';
					pageForm
						.attr("method","post")
						.attr('action',`${contextPath}/member/modPwd`)
						.append(pwdData).append(idData).appendTo('body')
						.submit();
					
				} else if(result==false){
					alert('비밀번호가 틀렸습니다.');
				} else if(pwd_new!=pwd_check){
					alert('비밀번호와 비밀번호 확인 이 다릅니다.');
				} else {
					alert('알수 없음')
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