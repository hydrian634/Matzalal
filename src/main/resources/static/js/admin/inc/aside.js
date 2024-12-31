window.addEventListener("load", function(){
	let asideIcon = document.querySelector(".aside-icon");
    let menuBtn = document.querySelector("#aside-icon");
    let sidebarSection = document.querySelector(".sidebar");

    
    	menuBtn.addEventListener('change', function(e){
			e.preventDefault();

			if(menuBtn.checked){
				sidebarSection.classList.add("show");
				asideIcon.classList.add("aside-show");
			} else{
				sidebarSection.classList.remove("show");
				asideIcon.classList.remove("aside-show");
			}		
		});
});