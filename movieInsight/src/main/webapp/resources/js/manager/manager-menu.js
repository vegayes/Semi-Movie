// 1) 메뉴 수정 버튼 누르면
const menuUpdate =document.getElementsByClassName("menu-update");

var click = null;
for(let i = 0; i <menuUpdate.length; i++){
    menuUpdate[i].addEventListener("click", function(){
            window.open("http://127.0.0.1:5556/admin-popup-menu-update.html",
             "팝업 수정","width=900, height=570, top=200, left=540");

    });
}



    // menu-search.js
    document.addEventListener("DOMContentLoaded", function() {
    const searchInput = document.getElementById("search-input");
    const searchBtn = document.getElementById("search-btn");
    const menuInfoTables = document.querySelectorAll(".menu-info-table");

    // 검색 함수
    function performSearch() {
        const searchText = searchInput.value.toLowerCase();

        menuInfoTables.forEach(function(table) {
            const rows = table.querySelectorAll("tr");
            rows.forEach(function(row) {
                const menuName = row.querySelector(".menu-title").textContent.toLowerCase();
                if (menuName.includes(searchText)) {
                    row.style.display = "flex";
                } else {
                    row.style.display = "none";
                }
            });
        });
    }

    // 검색 버튼 클릭 시
    searchBtn.addEventListener("click", function() {
        performSearch();
    });

    // Enter 키를 누를 때
    searchInput.addEventListener("keydown", function(event) {
        if (event.key === "Enter") {
            performSearch();
        }
    });
});

            
    
            