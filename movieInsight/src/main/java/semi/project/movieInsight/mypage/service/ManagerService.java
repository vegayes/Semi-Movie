package semi.project.movieInsight.mypage.service;

import java.util.Map;

public interface ManagerService {
	
	/** 1) 모든 홍보정보 조회
	 * @return
	 */
	Map<String, Object> selectPromotion();

	/**2) 모든 회원정보 조회 
	 * @return
	 */
	Map<String, Object> selectMember();
}
