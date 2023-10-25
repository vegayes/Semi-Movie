package semi.project.movieInsight.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import semi.project.movieInsight.cinema.dto.Menu;
import semi.project.movieInsight.mypage.dao.MypageDAO;


@Service
public class MypageServiceImpl implements MypageService{
	
	@Autowired
	MypageDAO dao;
	
	
	
	 
	 
	@Override
	public Map<String, List<Menu>> selectMenu() {
		
		return dao.selectMenu();
	}

}
