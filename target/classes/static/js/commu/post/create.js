
let areaSelect = document.querySelector("select[name=area-id]");
let inputTitle = document.querySelector("textarea[name=title]");
let inputContent = document.querySelector("textarea[name=content]");
let input = document.querySelector("input[type=file]");
let regBtn = document.querySelector("input[type=submit]");




areaSelect.oninput = function (e){
  console.log(e.target.value);
  console.log(areaSelect.value);
}

inputTitle.oninput = function (e){
  console.log(inputTitle.value);
}

inputContent.oninput = function (e) {
  console.log(inputContent.value);
}

input.oninput = function (e) {
  let el = e.target;

  const files = e.target.files;
  let text = "";

  let img = files[0];
  console.log(files);

  console.log(input.files[0]);

  for(const file of files){
    text += `${file.name}: ${file.type || '알수없음'}\n`;
  }
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
        let img = input.nextElementSibling
        img.src = reader.result;
        // preview.src = reader.result;
      },
      false,
  );


}

regBtn.onclick = function(e){
  e.preventDefault();

  console.log(areaSelect.value);
  console.log(inputTitle.value);
  console.log(inputContent.value);
  console.log(input.files[0]);

  let areaId = areaSelect.value;
  let title = inputTitle.value;
  let content = inputContent.value;
  let img = input.files[0];

  let formData = new FormData();
  formData.append("img", img);
  formData.append("title", title)
  formData.append("areaId", areaId);
  formData.append("content", content);

  console.log(formData);


  let imgg = formData.get("img");
  console.log("==============전송되는 form데이터===============");
  console.log(imgg);

  let request = new XMLHttpRequest();
  request.open("POST", "/api/commus/posts");
  // request.setRequestHeader("Content-Type", "application/json");
  request.onload = () => {
    console.log("전송완료!!");
    console.log(request.responseText);
    alert("등록되었습니다.");
    window.location="/commu/main"

  }
  request.send(formData);
}


/* ---------- 이미지 출력하기(파일 읽기) ---------- */

//	    let reader = new FileReader();
//
//		    reader.addEventListener("load", () => {
//		            let img = fileInput.nextElementSibling
//		            img.src = reader.result;
//		            // preview.src = reader.result;
//		        },
//		        false,
//	   		);
//		    reader.readAsDataURL(img);
//    		console.log(text);

// 파일을 바이너리로 아직 읽은 건 아니라
// 이제 바이너리로 읽으려면 도구가 필요해 = filereader

/* ---------- form 입력값 받기 ---------- */

//	let form = document.querySelector(".post-create form");
//	let areaId = form.querySelector("select[name=area_id]").value; // 이건 html에서 가져온거.
//	let title = form.querySelector("textarea[name=title]").value;
//	let content = form.querySelector("textarea[name=content]").value;
//
//	console.log(areaId, title, content);


/* ---------- 이미지 저장하기(---------- */

//	    let savedImg = fileInput.files;
//
//	    console.log(savedImg.name);
//
//
//    	for(let img of files)
//		savedImg.append("imgFiles", img);// entity 속성명과 안겹치게하자.
//
//		let request = new XMLHttpRequest();
//			request.onload = () => {
//				 		let newOne = JSON.parse(request.responseText);
//			};
//		request.open("POST", "/api/posts");
//		request.send(formData);
//	}
//


	


