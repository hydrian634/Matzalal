window.addEventListener("load", function(){

    let privacy = document.querySelector("input[name=privacy-policy]")
    let age = document.querySelector("input[name=age-policy]")

    // privacy.onclick = function (e){
    //     e.preventDefault();
    //
    //     let el = e.target;
    //     console.log(el.checked);

        // if(privacy.classList.contains("icon-uncheckedbox")){ // 체크할떄
        //     privacy.classList.remove("icon-uncheckedbox");
        //     privacy.classList.add("icon-checkedbox");
        // } else { // 체크 풀을때
        //     privacy.classList.add("icon-uncheckedbox");
        //     privacy.classList.remove("icon-checkedbox");
        // }
    // }

    // age.onclick = function (e){
    //     e.preventDefault();
    //
    //     let el = e.target;
    //     console.log(el.value);
    //
    //     if(age.classList.contains("icon-uncheckedbox")){ // 체크할떄
    //         age.classList.remove("icon-uncheckedbox");
    //         age.classList.add("icon-checkedbox");
    //     } else { // 체크 풀을때
    //         age.classList.add("icon-uncheckedbox");
    //         age.classList.remove("icon-checkedbox");
    //     }
    // }
})
