
let mypage = document.querySelector(".mypage");
let fileInput = mypage.querySelector("input[type=file]");

fileInput.oninput = function(e){
    console.log("파일입력 ");

    let el = e.target;
    console.log(el.dataset.id);

    const files = e.target.files;
    let text = "";

    for(const file of files){
        text += `${file.name}: ${file.type || '알수없음'}\n`;
    }

    let img = files[0];

    console.log(text);

    // 입력한 파일 타입제한 ( 이미지만 받기 )

    if(img.type.indexOf("image/") != 0){

        console.log("이미지파일만 입력이 가능합니다.");
        return;
    }

    // 이미지 출력하기(파일 읽기)
    let reader = new FileReader();

    reader.readAsDataURL(img);

    reader.addEventListener(
        "load",
        () => {
            let img = fileInput.nextElementSibling
            img.src = reader.result;
            // preview.src = reader.result;
        },
        false,
    );

    // 이미지 저장하기
    let savedImg = fileInput.files[0];
    let userId = el.dataset.id;

    console.log("유저 id" + userId);
    console.log("저장되는 이미지 파일이름 : " + savedImg.name);

    let formData = new FormData();
    formData.append("img", savedImg);
    formData.append("id", userId);

    let imgg = formData.get("img");
    console.log("==============전송되는 form데이터===============");
    console.log(imgg);

    let request = new XMLHttpRequest();
    request.open("POST", "/api/users");
    // request.setRequestHeader("Content-Type", "application/json");
    request.onload = () => {
        console.log("전송완료!!");
        console.log(request.responseText);

    }
    request.send(formData);



}