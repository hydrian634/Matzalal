window.addEventListener("load", function(){
	let mainSection = document.querySelector(".main-section");
	let createBtn = mainSection.querySelector(".store-create-button");
	let editBtn = mainSection.querySelector(".store-edit-button");
	let deleteBtn = mainSection.querySelector(".store-delete-button");
    let content = document.querySelector(".qna-list");
    

	createBtn.onclick = function(e){
		e.preventDefault();
		
		let checked = document.querySelectorAll(".checkBox:checked");
		var selectBoxes = Array.from(checked).map(checkbox => checkbox.value);
		
			console.log(selectBoxes);
			
			let processBlock = mainSection.querySelector(".qna-status-block span");
			
			/*if(isProcess.contatins("qna-completed")){
				alert("이미 등록된 답변이 있습니다.");
				return;
			}*/
						
			if(selectBoxes.length == 0){
				alert("처리할 질문글을 선택하세요");
				return false;
			} else if(selectBoxes.length == 1 ){
				console.log(selectBoxes);
				console.log(selectBoxes.length+"check");
			} else{
				alert("처리할 질문글은 하나만 선택해주세요.")
				return false;
			}
			
			let id = selectBoxes[0];
			
			/*url = ``;*/
			
			url = `/admin/api/qnas/${id}`;

		    fetch(url, {
		        method: 'GET'
		    })
		    .then(response => response.text())
		    .then(status => {
		        console.log("Question Status:", status);
		
		        if (status >= 1) {
		            alert("이미 등록된 답변이 있습니다.");
		            return;
		        } else if (status == 0) {
		            alert("등록페이지로 전환됩니다");
		            window.location.href = `/admin/QnA/answer?questionId=${id}`;
		        } else {
		            alert("처리 상태를 가져오지 못했습니다.");
		        }
  		  });
			
		}


	editBtn.onclick = function(e){
		e.preventDefault();
		
		let checked = document.querySelectorAll(".checkBox:checked");
		var selectBoxes = Array.from(checked).map(checkbox => checkbox.value);
			
			console.log(selectBoxes);
						
			if(selectBoxes.length == 0){
				alert("수정할 답변글을 선택하세요");
				return false;
			} else if(selectBoxes.length == 1){
				console.log(selectBoxes);
				console.log(selectBoxes.length+"check");
			} else{
				alert("수정할 답변글은 하나만 선택해주세요.")
				return false;
			}
			
			let id = selectBoxes[0];
			
			url = `/admin/api/qnas/${id}`;

		    fetch(url, {
		        method: 'GET'
		    })
		    .then(response => response.text())
		    .then(status => {
		        console.log("Question Status:", status);
		
		        if (status == 0) {
		            alert("수정할 답변이 없습니다.");
		            return;
		        } else if (status == 1) {
		            alert("수정페이지로 전환됩니다");
		            window.location.href = `/admin/QnA/edit?questionId=${id}`;
		        } else {
		            alert("처리 상태를 가져오지 못했습니다.");
		        }
  		  });  	    
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
		
		let url = `/admin/api/qnas/${selectBoxes}`;
		console.log(url);

    	fetch(url, {
	        method: 'DELETE'
	    })
	    .then(response => response.text())
	    .then(data => {
	        if (data === 'true') {
	            alert("게시글 삭제가 완료되었습니다.");
	            window.location.href = `/admin/QnA/list`;
	        }
	    })
	    .catch(error => {
	        console.error("삭제 요청 실패:", error);
	    });
    	
    
	}
})