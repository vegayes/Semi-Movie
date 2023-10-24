package semi.project.movieInsight.member.service;

public interface EmailService {

   int SignUp(String email, String string);
   
   int checkAuthKey(String inputKey, String email);

    int signUp(String email, String title);

  
}