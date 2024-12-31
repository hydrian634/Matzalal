window.addEventListener("load", function(){
	
//	let noticeFilterSection = document.querySelector(".fixed-button .search");
//	let locUl = noticeFilterSection.querySelector("ul");
//	let currentLoc = locUl.querySelector("li:first-child a");
	let content = document.querySelector(".post-layout"); //피드 1열?????
	
    let findButton = document.querySelector(".query-filter .btn-search");
    
//    let noticeListBoxLeft = document.querySelectorAll(".notice-list-box-left"); 

// ------------------------- 검색하기 -------------------------
    findButton.onclick = async function (e) {
	
    let inputQuery = document.querySelector(".query-filter .input-query");
        e.preventDefault();

        let query = inputQuery.value;
		console.log("검색어: "+query)
        
		let response = await fetch(`/api/notices?q=${query}`);
		let json = await response.json();
		console.log("JSON: "+json)
		bind(json);
	}
	
// ------------- 데이터 요청 후 응답기다리는...! -------------
function bind(list){
	
    content.innerHTML = ""; // section을 위한 DOM 객체를 직접 생성해서 append 한다.

    for (let n of list){

        let template = `
            <!--디테일 조회 링크-->
			<div class="notice-list-box" id="link"
			th:onclick="|location.href='@{/notice/detail(notice-id=${n.noticeId})}'|">  <!-- 여기 div class로 구역 잡기 -->
	        <section class="notice-list-box-left">
	            <h1 class="d:none">공지사항 목록</h1>
	            <div class="list-left-side"><span class="notice-number">${n.noticeId}
	            	href="?n=1" th:href="@{?n={n} (n=${n.noticeId})}"></span><span>.</span>
	                <h1>${n.title}</h1>
	                <div class="reg-date"> ${#dates.format(n.createdDate, 'yyyy.MM.dd')}</div>
	            </div>
	        </section>
	            
	            <span class="gotodetail deco icon-right-arrow icon-size:0"></span> 
	            <section class="notice-list-box-right"></section>
	            <div class="list-right-side">
	                <span class="gotodetail deco icon-right-arrow icon-size:2">화살표</span>
	            </div>
	    	</div>
			 
	        `;
	        content.insertAdjacentHTML("beforeend", template); // 때려부수지 않고 새로 짓는거
		}
	}
});

