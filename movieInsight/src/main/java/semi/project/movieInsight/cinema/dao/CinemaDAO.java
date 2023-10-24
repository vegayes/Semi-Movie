package semi.project.movieInsight.cinema.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import semi.project.movieInsight.cinema.dto.Cinema;

@Repository
public class CinemaDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<Cinema> searchCinemaList(String cinemaQuery) {
		
		System.out.println("DAO 입장");
		return sqlSession.selectList("cinemaMapper.searchCinemaList", cinemaQuery);
	}

}
