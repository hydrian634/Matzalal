window.addEventListener("load", function(){
    let content = document.querySelector(".content");

    // let heart = content.querySelector("#heart");
    let hearts = content.querySelectorAll(".heart");

    hearts.forEach(function(heart) {
        heart.onclick = async function (e) {
            e.preventDefault();
            // let el = e.target;

            // console.log("유저id:" + el.dataset.name);
            // console.log("식당id:" + el.dataset.id);
            //
            // let url = `/api/favs/${el.dataset.id},${el.dataset.name}`
            //
            // let response = await fetch(url, {
            //     method:"DELETE"
            // });
            //
            // let list = await response.json();
            // console.log(list);
            alert("삭제되었습니다.");
            // bind(list);

        }
    });

    // function bind(list){
    //     content.innerHTML = "";
    //     for (let f of list) {
    //         let template = `
    //         <section class="fav-card md:fav-card" th:each="f : ${list}">
    //                 <h1 th:text="${f.restName}">수저가</h1>
    //                 <!--2 -->
    //                 <div class="image-block">
    //                     <img src="/css/image/icon/food1.svg" th:src="@{'/css/image/icon/'+${f.bannerImg}}">
    //                 </div>
    //                 <!-- 3-->
    //                 <div class="content-block">
    //                     <p th:text="${f.content}">차돌박이 + 밥 무한리필 최고</p>
    //                 </div>
    //                 <!--4 -->
    //                 <div class="like-block">
    //                     <a data-id="3" th:data-name="${f.userId}"  th:data-id="${f.restId}" href="" id="heart" class="heart icon icon-size:2 icon-redheart icon-color:red ">좋아요</a>
    //                 </div>
    //                 <!— 5—>
    //                 <div class="star-review-block">
    //                     <a href="" class="icon icon-size:3 icon-color:yellow icon-star:fill">별</a>
    //                     <span>3.5 (<span th:text="${f.reviewCount}">15</span> review)</span>
    //                 </div>
    //             </section>
    //         `;
    //         content.insertAdjacentHTML("beforeend", template);
    //
    //     }
    // }

    });