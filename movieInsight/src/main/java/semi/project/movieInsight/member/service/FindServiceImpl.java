package semi.project.movieInsight.member.service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import semi.project.movieInsight.member.dao.FindDAO;

@Service
public class FindServiceImpl implements FindService {

	@Autowired
	private FindDAO dao;
	
	@Autowired
	private JavaMailSender mailSender;
	
	private String fromEmail = "eee595398@gmail.com";
	private String fromUsername = "아이디또는 비밀번호 찾기";
	
	
	@Transactional
	@Override
	public String findId(String email) {
		
	 
		return dao.selectFindId(email);
		
		
	
	}



}
