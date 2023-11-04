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
	private float sumCinemaGrade;			// 영화관 평점 계산 
	
	// 즐겨찾기 CINEMA 등록일
	private String cinemaLikeEnrollDate;
	
	// 영화관 즐겨찾기 ( 댓글 ) 
	private int cinemaCommentNo; 
    private String cinemaCommentContent;			
    private String cinemaCommentDate;
    private String cinemaCommentType;
    private float cinemaGrade; // 각 평점 
    
    private String writerProfile;// 댓글 작성자 프로필
    private String commentCinemaWriter; // 댓글 작성자 	
    private int memberNo; // 댓글 작성자 No
    private String cinemaCommentDelYN; // 댓글 삭제여부
    
    
    // 영화관 구분
    private String 	cinemaType;  // 영화관이면 C , OTT 면 O	
   
    
    
    
}
