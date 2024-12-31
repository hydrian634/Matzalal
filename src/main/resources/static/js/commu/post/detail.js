window.addEventListener("load", function(){
	let commentBox = document.querySelector(".commentbox")
	let btnPost = commentBox.querySelector(".btn-post")
	let content = document.querySelector(".commentgroup"); //
	let deleteComment = document.querySelectorAll(".comment-delete");
	let deletePost = document.querySelectorAll(".post-delete");
	let currentUserId = document.querySelector("#userid");
	console.log("currentUserId" + currentUserId);
	console.log("currentUserId: " + currentUserId.content);0
	console.log("deleteComment"+deleteComment);
	console.log("deletePost"+deletePost);
// ------------ 포스팅 삭제  -------------

	deletePost.forEach((postDelete) => {

		postDelete.addEventListener("click", async (e) => {
			e.preventDefault();
			let el = e.target;
			console.log("클릭클릭 ");
			console.log(el.dataset.post);

			let url =`/api/commus/posts/${el.dataset.post}`;

			fetch(url,{
				method: 'DELETE'
			})
				.then(response => response.text())
				.then(data => {
					if (data === '') {
						alert("삭제가 완료되었습니다.");
						window.location.href = `/commu/main`;
					}
				})
				.catch(error => {
					console.error("삭제 요청 실패:", error);
				});

		});
	});


// ------------ 댓글 삭제  -------------

	deleteComment.forEach((commuDelete) => {
		commuDelete.addEventListener("click", async (e) => {
			e.preventDefault();
			let el = e.target;
			console.log("클릭클릭 ");
			console.log(el.dataset.comment);

			let url =`/api/commus/comments/${el.dataset.comment}`;

			fetch(url,{
				method: 'DELETE'
			})
				.then(response => response.text())
				.then(data => {
					if (data === '') {
						alert("삭제가 완료되었습니다.");
						window.location.href = `/commu/post/detail?&post-id=${el.dataset.post}`;
					}
				})
				.catch(error => {
					console.error("삭제 요청 실패:", error);
				});

		});
	});
// ------------------------ 댓글 등록 -------------------------

	btnPost.onclick =  async function (e) {
		e.preventDefault();
		let el = e.target;
		console.log("댓글등록 버튼 클릭 !" + el);
		console.log("댓글등록 버튼 클릭 !");
		console.log(" !" + el.dataset.postid );

		let commentContent = commentBox.querySelector(".comment-content").value;
		console.log(commentContent);

		let url = `/api/commus/comments`; //POST

		let response = await fetch(url, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify({
				postId: commentBox.dataset.postid,
				content: commentContent

			})

		})

		let data = await response.text();
		if (data !== '') {
			window.location.href = `/commu/post/detail?&post-id=${el.dataset.postid}`;

		}}


// ------------- 데이터 요청 후 응답기다리는...! -------------

	/*function bind(commentList){
		console.log(" --function bind 시작 부분-- ");
		console.log(" -commentList- " + commentList);


        content.innerHTML = ""; // section을 위한 DOM 객체를 직접 생성해서 append 한다.

        for (let c of commentList){
		console.log(" 현재로그인한 유저:  " + currentUserId.content);
		console.log(" 댓글쓴 userid :  " + c.userId);
//			let iconEdit =  currentUserId.content = c.userId

            let template = `

              <section class="comment-list-box md:comment-list-box">
                        <h2 class="d:none"> 댓글 </h2>

                        <section class ="top">
                            <h1 class="d:none"> 작성자 정보 & 작성시간 & 확장메뉴</h1>

                            <section class="member_info">
                                <h1 class="d:none"> 작성자 등급 & 닉네임</h1>

                                <div class="badge">
 								<img src="/css/image/icon/badge${c.gradeId}.svg"
                                    style="height:20px;" alt="등급2" />
                                </div>

                                <div class="nickname">
                                    <span >
                                        ${c.alias}
                                    </span>
                                </div>
                            </section>

                            <section class="date_info md:date_info">
                                <h1 class="d:none"> 작성 시간 & 확장메뉴</h1>
                                <div>
                                    <span>
                                        ${c.createdDate}
                                    </span>
                                </div>
       							 <!--
                                <div>
                                    <a class="icon  icon-commu-more icon-size-commu:2 icon-color:base-5" href=""> 세로 확장</a>
                                </div>
                                 -->

                            </section>

                        </section> <!-- top 끝 -->

                        <section class="middle">
                            <h1 class="d:none"> 댓글 내용</h1>
                            <p> ${c.content} </p>
                        </section> <!-- middle 끝 -->

            `;


	        content.insertAdjacentHTML("beforeend", template); // 때려부수지 않고 새로 짓는거
	        }
    }*/

});