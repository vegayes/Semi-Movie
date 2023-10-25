package semi.project.movieInsight.main;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@Controller
public class MainController {
	
	@RequestMapping("/")
	public String test1() {

//		System.out.println("1: 관리자-영화관, 2.관리자-영화, 3.관리자 멤버, 4.영화관 홈페이지\n"
//						+ "5: 마이페이지, 6: 메인페이지, 7: 관리자-메뉴, 8:관리자-홍보 " );
//		Scanner sc = new Scanner(System.in);
//		int num = sc.nextInt();
//		String path = "";
//		
//		switch(num) {
//		
//		case 1: return  "manager/manager-cinema"; 
//		
//		case 2 : return "manager/manager-movie"; 
//		
//		case 3 : return "manager/manager-member"; 
//		
//		case 4 : return "cinema/cinema-homepage"; 
//		
//		case 5 : return "mypage/mypage";
//		
//		case 6 : return "common/main"; 
//		
//		case 7 : return "admin/admin-menu"; 
//		
//		case 8 : return "admin/admin-event";  
//		
//		default : return "common/main";
//		}
		

//		return "member/login_signUp";
//		return "member/find_id";
//		return "movie/home-page";
//		return "cinema/cinema-detail-page";
//		return "cinema/cinema-homepage";
		return "manager/manager-menu";
	}
	
}
