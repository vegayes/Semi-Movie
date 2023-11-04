package semi.project.movieInsight.movie.dto;

import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Movie {
	
    private int movieNo;				// 영화번호
    private String movieTitle;			// 영화제목
    private String movieImg;			// 영화이미지
    private String movieSummary;		// 영화 줄거리
    private String movieGenre;			// 영화 장르
    private String movieReleaseDate;	// 영화 개봉일
    private String movieAge;			// 관람 나이
    private int movieRunningTime;		// 영화 상영시간
    private String movieEnrollDate; 	// 동영상 등록날짜
    private String movieDelYN;			// 영화 삭제 여부


    private float sumMovieGrade;			// 영화 평점 계산 


//  배우 이름 검색 구분자 조회 
    private String actorNames;
//  감독 이름 검색 구분자 조회 
    private String directorNames;
    
 /*
// 출연진 이름 
    private String castingName;
// 출연진 나무위키 URL
    private String castingURL;
    
    */
    
	// 즐겨찾기 MOVIE 등록일
	private String movieLikeEnrollDate;
	
	
	
	// 댓글
	private int movieCommentNo; 
    private String movieCommentContent;			
    private String movieCommentDate;

    private float movieGrade; // 각 평점 
    private String commentMovieWriter; // 댓글 작성자 	
    private int memberNo; // 댓글 작성자 No
    private String writerProfile;// 댓글 작성자 프로필
    
    // 방문기록 넘버
    private int visitNo;

}
