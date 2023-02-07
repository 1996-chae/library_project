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
	
	$('#nick_ch_yes').hide();
	$('#nick_ch_no').hide();
	let join_nick_ok = false;
	let nick = $('input[name=nickname]').val();
	let newNick;
	
	// 닉네임 중복 확인
	$('.nick_ch').on('click',function(){
		newNick = $('input[name=nickname]').val();
		$.ajax({
			type : 'post', 
			url : `${contextPath}/member/nicknameChek`,
			data : {
				nickname : newNick
			},
			success : function(result){
				$('#nick_ch_yes').show();
				$('#nick_ch_no').hide();
				join_nick_ok = true;
			}, 
			error : function(){
				$('#nick_ch_yes').hide();
				$('#nick_ch_no').show();
				join_nick_ok = false;
				
			}
		});	// ajax end
	}); // 로그인하는것 종료
	
	// 수정완료
	$('.mod_nick_ok').on('click',function(){
			
		if(newNick!=$('input[name=nickname]').val()){
			alert('닉네임 중복 체크를 확인하세요');
			$('#nick_ch_yes').hide();
			$('#nick_ch_no').show();
			join_nick_ok = false;
			
		} else if(join_nick_ok == false) { 
			alert('닉네임 중복 체크를 확인하세요');
		} else {
			let pageForm = $('<form/>');
			let newNickData = '<input type="hidden" name="nickname" value="'+newNick+'">';
			let idData = `<input type="hidden" name="id" value="${auth.id}">`;
			pageForm
				.attr("method","post")
				.attr('action',`${contextPath}/member/modNick`)
				.append(newNickData).append(idData).appendTo('body')
				.submit();
		}
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