window.addEventListener("load",function(){
    let regUser = document.querySelector(".user-reg");
    let inputEmail = regUser.querySelector("input[name=email]");
    let idInvalid = regUser.querySelector(".email-box #invalid");
    let inputPwd = regUser.querySelector("input[name=password]");
    let inputCheckPwd = regUser.querySelector("input[name=checked-password]");
    let pwdInvalid = regUser.querySelector("#invalid-pwd");
    let checkedPwdInvalid = regUser.querySelector("#invalid-checked-pwd");
    let inputPhone= regUser.querySelector("input[name=phone]");
    let phoneInvalid = regUser.querySelector("#invalid-phone");
    let inputAlias= regUser.querySelector("input[name=alias]");
    let aliasInvalid = regUser.querySelector("#invalid-alias");
    let aliasDouble = regUser.querySelector("#double-alias");



    // let idValid = document.querySelector(".email-box #valid")

    inputEmail.oninput = async function(e){
        e.preventDefault();
        console.log(e.target.value);

        let el = e.target;
        let query = el.value;
        console.log("입력한 메일:::: "+ el.value);

        // 이메일 유효성검사
        let emailPattern = /[a-z0-9]+@[a-z]+\.[a-z]{2,3}/;

        if(inputEmail.value === '') {
            idInvalid.classList.add("d:none");
        } else if(!inputEmail.value.match(emailPattern)){
            console.log("유효하지않다!");
        } else
            idInvalid.classList.add("d:none");


        // 중복 유무 ( 중복o - true, 중복x - false)
        let response= await fetch(`/api/users/reg?q=${query}`);
        let result = await response.json();

        // if(result == true) { // 중복 o
        //     idInvalid.classList.remove("d:none");
        //     valid.classList.add("d:none");
        //     console.log("중복입니다.");
        // } else { // 중복 x
        //     idInvalid.classList.add("d:none");
        //     valid.classList.remove("d:none");
        //     console.log("가능한 이메일입니다.")
        // }
        console.log(result);

        if(result === true) { // 중복 od
            idInvalid.classList.remove("d:none");
            console.log("중복입니다.");
        } else { // 중복 x
            idInvalid.classList.add("d:none");
            console.log("가능한 이메일입니다.");
        }
    }

    inputPwd.oninput = async function(e){

        let el = e.target;
        console.log("입력한 비밀번호11 1111: " + el.value);

        let pwdPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;

        console.log("입력한 비밀번호 정규식!! : " + inputPwd.value.match(pwdPattern))
        if(inputPwd.value === '') {
            pwdInvalid.classList.add("d:none");
        } else if(!inputPwd.value.match(pwdPattern)){
            console.log("유효하지않다!");
            pwdInvalid.classList.remove("d:none");
        } else
            pwdInvalid.classList.add("d:none");

    }

    inputCheckPwd.oninput = function(e){
        console.log("입력한 비밀번호 : " + inputPwd.value);
        console.log("체크한 비밀번호 : " + inputCheckPwd.value);

        if(inputCheckPwd.value === '') {
            checkedPwdInvalid.classList.add("d:none");
        }
        else if(inputPwd.value !==  inputCheckPwd.value) {
            console.log("일치하지 않다.");
            checkedPwdInvalid.classList.remove("d:none");
        } else
            checkedPwdInvalid.classList.add("d:none");
    }

    inputPhone.oninput = function(e){

        let el = e.target;
        console.log("입력한 핸드폰번호11 11121: " + el.value);

        let phonePattern = /^01[016789]-\d{3,4}-\d{4}$/;

        if(inputPhone.value === '') {
            phoneInvalid.classList.add("d:none");
        } else if(!inputPhone.value.match(phonePattern)){
            console.log("유효하지않다!");
            phoneInvalid.classList.remove("d:none");
        } else
            phoneInvalid.classList.add("d:none");

        // if(!inputPhone.value.match(phonePattern)) {
        //     console.log("유효하지않다!");
        //     phoneInvalid.classList.remove("d:none");
        //     // valid.classList.add("d:none");
        //     return;
        // } else
        //     phoneInvalid.classList.add("d:none");
    }

    inputAlias.oninput = async function(e){
        let el = e.target;
        console.log("입력한 닉네임 11 11122112334221: " + el.value);

        let query = el.value;
        let aliasPattern = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/ ]/gim;

        // if(aliasPattern.test(el.value)){
        //     console.log("유효하지않다!");
        //     aliasInvalid.classList.remove("d:none");
        // } else
        //     aliasInvalid.classList.add("d:none");
        console.log("정규식 유효성 검사 : " + !inputAlias.value.match(aliasPattern));

        //유효성 검사
        if(inputAlias.value === '') {
            aliasInvalid.classList.add("d:none");
        } else if(inputAlias.value.match(aliasPattern)){
            console.log("유효하지않다!");
            aliasInvalid.classList.remove("d:none");
        } else
            aliasInvalid.classList.add("d:none");

        // 중복검사
        let response= await fetch(`/api/users/reg?a=${query}`);
        let result = await response.json();

        console.log(result);

        if(result === true) { // 중복 o
            aliasDouble.classList.remove("d:none");
            console.log("중복입니다.");
        } else { // 중복 x
            aliasDouble.classList.add("d:none");
            console.log("가능한 닉네임입니다.");
        }
    }
})