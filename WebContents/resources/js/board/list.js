console.log('list.js');

$(function(){
	
	// 글 상세 들어가기
	let listForm = $('#listForm');
	$('.title').on('click',function(e){
		e.preventDefault();
		$('[name="bno"]').remove();
		
		let bnoData = "<input type='hidden' name='bno' value='"+$(this).attr('href')+"'/>";
		listForm.append(bnoData);
		if(pageNum) {
			let pageDate = `<input type='hidden' name='pageNum' value='${pageNum}'/>`;
			listForm.append(pageDate);
			};
		if(writeType) {
			let typeDate = `<input type='hidden' name='writeType' value='${writeType}'/>`;
			listForm.append(typeDate);
			};
		listForm.attr("action",`${contextPath}/board/detail`)
				.submit();
	});
	
	// 글 종류와 페이지 글 페이지를 이동하는 작업!!!
	$('.pageMove').on('click',function(e){
		e.preventDefault();
		pageNum = $(this).attr('href');
		toPageNum(pageNum);
	});
	$('.pagePrev').on('click',function(e){
		e.preventDefault();
		pageNum = $(this).attr('href');
		toPageNum(pageNum);
	});
	$('.pageNext').on('click',function(e){
		e.preventDefault();
		pageNum = $(this).attr('href');
		toPageNum(pageNum);
	});
	
}); // 종료

// 페이지 이동 (타입이랑 같이)
function toPageNum(pageNum){
	let pageForm = $('#pageForm');
	let pageData = "<input type='hidden' name='pageNum' value='"+pageNum+"'/>";
	if(writeType){
		let TypeData = "<input type='hidden' name='writeType' value='"+writeType+"'/>";
		pageForm.append(pageData).append(TypeData)
	} else {
		pageForm.append(pageData)
	}
	pageForm.attr("action",`${contextPath}/board/list`)
			.submit();
	
}