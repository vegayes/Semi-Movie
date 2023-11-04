package semi.project.movieInsight.movie.dao;

import java.util.ArrayList;
import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import semi.project.movieInsight.movie.dto.Movie;

@Repository
public class MovieDAO {
	
	@Autowired 
	private SqlSessionTemplate sqlSession;

	public List<Movie> searchMovieList(String movieQuery) {
		
		return sqlSession.selectList("movieMapper.searchMovieList", movieQuery);
	}

	/** 관리자 영화 조회 
	 * @return
	 */
	public List<Movie> selectManagerMovieList() {
		
		return sqlSession.selectList("movieMapper.selectManagerMovieList");

	}

	  // 장르에 따른 영화 조회
    public List<Movie> findMoviesByCategory(String category) {
    	System.out.println(category);
        return sqlSession.selectList("movieMapper.findMoviesByCategory", category);
    }

	/** header의 정렬 클릭 시 
	 * @param query
	 * @return
	 */
	public List<Movie> orderByMovieList(String query) {
		
		List<Movie> movieList = new ArrayList<Movie>();
		
		if(query.equals("인기순")) {
			movieList = sqlSession.selectList("movieMapper.orderByMovieList", query);
		}
		
		return null;
	}


}
