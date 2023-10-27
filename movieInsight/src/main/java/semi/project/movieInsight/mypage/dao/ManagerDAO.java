package semi.project.movieInsight.mypage.dao;

import java.lang.reflect.Member;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import semi.project.movieInsight.cinema.dto.Promotion;

@Repository
public class ManagerDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	/** 1-1)특별관 전체 조회
	 * @return
	 */
	public List<Promotion> selectPromotionList() {
		return sqlSession.selectList("promotionMapper.selectPromotionList");
	}

	/** 1-2)홍보 전체 조회
	 * @return
	 */
	public List<Promotion> selectEventPromotionList() {
		return sqlSession.selectList("promotionMapper.selectEventPromotionList");
	}

	/**2-1) 회원정보 조회 
	 * @return
	 */
	public List<Member> selectMemberList() {
		return sqlSession.selectList("memberMapper.selectMemberList");
	}
	
	/**2-2) 회원 수 조회 
	 * @return
	 */
	public int selectMemberCount() {
		return sqlSession.selectOne("memberMapper.selectMemberCount");
	}

	
}
