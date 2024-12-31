// document.getElementById("reviewForm").addEventListener("submit", function (e) {
//     e.preventDefault();
//
//     // let restId = "your_restaurant_id";
//     // let currentUrl = window.location.href;
//     // let newUrl = currentUrl + "?rest_id=" + restId;
//     // window.location.href = newUrl;
//
//     const formData = new FormData(this);
//     const reviewData = {
//         photo: formData.get("photo"),
//         rating: parseFloat(formData.get("rating")),
//         content: formData.get("content")
//     };
//
//     // AJAX 요청
//     const xhr = new XMLHttpRequest();
//     xhr.open("POST", "/api/reviews/create", true);
//     xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
//
//     xhr.onload = function () {
//         if (xhr.status === 200) {
//             const response = JSON.parse(xhr.responseText);
//             // 성공적으로 요청 처리
//             console.log(response);
//         } else {
//             // 요청 실패
//             console.error("요청 실패");
//         }
//     };
//
//     xhr.send(JSON.stringify(reviewData));
// });