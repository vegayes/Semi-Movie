package semi.project.movieInsight.mypage.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.dto.Event;
import semi.project.movieInsight.cinema.dto.Menu;
import semi.project.movieInsight.cinema.dto.Promotion;
import semi.project.movieInsight.member.dto.Member;
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
	public int updateCinema(MultipartFile cinemaImage, String filePath, Cinema cinemaInfo) 
		throws Exception{
		
		//System.out.println("영화관 사진이름 : " + cinemaImage.getOriginalFilename());
		cinemaInfo.setCinemaImg(cinemaImage.getOriginalFilename());
		int result = dao.updateCinema(cinemaInfo);
		
		
		if(result > 0) { 
			
			if(cinemaImage.getSize() != 0) {
				cinemaImage.transferTo(new File(filePath + cinemaImage.getOriginalFilename()));
				
			}		
			
		}else {
			
			 throw new RuntimeException("Cinema update failed ");
		}
		
		return result;
		
	}


	/**
	 * 영화관 새로 등록
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertCinema(MultipartFile cinemaImage,String filePath, Cinema cinemaInfo) throws Exception{
		
				cinemaInfo.setCinemaImg(cinemaImage.getOriginalFilename());
				int result = dao.insertCinema(cinemaInfo);
				
				
				if(result > 0) { 
					
					if(cinemaImage.getSize() != 0) {
						cinemaImage.transferTo(new File(filePath + cinemaImage.getOriginalFilename()));
						
					}		
					
				}else {
					
					 throw new RuntimeException("Cinema insert failed ");
				}
				
				return result;
				
	}

	/**
	 * 영화 새로 등록
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertMovie(Movie movieInfo, MultipartFile movieImage, String filePath, List<String> actorNamesList, List<String> directorNamesList) throws Exception {
		
		movieInfo.setMovieImg(movieImage.getOriginalFilename());
		int result = dao.insertMovie(movieInfo,actorNamesList,directorNamesList);
		
		if(result > 0) { 
			
			if(movieImage.getSize() != 0) {
				movieImage.transferTo(new File(filePath + movieImage.getOriginalFilename()));
				
			}		
			
		}else {
			
			 throw new RuntimeException("Cinema insert failed ");
		}
		
		return result;
	}

	
	
	/**
	 * 영화 번호 얻어옴
	 */
	@Override
	public int selectMovieNo(String movieTitle) {
		return dao.selectMovieNo(movieTitle);
	}

	
	
	
	/**
	 * 영화 업데이트
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateMovie(Movie movieInfo) {
		
		int result = dao.updateMovie(movieInfo);
		
		if(result > 0) { 
			
			return result;
			
		}else {
			
			 throw new RuntimeException("Cinema insert failed ");
		}
		
	}
	
	
	
	
	///===============================특별관======================================================///////////
	
	/**
	 * 관리자가 특별관 홍보 추가
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertPromotion(MultipartFile image, String filePath, Map<String, Object> promotionMap) throws Exception{
		
		
		int result = dao.insertPromotion(promotionMap);
		
		if(result > 0) { 
			
			if(image.getSize() != 0) {
				image.transferTo(new File(filePath + image.getOriginalFilename()));
				
			}		
			
		}else {
			
			 throw new RuntimeException("Promotion insert failed ");
		}
		
		return result;
	}
	
	
	
	
	
	/**
	 * 관리자가 이벤트 정보 추가
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertEvent(Map<String, Object> eventMap, MultipartFile image, String filePath) throws Exception{
		
		int result = dao.insertEvent(eventMap);
		
		if(result > 0) { 
			
			if(image.getSize() != 0) {
				image.transferTo(new File(filePath + image.getOriginalFilename()));
			}		
			
		}else {
			 throw new RuntimeException("Promotion insert failed ");
		}
		
		return result;
	}

	
	/**
	 * 이벤트 삭제 메서드
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int deleteEvent(String eventTitle) {

		 int result = dao.deleteEvent(eventTitle);

		    if (result > 0) {
		        return result;
		    } else {
		       
		        throw new RuntimeException("event deletion failed for cinemaNo: " + eventTitle);
		    }
	}

	
	/**
	 * 이벤트 삭제 메서드
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int deletePromotion(String promotionType) {

		 int result = dao.deletePromotion(promotionType);

		    if (result > 0) {
		        return result;
		    } else {
		       
		        throw new RuntimeException("promotion deletion failed for promotionNo: " + promotionType);
		    }
	}

	
	
	/**
	 *  메뉴 추가 메서드
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertMenu(Map<String, Object> menuMap, MultipartFile image, String filePath) throws Exception {
		
		
		
		int result = dao.insertMenu(menuMap);
		
		if(result > 0) { 
			
			if(image.getSize() != 0) {
				image.transferTo(new File(filePath + image.getOriginalFilename()));
				
			}		
			
		}else {
			
			 throw new RuntimeException("insertMenu insert failed ");
		}
		
		return result;
		
	}

	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deleteMenu(int menuNo) {
		
		int result = dao.deleteMenu(menuNo);
		
		if(result > 0) { 
			
			return result;
			
		}else {
			
			 throw new RuntimeException("deleteMenu delete failed ");
		}
		
	}

	
	// 영화, 영화관 댓글목록 가져오는 메서드
	@Override
	public List<Movie> selectMovieComment(int memberNo) {
		
		return dao.selectMovieComment(memberNo);
	}

	@Override
	public List<Cinema> selectCinemaComment(int memberNo) {
		
		return dao.selectCinemaComment(memberNo);
	}

	@Override
	public Member selectMemberInfo(int memberNo) {
	
		return dao.selectMemberInfo(memberNo);
	}

	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deleteMember(int memberNo) throws Exception{
		
		int result = dao.deleteMember(memberNo);
		
		if(result > 0) { 
			
			return result;
			
		}else {
			
			 throw new RuntimeException("deleteMember delete failed ");
		}
		
	}

	

	
	
	

	
	
	
	
	


	



	
	
	
}
