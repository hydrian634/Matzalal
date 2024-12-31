window.addEventListener("load", function(){
	let mainSection = document.querySelector(".main-section");
	let editBtn = mainSection.querySelector(".store-edit-button");
	let deleteBtn = mainSection.querySelector(".store-delete-button");
    let findBtn = document.querySelector(".query-filter .btn-find");
    let inputQuery = document.querySelector(".query-filter .input-query");
    let content = document.querySelector(".user-list");
    
   
	deleteBtn.onclick = function(e){
        e.preventDefault();
        console.log("delBtn");

		let checked = document.querySelectorAll(".checkBox:checked");
		var selectBoxes = Array.from(checked).map(checkbox => checkbox.value);

		if(selectBoxes.length == 0){
			alert("삭제할 회원을 선택하세요");
			return false;
		} else if(selectBoxes.length >= 1){
			console.log(selectBoxes);
			console.log(selectBoxes.length+"check");
			
		} 
		let url = `/admin/api/users/${selectBoxes}`;
		console.log(url);
    	fetch(url, {
	        method: 'DELETE'
	    })
	    .then(response => response.text())
	    .then(data => {
	        if (data === 'true') {
	            alert("회원 삭제가 완료되었습니다.");
	            window.location.href = `/admin/user/list`;
	        }
	    })
	    .catch(error => {
	        console.error("삭제 요청 실패:", error);
	    });
    	
    
	}
        
	editBtn.onclick = function(e){
		e.preventDefault();
		
		let checked = document.querySelectorAll(".checkBox:checked");
		var selectBoxes = Array.from(checked).map(checkbox => checkbox.value);
			
			console.log(selectBoxes);
						
			if(selectBoxes.length == 0){
				alert("수정할 회원을 선택하세요");
				return false;
			} else if(selectBoxes.length == 1){
				console.log(selectBoxes);
				console.log(selectBoxes.length+"check");
			} else{
				alert("수정할 회원은 한 명만 선택해주세요.")
				return false;
			}
			
			let id = selectBoxes[0];
			url = ``;
			console.log(id);
			
	    	fetch(url, {
		        method: 'GET'
		    })
		    .then(response => response.text())
		    .then(data => {
				console.log(data);
	           /* alert("회원 수정페이지로 이동중");*/
	            window.location.href = `/admin/user/edit?id=${id}`;
		      
	        })
		    
		}
		
	console.log(content);	
		
	function bind(json){
		content.innerHTML="";
		
		for(let u of json){
			
			let template = `
				<section class="user-block md:user-block">
	                <h1 class="user-alias-block">${u.alias}</h1>
					<div class="checkbox-block">
	                	<input type="checkbox" class="checkBox" name="checkBox" value="${u.id}">
	                </div>
	            	<div class="user-image-block">
	                    <img src="/image/user/${u.profileImg}" style="max-width: 80%; max-height: 80%; object-fit: fill;" class="">
	           		</div>
	                
	                <div class="user-email-block">
	                    <a class="">${u.email}</a>
	                </div>
	
					<div class="user-grade-block">
						<img src="/css/image/icon/badge${u.gradeId}.svg">
		                <div>
		                    <a class="">${u.gradeId}</a>
		                </div>
	                </div>
	
	                <div class="user-reg_date-block">
	                    <a class="">${u.date}</a>
	                </div>
	               				
            	</section>
				
			`;
			content.insertAdjacentHTML("beforeend", template);
		}
		/*					<div class="pagination">
	                	<a class="prevPage">&laquo;</a>
	                	<a >${u.page}</a>
	                	<a class="nextPage">&raquo;</a>
	            	</div>*/
		
	}
        
   findBtn.onclick = async function(e){
	   e.preventDefault();
	   let query = inputQuery.value;
	   console.log(query);
	   let page = 1;
	   
	   let response = await fetch(`/admin/api/users?p=${page}&q=${query}`);
	   let json = await response.json();
	   
	   bind(json);
	   console.log(json);
   }     
/*      
	prevPageBtn.addEventListener("click", () => {
	    if (currentPage > 1) {
	        updatePage(currentPage - 1);
	    }
	});
	
	nextPageBtn.addEventListener("click", () => {
	    // 다음 페이지로 이동할 수 있는 조건을 설정
	    updatePage(currentPage + 1);
	});*/
    

})