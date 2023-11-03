package semi.project.movieInsight.cinema.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Menu {
	
    private int menuNo;
    private String menuName;
    private int menuPrice;
    private String menuImg;
    private String menuCategory;	
	
    // 메뉴 평점
    private float menuGrade;
    private int memberNo; // 평점을 준 사람
    
    // 판매하는 영화관
    private String cinemaName;
    
    

}
