package semi.project.movieInsight.mypage.service;

import java.io.File;
import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.dto.Menu;
import semi.project.movieInsight.cinema.dto.Promotion;
import semi.project.movieInsight.movie.dto.Movie;
import semi.project.movieInsight.mypage.dao.ManagerDAO;

/**
 * @author user1
 *
 */
@Service
public class ManagerServiceImpl implements ManagerService{

	@Autowired
	private ManagerDAO dao;

	/**
	 * 1) 모든 홍보정보 조회 
	 */
	@Override
	public Map<String, Object> selectPromotion() {
		
		// 1) 특별관 전체  가져오기
		List<Promotion> promotionList = dao.selectPromotionList();
		
		// 2) 홍보 전체  가져오기
		List<Promotion> eventPromotionList = dao.selectEventPromotionList();
		System.out.println(eventPromotionList);
		

		Map<String,Object> promotionMap = new HashMap<String, Object>();
		
		promotionMap.put("promotion", promotionList);
		promotionMap.put("event", eventPromotionList);
		
		
		return promotionMap;
	}

	/**
	 * 2) 모든 회원정보 조회 
	 */
	@Override
	public Map<String, Object> selectMember() {
		
		// 1) 회원 정보 가져오기 
		List<Member> memberList = dao.selectMemberList();
		// 2) 총 회원의 수 가져오기 
		int memberCount = dao.selectMemberCount();
		
		System.out.println("회원 모든 정보 " + memberList);
		
		System.out.println("회원수 " + memberCount);
		
		Map<String,Object> memberMap = new HashMap<String, Object>();
		
		memberMap.put("memberList", memberList);
		memberMap.put("memberCount", memberCount);
		
		
		
		return memberMap;
	}

	
	/**
	 * 관리자 페이지에서 메뉴 전체조회
	 */
	@Override
	public Map<String, List<Menu>> selectMenu() {
		
		return dao.selectMenu();
	}

	
	
	/**
	 * 관리자 페이지에서 영화관 삭제
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int deleteCinema(int cinemaNo) {
	    int result = dao.deleteCinema(cinemaNo);

	    if (result == 1) {
	        return 1;
	    } else {
	       
	        throw new RuntimeException("Cinema deletion failed for cinemaNo: " + cinemaNo);
	    }
	}

	
	
	
	/**
	 * 관리자 페이지에서 영화 삭제
	 */
	@Override
	public int deleteMovie(int movieNo) {
		
		 int result = dao.deleteMovie(movieNo);

		    if (result == 1) {
		        return 1;
		    } else {
		       
		        throw new RuntimeException("Movie deletion failed for movieNo: " + movieNo);
		    }
	}
	

	/**
	 * 영화관 정보 업데이트
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateCinema(MultipartFile cinemaImage, String webPath, String filePath, Cinema cinemaInfo) 
		throws Exception{
		
		//System.out.println("영화관 사진이름 : " + cinemaImage.getOriginalFilename());
		cinemaInfo.setCinemaImg(cinemaImage.getOriginalFilename());
		int result = dao.updateCinema(cinemaInfo);
		//System.out.println("filePath : " + filePath);
		
		if(result > 0) { 
			
			if(cinemaImage.getSize() != 0) {
				cinemaImage.transferTo(new File(filePath + cinemaImage.getOriginalFilename()));
				
			}		
			
		}else {
			
			 throw new RuntimeException("Cinema update failed ");
		}
		
		return result;
		
	}


	
	
	
}
