$(function(){
	
	if(auth.id.length>0){ // 로그인 중일때는 회원가입페이지에서 나가짐
		let pageForm = $('<form/>');
		pageForm
			.attr("method","post")
			.attr('action',`${contextPath}/board`)
			.appendTo('body')
			.submit();
	}
	
	$('#id_ch_yes').hide();
	$('#id_ch_no').hide();
	$('#nick_ch_yes').hide();
	$('#nick_ch_no').hide();
	let join_id_ok = false;
	let join_nick_ok = false;
	let id;
	let nickname;
	// 아이디 중복 확인
	$('.id_ch').on('click',function(){
		id = $('input[name=id]').val();
		$.ajax({
			type : 'post', 
			url : `${contextPath}/member/idChek`,
			data : {
				id : id
			},
			success : function(result){
				$('#id_ch_yes').show();
				$('#id_ch_no').hide();
				join_id_ok = true;
				
			}, 
			error : function(){
				$('#id_ch_yes').hide();
				$('#id_ch_no').show();
				join_id_ok = false;
			}
		});	// ajax end	
	}); // 로그인하는것 종료
	
	// 닉네임 중복 확인
	$('.nick_ch').on('click',function(){
		nickname = $('input[name=nickname]').val();
		$.ajax({
			type : 'post', 
			url : `${contextPath}/member/nicknameChek`,
			data : {
				nickname : nickname
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
	
	// 회원가입 완료
	$('.join_ok').on('click',function(){
		
		let pwd = $('input[name=pwd]').val()
		let name = $('input[name=name]').val()
		let email = $('input[name=email]').val()
		
		if(id==null || nickname==null || pwd == null || name == null || email == null){
			alert('빈칸 없이 작성해주세요');
		}
		else if(id!=$('input[name=id]').val() && nickname!=$('input[name=nickname]').val()){
			alert('아이디 중복 체크를 확인하세요');
			$('#id_ch_yes').hide();
			$('#id_ch_no').show();
			join_id_ok = false;
			$('#nick_ch_yes').hide();
			$('#nick_ch_no').show();
			join_nick_ok = false;
			
		} else if(id!=$('input[name=id]').val()){
			alert('아이디 중복 체크를 확인하세요');
			$('#id_ch_yes').hide();
			$('#id_ch_no').show();
			join_id_ok = false;
			
		} else if(nickname!=$('input[name=nickname]').val()){
			alert('닉네임 중복 체크를 확인하세요');
			$('#nick_ch_yes').hide();
			$('#nick_ch_no').show();
			join_nick_ok = false;
			
		} else if(join_id_ok == false){
			alert('아이디 중복 체크를 확인하세요');
		} else if(join_nick_ok == false) {
			alert('닉네임 중복 체크를 확인하세요');
			
		} else {
			$.ajax({
				type : 'get', 
				url : `${contextPath}/member/join`,
				data : {
					id : $('input[name=id]').val(),
					pwd : $('input[name=pwd]').val(),
					nickname : $('input[name=nickname]').val(),
					name : $('input[name=name]').val(),
					email : $('input[name=email]').val()
				},
				success : function(result){
					alert(result);
					window.location.href = `${contextPath}/board/list`;
				}, 
				error : function(){
					alert('회원가입 실패 : 잘못된 입력값을 넣었습니다.')
				}
			});	// ajax end	
		}
	});// join_ok 종료
	
	
	
});