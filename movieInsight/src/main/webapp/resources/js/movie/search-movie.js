// 1) search로 검색된 값으로 이동 
const searchMovieMove = document.getElementsByClassName("movie-search-list-container");
var movieNo = null; 
function movieInfo(movieInfo) {
    movieNo  = movieInfo;
}

for(let i=0 ; i< searchMovieMove.length ; i++){
    searchMovieMove[i].addEventListener('click', ()=> {
        location.href = location.pathname.replace("search","movie/")
        + movieNo;
    });
}



























