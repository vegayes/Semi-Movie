package semi.project.movieInsight.mypage.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.dto.Menu;
import semi.project.movieInsight.member.dto.Member;
import semi.project.movieInsight.movie.dao.MovieDAO;
import semi.project.movieInsight.movie.dto.Movie;
import semi.project.movieInsight.mypage.dao.MypageDAO;


@Service
public class MypageServiceImpl implements MypageService{
	
	@Autowired
	private MypageDAO dao;
	
// 1) 즐겨찾기
	/**
	 * 1-1) 영화 즐겨찾기
	 */
	@Override
	public List<Movie> selectLikeMovie(int memberNo) {
		return dao.selectLikeMovie(memberNo);
	}


	/**
	 * 1-2) 영화관 즐겨찾기
	 */
	@Override
	public List<Cinema> selectLikeCinema(int memberNo) {
		return dao.selectLikeCinema(memberNo);
	}

	
//  3) 댓글 조회 

	/**
	 * 3-1) 영화 댓글 조회 
	 */
	@Override
	public List<Movie> selectCommentMovie(int memberNo) {
		return dao.selectCommentMovie(memberNo);
	}

	
	/**
	 * 3-2) 영화관 댓글 조회 
	 */
	@Override
	public List<Cinema> selectCommentCinema(int memberNo) {
		return dao.selectCommentCinema(memberNo);
	}

	
// 4) 정보수정	
	
	/**
	 * 4-1) 닉네임 중복 확인
	 */
	@Override
	public int checkNickname(String nickname) {
		return dao.checkNickname(nickname);
	}
	
	/**
	 * 4-2) 프로필 수정 확인
	 */
	@Override
	public int updateProfile(MultipartFile profileImage, String webPath, String filePath, Member loginMember) throws IllegalStateException, IOException {
		
		// 프로필 이미지 변경 실패 대비
		String temp = loginMember.getMemberProfile(); // 기존에 가지고 있던 이전 이미지 저장
		
		String name = null; // 변경 이름 저장 변수
	
		
		if(profileImage.getSize() > 0) { // 업로드된 이미지가 있을 경우
			
			System.out.println("profileImage:" + profileImage);
			
			System.out.println("profileImage.getOriginalFilname:" +profileImage.getOriginalFilename());

			System.out.println("webPath + profileImage:" +webPath+profileImage);
			
			System.out.println("filePath + profileImage.getOriginalFilname:" +filePath+profileImage.getOriginalFilename());

//			profileImage:MultipartFile[field="profileImage", filename=어벤져스-에이지 오브 울트론.jpg, contentType=image/jpeg, size=1155113]
//			profileImage.getOriginalFilname:어벤져스-에이지 오브 울트론.jpg
//			webPath + profileImage:/movieInsight/resources/images/member/MultipartFile[field="profileImage", filename=어벤져스-에이지 오브 울트론.jpg, contentType=image/jpeg, size=1155113]
			
			name = profileImage.getOriginalFilename();
			
			// 2) 바뀐 이름 loginMember에 세팅
			loginMember.setMemberProfile(name);
			
		} else { // 업로드된 이미지가 없는 경우 (x버튼) 
			
			loginMember.setMemberProfile(null);
			
		}
		
		
		// 프로필 이미지 수정 DAO 메서드 호출
		int result = dao.updateProfileImage(loginMember);
		
		
		if(result > 0) { // DB에 이미지 경로 업데이트 성공했다면
			
			// 업로드된 새 이미지가 있을 경우
			if(name != null) {
				
				
				// 메모리에 임시 저장되어있는 파일을 서버에 진짜로 저장하는 것
				profileImage.transferTo(new File(filePath + name));
				
				System.out.println("저장된 공간 ::" + filePath + name);
			}
			
			
		} else { // 실패
			
			// 이전 이미지로 프로필 다시 세팅
			loginMember.setMemberProfile(temp);
			
		}
		
		return result;
	}








	
	
	

}
