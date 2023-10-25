//package semi.project.movieInsight.member.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import semi.project.movieInsight.member.service.EmailService;
//
//@Controller
//@RequestMapping("/sendEmail")
//public class EmailController {
//        
//   @Autowired
//   private EmailService service;
//   
//   public int signUp(String email) {
//	   return service.SignUp(email,"회원 가입");
//   }
//   
//   public int checkAuthKey(String inputKey, String email) {
//	    
//	   return service.checkAuthKey(inputKey,email);
//   }
//   
//}
