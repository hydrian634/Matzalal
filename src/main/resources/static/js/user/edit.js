window.addEventListener("load", function(){
    let edit = document.querySelector(".user-info-edit");
    let checkAlias = edit.querySelector(".alias-check");
    let inputAlias = edit.querySelector("input[name=alias]");
    let inputPwd = edit.querySelector("input[name=pwd]");
    let inputCheckPwd = edit.querySelector("input[name=checkpwd]");

    let dupliAlias = edit.querySelector("#dupli-alias");
    let invalidAlias = edit.querySelector("#invalid-alias");

    let InvalidPwd = edit.querySelector("#invalid-pwd");
    let checkedPwdInvalid = edit.querySelector("#invalid-checkpwd");
    let checkPwd = edit.querySelector(".pwd-check");

    let locBtn = edit.querySelector(".loc-btn");
    let locSelect = edit.querySelector(".loc-select");

    // 지역 변경
    locBtn.onclick = async (e) => {

        console.log("=================================================")
        console.log("=================지역 변경 버튼 =================")
        console.log("=================================================")
        e.preventDefault();

        let locId = locSelect.value;

        // 지역 유효성 확인
        if(locSelect.value ==="0"){
            alert("지역을 선택해주세요.");
            return;
        }

        let user = {
            locationId : locId
        }

        let response= await fetch(`/api/users/edit`, {
            method : 'PUT',
            headers: {
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify(user)
        });

        let result = await response.json();

        console.log(result);
        if(result === true) {
            alert("지역이 변경되었습니다.");

            window.location="/user/mypage";

        }

    }

    // 비밀번호 입력시
    inputPwd.oninput = async function(e){

        let el = e.target;
        console.log("입력한 비밀번호: " + el.value);

        let pwdPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;

        // 중복확인
        if(inputPwd.value === '') {
            InvalidPwd.classList.add("d:none");
        } else if(!inputPwd.value.match(pwdPattern)){
            console.log("유효하지않다!");
            InvalidPwd.classList.remove("d:none");
        } else
            InvalidPwd.classList.add("d:none");



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

    // 비밀번호 변경 버튼
    checkPwd.onclick = async function(e){
        e.preventDefault();

        let checkPwd = inputCheckPwd.value;
        console.log("입력한 패스워드 : " +  checkPwd);
        // 1. 닉네임 있는지 확인 , 있으면 리턴, 없으면 변경 O

        let user = {
            pwd : checkPwd
        }

        console.log("보내질 JSON : " + JSON.stringify(user));

        // 1.1 api로 보낸다
        let response= await fetch(`/api/users/edit`, {
            method : 'PUT',
            headers: {
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify(user)
        });

        let result = await response.json();

        console.log("변경결과: " + result);
        if(result === true){
            alert("입력하신 비밀번호로 변경이 완료되었습니다.");
            window.location="/user/edit"
        } else
            alert("다시 한번 입력해주세요.");

    }

    // 닉네임 변경 확인

    inputAlias.oninput = async function(e){
        e.preventDefault();
        let el = e.target;
        let query = el.value;
        console.log("입력한 닉네임:::: "+ el.value);

        let aliasPattern = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/ ]/gim;
        let jamoPattern = /([^가-힣a-z\x20])/i;

        let patternResult = aliasPattern.test(inputAlias.value);
        let jamoPatternResult = jamoPattern.test(inputAlias.value);

        console.log("닉네임 유효성검사!! true면 정규식위반!!" + patternResult);
        console.log("자음모음 유효성검사!! true면 정규식위반!!" + jamoPatternResult);

        let response= await fetch(`/api/users/edit?q=${query}`);
        let result = await response.json();

        console.log("닉네임 검색 결과 : " + result);
        if(result === true){
            dupliAlias.classList.remove("d:none");
            console.log("중복입니다.");
        } else if(inputAlias.value === ''){
            dupliAlias.classList.add("d:none");
        } else
            dupliAlias.classList.add("d:none");

    }

    checkAlias.onclick = async function(e){
        console.log("=============================================================")
        console.log("=====================닉네임 변경 버튼 ========================")
        console.log("=============================================================")
        e.preventDefault();

        let aliasPattern = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/ ]/gim;
        let jamoPattern = /([^가-힣a-z\x20])/i;

        let patternResult = aliasPattern.test(inputAlias.value);
        let jamoPatternResult = jamoPattern.test(inputAlias.value);

        console.log("닉네임 유효성검사!! true면 정규식위반!!" + patternResult);
        console.log("자음모음 유효성검사!! true면 정규식위반!!" + jamoPatternResult);

        if (!dupliAlias.classList.contains("d:none")
            || inputAlias.value.length<2
            || patternResult // 닉네임 정규식
            || jamoPatternResult // 자음모음 정규식
        ){
            alert("정해진 형식대로 입력해주시기 바랍니다.");
            return;
        }

        let el = e.target;
        console.log(el.dataset.id);

        let alias = inputAlias.value;
        console.log("입력한 닉네임 : " +  alias);
        // 1. 닉네임 있는지 확인 , 있으면 리턴, 없으면 변경 O

        let user = {
            id : el.dataset.id,
            alias : alias
        }

        console.log(JSON.stringify(user));

        // 1.1 api로 보낸다
        let response= await fetch(`/api/users/edit`, {
            method : 'PUT',
            headers: {
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify(user)
        });

        let result = await response.json();

        console.log("변경 결과(true면 변경 완료) :" + result);

        if(result === true ){
            alert("[" + alias + "] (으)로 변경되었습니다.");
            window.location="/user/mypage";
        }

    }

})