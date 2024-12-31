/*function readURL(input) {
    if (input.files && input.files[0]) {
      var reader = new FileReader();
      reader.onload = function(e) {
        document.getElementById('preview').src = e.target.result;
      };
      reader.readAsDataURL(input.files[0]);
    } else {
      document.getElementById('preview').src = "";
    }
  }*/
  
/*window.addEventListener("load", function(){
  let form = document.getElementById("editNotice");
  
  form.addEventListener("submit", function(e) {
    e.preventDefault();
    const formData = new FormData(this);
    console.log(formData)

    fetch('/admin/api/notices/update', {
        method: 'PUT',
        body: formData
    }).then(response => {
        console.log(body);
        window.location.href = `/admin/notice/list`;
        
    }).catch(error => {
        console.error('에러:', error);
    });
});
});*/