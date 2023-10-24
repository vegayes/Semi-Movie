package semi.project.movieInsight.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Member {
	
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberEmail;
	private String memberNickname;
	private String memberGender;
	private String memberProfile;
	private String memberEnrollDate;
	private String memberDelYN;
	
}
