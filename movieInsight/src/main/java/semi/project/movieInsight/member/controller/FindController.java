package semi.project.movieInsight.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import semi.project.movieInsight.member.service.FindService;
import semi.project.movieInsight.member.dto.Member;
@RequestMapping("/find")
@Controller
public class FindController {
	
	@Autowired
	private FindService service;
	
	
	// 아이디 찾기 페이지
	@GetMapping("/findID")
	public String Id() {
		
		return "member/find_id";
	}
	

	// 비밀번호 찾기 페이지
	@GetMapping("/findPW")
	public String Pw() {
		
		return "member/find_pw";
	}
	
	
	// 아이디 찾기 이메일
	@PostMapping("/findID")
	public String findId(String memberEmail, Model model) {
		
		//@RequestParam(value="memberEmail", required=true) 
		
	  String result= service.findId(memberEmail);
	  
	  if(result!=null) {
		  	
		  	// 아이디 전송
		 String re= service.sendEmail(result, memberEmail);
		  
		   model.addAttribute("message", "아이디가 이메일로 발송되었습니다.");
		   
		   
	  }else {
		  
		    model.addAttribute("message", "입력한 이메일 주소에 해당하는 사용자를 찾을 수 없습니다.");
	
		 
	  }

	   return "member/find_id";
	}

	
	
	//비밀번호 찾기 및 변경 후 암호화
	
	@PostMapping("/findPW")
	public String findPW(String memberEmail, String memberId, Model model) {
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberEmail", memberEmail);
		map.put("memberId", memberId);
		
			// 비밀번호 찾기
			Member member=service.findPW(map);
		
//			bcyrpt.matches(memberPw.)
			
			//System.out.println(member);
			
				// 비밀번호가 있다면 
			  if(member!=null) {
				  
				  	String memberPw = member.getMemberPw();
				  
				  	// 무작위 비밀번호 생성 후 저장
				  	// 보안상 문제
				  	String newPw = createPw(memberId);
				  	System.out.println(newPw);
				  	
				  	// 비밀번호 암호화
				  	String salt = BCrypt.gensalt(); // 암호화를 위한 동일한 솔트생성
				    String hashedPw = BCrypt.hashpw(newPw, salt);
					System.out.println(hashedPw);
					//BCryptPasswordEncoder
				    //passwordEncoder.matches 솔트매치 확인
					
					
					// 맵에 임시 비밀번호 저장
				  	map.put("memberPw",hashedPw);
					
				  	// 임시 비밀번호로 변경
				  	int result = service.updatePw(map);
				  	
				  	System.out.println(map);
				  	
				  	// 새로운 패스워드 보내기위해 다시 저장
				  	map.put("memberPw",newPw);
					service.sendEmail(map);
					  
				
				   model.addAttribute("message", "임시 비밀번호가 이메일로 발송되었습니다. 마이페이지에서 변경해주세요");
				   
				   
			  }else {
				  
				    model.addAttribute("message", "입력한 이메일 주소와 아이디에 해당하는 사용자를 찾을 수 없습니다.");
			
			  }
			
			
		 return "member/find_pw";
	}

	// 무작위 비밀번호 생성 
	private String createPw(String memberId) {
	
		
		return service.createPw(memberId);
	}
	
	


	
}
