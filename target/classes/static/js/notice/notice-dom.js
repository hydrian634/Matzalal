window.addEventListener("load", function(){
	let searchInput = document.querySelector(".search-input"); //검색창 범위
	let searchBtn = document.querySelector(".btn-search"); //검색버튼 범위
	let content = document.querySelector(".notice-template"); //꽂아줄 부분 상위 범위
	
	searchBtn.onclick = async function(e) {
        e.preventDefault();

		let query = searchInput.value;
		console.log("검색어: "+query);		
		
		//컨트롤러로 보내주기.
		let response = await fetch(`/api/notices?q=${query}`);
		let json = await response.json();
		console.log("json: ", JSON.stringify(json));
		bind(json);
	}
	
	//페이지(=템플릿) 화면에 출력
	function bind(list){	
		content.innerHTML = "";    //부수고 들어간다.
        for (let n of list){	
			let formattedDate = formatDate(n.createdDate);
			
		let template = `
	       <section class="notice-list-box-left">
                    <h1 class="d:none">공지사항 목록</h1>
                    <div class="list-left-side">
                        <span class="notice-number">${n.noticeId}</span><span>.</span>
                        <h1>${n.title}</h1>
                        <div class="reg-date">${formattedDate}</div>
                    </div>
                </section>
            `;
            content.insertAdjacentHTML("beforeend", template); // 부수지 않고 새로 짓는 것.
        }
    }

    // 수정된 부분: 날짜 포맷팅 함수
    function formatDate(dateString) {
        const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
        const formattedDate = new Date(dateString).toLocaleDateString('ko-KR', options);
        return formattedDate;
    }
});