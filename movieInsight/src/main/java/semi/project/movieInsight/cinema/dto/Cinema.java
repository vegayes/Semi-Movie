package semi.project.movieInsight.cinema.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Cinema {

	private int cinemaNo;
	private String cinemaName;
	private String cinemaAddress;  
	private String cinemaContact;
	private String cinemaImg;
	private String cinemaLink;
	private int cinemaMaxInclude;
	private String cinemaDelYN;
	private String cinemaSpecialHall;
    private int sumCinemaGrade;			// 영화관 평점 계산 
	
	// 즐겨찾기 CINEMA 등록일
	private String cinemaLikeEnrollDate;
	
	// 영화관 즐겨찾기
	private int cinemaCommentNo; 
    private String cinemaCommentContent;			
    private String cinemaCommentDate;
    private String cinemaCommentType;
    private int cinemaGrade; // 각 평점 
    
   
    // 영화관 구분
    private String 	cinemaType;  // 영화관이면 C , OTT 면 O	
}
