window.addEventListener("load", function(){
	let mainSection = document.querySelector(".main-section");
	let editBtn = mainSection.querySelector(".store-edit-button");
	//let checkBox = document.querySelector(".checkbox-block input");
	let checkBoxes = document.querySelectorAll("input[name='checkBox']");
	let editBoxes = document.querySelectorAll("a[name='editBox']");
	
	
	editBtn.onclick = function(){
		
		console.log(checkBoxes);
		
		var checkNum = 0;
		var checkBoxArray = [];
		
		checkBoxes.forEach(function(checkBox){
			if(checkBox.checked){
				checkNum++;
			}
		});
		if(checkNum == 0){
			alert("수정할 회원을 선택하세요");
			return false;
		} else if(checkNum == 1){
			console.log(editBoxes.checked.value);
			console.log("1 check");
			
		} else{
			alert("한 건만 선택하세요");
			return false;
		}	
	}
	
	
	
	/*function getCheckboxValue(event)  {
	  let result = '';
	  if(event.target.checked)  {
	    result = event.target.value;
	  }else {
	    result = '';
	  }
	  
	  alert(result);
	}*/
	
	
	
	
	
});
