window.addEventListener("load", function(){
	let userSection = document.querySelector(".user-detail");
	let fileInput = userSection.querySelector("input[type=file]");
	let editBtn = document.querySelector(".store-edit-button");
	
	// 사진 미리보기
	fileInput.oninput = function(e){
		console.log("입력완료");
		const files = e.target.files;
	    let text = "";
	    for(const file of files){
	        text += `${file.name}: ${file.type || "알 수 없음."}\n`;
	    }
	
	    let f = files[0];
	    
	    var reader = new FileReader();
    
	    reader.addEventListener("load", ()=> {
			  let img = fileInput.nextElementSibling;
		      img.src = reader.result;
		    });
		    reader.readAsDataURL(f);
	    console.log(text);
	}


	editBtn.onclick = function(e) {
		console.log("수정버튼")
	    /*let el = e.target;*/
	
	    /*let isValidButtonClicked = el.classList.contains("store-edit-button")
						        || false;
	
	
	    if (!isValidButtonClicked) {
	        return;
	    }*/
	
	 /*   if (el.classList.contains("store-edit-button")) {*/
			
			e.preventDefault();
	        console.log("asdf");
	        
	        let id = document.querySelector("#id").value;
		 	let alias = document.getElementById("alias").value;
		 	let email = document.getElementById("email").value;
		 	let phone = document.getElementById("phone").value;
		 	let pwd = document.getElementById("pwd").value;
		 	let name = document.getElementById("name").value;
		 	let location = document.getElementById("locCateList").value;
	        let grade = document.querySelector("#grade").value;
	           
	        console.log(grade, pwd, name, location, id, grade);
	        
	        let file = fileInput.files[0];
	        
	        // 따로 보내는 것은 이미지만 폼데이터에 담고 나머지는 json으로 보내기
	        // 통으로 보내는 것은 폼데이터에 다 담아서 보내기 
	        let formData = new FormData();
	        formData.append("id", id);
	        formData.append("pwd", pwd);
	        formData.append("nAlias", alias);
	        formData.append("nEmail", email);
	        formData.append("nPhone", phone);
	        formData.append("name", name);
	        formData.append("locationId", location);
	        formData.append("gradeId", grade);
	        formData.append("img", file);
	        
	        let request = new XMLHttpRequest();
			
			request.open("PUT", "http://localhost:8000/admin/api/users");
			
			request.send(formData);
			console.log(request.onloadend);
			 window.location.href = `/admin/user/list`;

}
			

});
