console.log('book/main.js');

$(function(){
	let bnoSet = new Set(); 
	var value;
	
	$(document).ready(function(){
		
		$("#myInput").on("keyup", function() {
			value = $(this).val().toLowerCase();
			console.log(value);
			if(bnoSet.size <= 0){
				return;
			}
			findBook(value, bnoSet)
			
		});
	});
	
	$('.bno_ckbox').on('change',function(){
		let bno = $(this).val();
		let isChecked = $(this).prop('checked')
		if(isChecked){
			console.log(bno + "번 체크");
			bnoSet.add(bno);
			findBook(value, bnoSet);
		} else {
			console.log(bno + "번 체크 해제");
			bnoSet.delete(bno);
			findBook(value, bnoSet);
		}
		console.log(bnoSet);
	});
	
	$('.bookTo').on('click',function(){
		findBook(value, bnoSet);
	})
	
});

// 자동 검색
function findBook(value, bnoSet){
	let bnoListStr = Array.from(bnoSet).join();
	$.ajax({
		type : 'get',
		url : `${contextPath}/book/find`,
		data : {
			search : value,
			selection : bnoListStr
		},
		success : function(bookList){
			bookListRender(bookList);
		}, 
		error : function(){
			console.log("왜 실패 할까?")
		}
	});
}

// 검색 완료 리스트 표현
function bookListRender(bookList){
	if(bookList==null)return;
	let output = "";
	for(let r of bookList){
		output += `
			<tr>
				<td>${r.name}</td>
				<td>${r.writer}</td>
				<td>${r.shop}</td>
				<td>${r.code}</td>
			</tr>`
	}
	$('.bookList tbody').html(output);
}