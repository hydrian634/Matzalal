let findpw = document.querySelector(".findpw");

let inputName = findpw.querySelector("input[name=name]");
let inputEmail = findpw.querySelector("input[name=email]");
let sendMail = findpw.querySelector("fieldset button");
let confirmButton = document.querySelector(".btn-main");

inputName.oninput = (e)=>{
    e.preventDefault();

    console.log("입력한 이름 : " + inputName.value);
}

inputEmail.oninput = (e) =>{
    e.preventDefault();
    let el = e.target;
    let email = el.value;

    // invalidPhone.classList.add("d:none");

    console.log(email);

}

sendMail.onclick = async (e)=>{
    e.preventDefault();


    // 이메일, 이름 매칭
    console.log("클릭 시 입력한 이름 : " + inputName.value);
    console.log("클릭 시 입력한 이메일 :" + inputEmail.value);

    let email = inputEmail.value;
    let name = inputName.value

    let response = await fetch(`/api/strangers/findpw?email=${email}&name=${name}`)
    let result = await response.json();

    console.log(result); // 입력한 이름과 핸드폰번호 일치 여부
    if (result === false) {
        alert("입력하신 정보가 일치하지 않습니다.");
        confirmButton.classList.remove("bg-color:logo-2")
        return;
    }

    console.log(result);

    let data = {
        dataEmail : email,
        dataName : name
    }

    let user = JSON.stringify(data);
    console.log(JSON.stringify(data));

    let request = new XMLHttpRequest();
    request.onload = () => {
        alert("입력하신 이메일로 임시비밀번호가 전송되었습니다.");
        console.log(request.responseText);
        window.location="/stranger/login"
    }
    request.open("POST", `/api/strangers/findpw?email=${email}&name=${name}`);

    request.send(user);

}