window.addEventListener("load", function(){
	let mainSection = document.querySelector(".main-section");
	let deleteBtn = mainSection.querySelector(".store-delete-button");
    let content = document.querySelector(".post-list");

    
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
		
		let url = `/admin/api/commu/posts/${selectBoxes}`;
		console.log(url);

    	fetch(url, {
	        method: 'DELETE'
	    })
	    .then(response => response.text())
	    .then(data => {
	        if (data === 'true') {
	            alert("게시글 삭제가 완료되었습니다.");
	            window.location.href = `/admin/commu/post`;
	        }
	    })
	    .catch(error => {
	        console.error("삭제 요청 실패:", error);
	    });
    	
    
	}
})