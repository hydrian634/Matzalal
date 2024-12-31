window.addEventListener("load", function(){
    let section = document.querySelector(".login")
    let btnLogin = section.querySelector(".btn-login");
    let inputEmail = section.querySelector("input[name=email]");
    let inputPwd = section.querySelector("input[name=pwd]");

    btnLogin.onclick = function(){
        console.log("Email : " + inputEmail.value);
        let query = inputEmail.value;

        let request = new XMLHttpRequest();
        request.open("GET", `/api/users?id=${email}`, true);
        request.send();
        request.onload = function(){
            alert(request.responseText);
        }


        console.log("Pwd : " + inputPwd.value);
        console.log("클릭했습니다.");

    };


})