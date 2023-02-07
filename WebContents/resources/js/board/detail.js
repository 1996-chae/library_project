console.log('detail.js');

$(function(){
	
	bno = $('[name="bno"]').val();
	let bnoData = "<input type='hidden' name='bno' value='"+bno+"'/>";
	let pageForm = $('<form/>');
	
	// 수정하기
	$('.toModForm').on('click',function(e){
		e.preventDefault();
		pageForm
			.attr("method","post")
			.attr('action',`${contextPath}/board/modifyForm`)
			.append(bnoData)
			.appendTo('body')
			.submit();
	});
	
	// 삭제하기
	$('.remove').on('click',function(e){
		e.preventDefault();
		alert(bno);
		pageForm
			.attr("method","post")
			.attr('action',`${contextPath}/board/delete`)
			.append(bnoData)
			.appendTo('body')
			.submit();
	});
	
	
	// 목록으로 돌아가기
	$('.toList').on('click',function(e){
		e.preventDefault();
		
		if(writeType){
			let TypeData = "<input type='hidden' name='writeType' value='"+writeType+"'/>";
			pageForm.append(TypeData)
			}
		if(pageNum) {
			let pageData = "<input type='hidden' name='pageNum' value='"+pageNum+"'/>";
			pageForm.append(pageData)
		}
		pageForm.attr("action",`${contextPath}/board/list`)
			.attr("method","get")
			.appendTo('body')
			.submit();
	});
});

// 댓글 작업
$(function(){
	let bno = $('input[name="bno"]').val();
	// 댓글 목록 
	replyService.list(bno);
	
	// 댓글 쓰기
	$('.reply_write').on('click',function(){
		let reply = $('.reply_content').val();
		let replyVO = {
			bno : bno, 
			reply : reply, 
			id : auth.id,
			nickname : auth.nickname
		}
		replyService.write(replyVO);
		replyService.list(bno);
	});
	
	// 수정 버튼 이벤트
	$('.replyList').on('click','.reply_modBtn',function(){
		let rno = $(this).closest('div').data('rno');
		replyService.modify(rno);
	});
	
	// 삭제 버튼 이벤트
	$('.replyList').on('click','.reply_delBtn',function(){
		let rno = $(this).closest('div').data('rno');
		replyService.remove(rno,bno);
		replyService.list(bno);
	});
})

