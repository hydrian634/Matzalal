window.addEventListener("load", function() {
    let menuNav = document.querySelector(".main-menu");
    let webUL = menuNav.querySelector("ul");

/*    let formSection = document.querySelector(".query-filter");
    let content = document.querySelector(".menu-card-list .content");*/
    let currentTab = webUL.querySelector("li.current a");

    webUL.addEventListener("click", function(e) {
        e.preventDefault();

        let el = e.target;

        if (el.tagName !== "A") {
            return;
        }

        console.log(el.className);

        currentTab.parentElement.classList.remove("current");
        el.parentElement.classList.add("current");
        currentTab = el;

        // Your business logic
        console.log(el.dataset.id);

        // /api/menus?c=
        let request = new XMLHttpRequest();
        request.open("GET", `/api/menus?c=${el.dataset.id}`, true);
        request.onload = function() {
            // console.log(request.responseText);
            let list = JSON.parse(request.responseText);
            // alert(request.responseText);

            content.innerHTML = "";
            for (let h of list) {
                let template = `
                    <section class="menu-card">
                        <!-- Your template content here -->
                    </section>
                `;

                content.insertAdjacentHTML("beforeend", template);
            }
        };
        request.send(); // send the request
    });
});
