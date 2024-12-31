window.addEventListener("load", function(){
	let mainSection = document.querySelector(".main-section");
	let editBtn = mainSection.querySelector(".store-edit-button");
	let deleteBtn = mainSection.querySelector(".store-delete-button");
    let content = document.querySelector(".notice-list");


	editBtn.onclick = function(e){
		e.preventDefault();
		
		let checked = document.querySelectorAll(".checkBox:checked");
		var selectBoxes = Array.from(checked).map(checkbox => checkbox.value);
			
			console.log(selectBoxes);
						
			if(selectBoxes.length == 0){
				alert("수정할 공지사항을 선택하세요");
				return false;
			} else if(selectBoxes.length == 1){
				console.log(selectBoxes);
				console.log(selectBoxes.length+"check");
			} else{
				alert("수정할 공지사항은 하나만 선택해주세요.")
				return false;
			}
			
			let id = selectBoxes[0];
			url = ``;
			
	    	fetch(url, {
		        method: 'GET'
		    })
		    .then(response => response.text())
		    .then(data => {
				console.log(data);
	            window.location.href = `/admin/notice/edit?noticeId=${id}`;
		      
	        })
	       
		    
		}
    
	deleteBtn.onclick = function(e){
        e.preventDefault();
        console.log("delBtn");

		let checked = document.querySelectorAll(".checkBox:checked");
		var selectBoxes = Array.from(checked).map(checkbox => checkbox.value);

		if(selectBoxes.length == 0){
			alert("삭제할 게시글을 선택하세요");
			return false;
		} else if(selectBoxes.length >= 1){
			console.log(selectBoxes);
			console.log(selectBoxes.length+"check");
			
		} 
		
		let url = `/admin/api/notices/${selectBoxes}`;
		console.log(url);

    	fetch(url, {
	        method: 'DELETE'
	    })
	    .then(response => response.text())
	    .then(data => {
	        if (data === 'true') {
	            alert("게시글 삭제가 완료되었습니다.");
	            window.location.href = `/admin/notice/list`;
	        }
	    })
	    .catch(error => {
	        console.error("삭제 요청 실패:", error);
	    });
    	
    
	}
})