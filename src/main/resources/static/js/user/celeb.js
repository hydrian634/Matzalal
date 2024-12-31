window.addEventListener("load", function () {
    let celebForm = document.querySelector(".align");
    let celebCategory = celebForm.querySelector("ul");

    let align = document.querySelector(".align");

    let celebRestList = document.querySelector(".celeb .celeb_rest_list");
    //------------카테고리 클릭----------------
    //첫번 째 li 자식의 a의 객체 대입
    let currentCategory = celebCategory.querySelector("li:first-child a");

    celebCategory.onclick = async function(e){
        e.preventDefault();

        let el = e.target;

        // console.log(el.dataset.id);
        if (el.tagName !== "A")
            return;

        currentCategory.classList.remove("clicked");
        el.classList.add("clicked");
        currentCategory = el;


        let response = await fetch(`/api/celebs?c=${el.dataset.id}`);

        if(el.dataset.id === undefined)
            response = await fetch(`/api/celebs?`);

        let json = await response.json();
        bind(json);
    }
    // -----------------------------데이터 바인딩--------------------------------

    function bind(CelebRestViewList){
        // let list = JSON.parse(json);

        celebRestList.innerHTML = "";

        for(let cr of CelebRestViewList){
            let template = `
                        <article class="celeb_rest">
                            <h1 class="d:none" >또순이네</h1>
                            <a class="rest_name" href="/celeb/detail?restId=${cr.restId}&menuId=${cr.menuId}">${cr.restName}</a>
                            <a href="/celeb/detail?restId=${cr.restId}&menuId=${cr.menuId}"><img src="/css/image/rest/bannerImg/${cr.bannerImg}"></a>
                        </article>`;

            celebRestList.insertAdjacentHTML("beforeend",template);
        }
    }
    // -----------------------------데이터 바인딩 끝--------------------------------
    // let currentCategory = celebCategory.querySelector("li:first-child a");
    //
    // celebCategory.onclick = async function (e) {
    //     e.preventDefault(); //get요청 막아서 페이지 새로고침 되는 것을 막음.
    //
    //     let el = e.target;
    //
    //     //DOM강의에 있음. 근데 target으로 대상을 지정? 해줘야 함?
    //     if (el.tagName !== "A")
    //         return;
    //     console.log(el.dataset.id);
    //
    //     //class=className
    //     //원리 : class에 current라는 클래스를 넣는 것.
    //     currentCategory.classList.remove("current");
    //     el.classList.add("current");
    //     currentCategory = el;
    //
    //     let response = await fetch(`/api/menus?c=${el.dataset.id}`);
    //     if(el.dataset.id === undefined)
    //         response = await fetch(`/api/menus?`);
    //     let json = await response.json();
    //     bind(json);
    // };


    // ------------------검색------------------
    // findButton.onclick = async function (e) {
    //     e.preventDefault();
    //
    //     let query = inputQuery.value;
    //     // let request = new XMLHttpRequest();
    //     // //request.open
    //     // //인자는 ("요청하는 방식", "요청할 주소", 비동기 방식에 대한 참/거짓)
    //     //
    //     // request.open("GET", `/api/menus?q=${query}`,true);
    //     //
    //     // request.onload=function(){
    //     //   bind(request.responseText);
    //     // };
    //     // request.send();
    //     // console.log("watame");
    //
    //     let response = await fetch(`/api/menus?q=${query}`);
    //     let json = await response.json();
    //     bind(json);
    //
    // };

    //---------------------------------------

});

