package semi.project.movieInsight.movie.dto;

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
    private int movieRunningTitle;		// 영화 상영시간
    private String movieEnrollDate; 	// 동영상 등록날짜
    private String movieDelYN;			// 영화 삭제 여부
    

//  배우 이름 조회 
    private String actorNames;
//  감독 이름 조회 
    private String directorNames;
    
    
    
}
