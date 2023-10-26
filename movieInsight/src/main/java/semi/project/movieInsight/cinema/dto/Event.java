package semi.project.movieInsight.cinema.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Event {

	private int eventPRNo;
	private int cinemaNo;
	private String eventTitle;  
	private String eventContent;
	private String eventImg;
	private String eventURL;
	private String eventEnrollDate;
	private String eventDelYN;
	
	
}
