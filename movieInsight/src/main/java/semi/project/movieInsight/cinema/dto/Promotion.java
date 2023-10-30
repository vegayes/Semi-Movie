package semi.project.movieInsight.cinema.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Promotion {
	
	private int promotionNo;
	private String promotionType;
	private String promotionImg;  
	private String promotionURL;
	private String promotionContent;
	private String promotionDelYN;
	private int cinemaNo;


}
