package semi.project.movieInsight.main.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import semi.project.movieInsight.cinema.dto.Cinema;

@Repository
public class StaffKindDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// 영화관 조회
	public List<Cinema> cinemaStaff() {
		return sqlSession.selectList("staffMapper.selectCinema");
	}

	

}
