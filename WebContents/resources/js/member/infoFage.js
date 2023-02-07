console.log('member/myFage');
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
	
});