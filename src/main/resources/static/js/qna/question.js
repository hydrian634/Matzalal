window.addEventListener("load", function() {
    let lock = document.querySelector(".qna-reg-section .open-close-block");

	lock.onclick = (e)=>{
		e.preventDefault();
		
		let el = e.target;
		
		console.log(el.value);
		        
/*		if(el.classList.contains("open"))
			console.log("공개 눌렸다.")
		else if(el.classList.contains("close"))
			console.log("비공개 눌렸다.")*/
			
	};
    
});