

// 사진 추가시 첨부파일명을 박스에 넣는 js 코드
$("#file").on('change',function(){
    var fileName = $("#file").val();
    $(".upload-name").val(fileName);
  });