import { initializeApp } from "https://www.gstatic.com/firebasejs/10.5.2/firebase-app.js";
import { getAnalytics } from "https://www.gstatic.com/firebasejs/10.5.2/firebase-analytics.js";
import { getAuth, RecaptchaVerifier, signInWithPhoneNumber } from "https://www.gstatic.com/firebasejs/10.5.2/firebase-auth.js";



// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyB5HH0ilocOL-UJnXHMYwsbTdvNjuHDnDM",
    authDomain: "matzalal.firebaseapp.com",
    projectId: "matzalal",
    storageBucket: "matzalal.appspot.com",
    messagingSenderId: "1020068727880",
    appId: "1:1020068727880:web:54df3ca6ce410f500c1c7d",
    measurementId: "G-RKG8L8LYKL"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);

const auth = getAuth();
auth.languageCode = 'it';

// 리케차 안보이기
window.recaptchaVerifier = new RecaptchaVerifier(auth, phoneNumberButton, {
    'size': 'invisible',
    'callback': (response) => {
        // reCAPTCHA solved, allow signInWithPhoneNumber.
        onSignInSubmit();
    }
}, auth);


let inputPhone = document.querySelector("#phoneNumber");
let invalidPhoneLegnth = document.querySelector("#invalid-phone-length");
let invalidPhone = document.querySelector("#invalid-phone");



// 핸드폰번호 유효성검사
inputPhone.oninput = function (e){
    e.preventDefault();
    let el = e.target;
    let phone = el.value;
    let size = phone.length;

    invalidPhone.classList.add("d:none");

    console.log(phone);
    if(size < 11) {
        invalidPhoneLegnth.classList.remove("d:none");
    } else
        invalidPhoneLegnth.classList.add("d:none");


}


// 핸드폰 입력에 따른처리
document.getElementById('phoneNumberButton').addEventListener('click', async (e)=> {
    e.preventDefault();


    const inputName = document.querySelector("input[name=name]").value;
    const phoneNumber = document.getElementById('phoneNumber').value;
    let formatPhoneNumber = phoneNumber.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');

    // 핸드폰, 이름 매칭
    console.log("입력한 이름 : " + inputName);
    console.log("입력한 핸드폰번호 :" + formatPhoneNumber);

    let response = await fetch(`/api/strangers/findid?name=${inputName}&phone=${formatPhoneNumber}`)
    let result = await response.json();

    console.log(result); // 입력한 이름과 핸드폰번호 일치 여부
    if (result === false) {
        console.log("존재하지않습니다.");
        invalidPhone.classList.remove("d:none");
        return;
    }


    console.log("존재합니다");

    const appVerifier = window.recaptchaVerifier;

    signInWithPhoneNumber(auth, '+82'+phoneNumber, appVerifier)
        .then((confirmationResult) => {
            // SMS sent. Prompt user to type the code from the message, then sign the
            // user in with confirmationResult.confirm(code).
            window.confirmationResult = confirmationResult;
            // ...
            console.log(confirmationResult);
            console.log("인증코드를 보냈습니다.");
            alert("입력하신 번호로 인증코드를 보냈습니다.");

        }).catch((error) => {
        // Error; SMS not sent
        // ...
        console.log(error);
        console.log("보내지지 않았습니다.");
    });
});

document.getElementById('confirmCodeButton').addEventListener('click', async (e)=> {
    e.preventDefault();

    console.log("인증버튼 클릭");

    // 인증 코드로 사용자 로그인 처리
   const code = document.getElementById('confirmCode').value;
    console.log("인증코드 입력 :: " + code);

    confirmationResult.confirm(code).then(async (result) => {     // 인증 성공
        // User signed in successfully.
        const user = result.user;
        // ...
        console.log(result)
        console.log("인증되었습니다.");

        // 인증되면 userid 가져오기

        const inputName = document.querySelector("input[name=name]").value;
        const phoneNumber = document.getElementById('phoneNumber').value;

        let formatPhoneNumber = phoneNumber.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');

        // 핸드폰, 이름 매칭
        console.log("입력한 이름 : " + inputName);
        console.log("입력한 핸드폰번호 :" + formatPhoneNumber);

        let response = await fetch(`/api/strangers?name=${inputName}&phone=${formatPhoneNumber}`)
        let sendResult = await response.text();

        console.log(sendResult); // 입력한 이름을부터의 아이디 출력
        alert("회원님의 아이디는 [" + sendResult + "] 입니다.");

        window.location="/stranger/login"

    }).catch((error) => { // 인증 실패

        // User couldn't sign in (bad verification code?)
        // ...

        console.log(error)
        alert("인증코드가 일치하지 않습니다.");
    });
})

let confirmCode = document.querySelector("#confirmCode");
let confirmButton = document.querySelector(".btn-certi");

// 인증번호 6자리 입력시 버튼색 추가
confirmCode.oninput = function(e){
    e.preventDefault();

    console.log(e.target.value);

    let code = e.target.value;
    let size = code.length;

    if(size ===6){
        confirmButton.classList.add("bg-color:logo-2");
        console.log("6개 입력!!!!");
    } else{
        confirmButton.classList.remove("bg-color:logo-2")
    }

    console.log(code.length);

}
