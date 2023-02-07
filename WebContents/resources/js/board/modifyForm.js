$(function(){
	// 글 상세로 돌아가기
	let listForm = $('#listForm');
	$('.backDetail').on('click',function(e){
		e.preventDefault();
		$('[name="bno"]').remove();
		let bnoData = "<input type='hidden' name='bno' value='"+$(this).attr('href')+"'/>";
		let pageDate;
		if(pageNum) {pageDate = `<input type='hidden' name='pageNum' value='${pageNum}'/>`;};
		let typeDate;
		if(writeType) {typeDate = `<input type='hidden' name='writeType' value='${writeType}'/>`;};
		listForm.append(bnoData).append(pageDate).append(typeDate)
				.attr("action",`${contextPath}/board/detail`)
				.submit();
	});
	
});