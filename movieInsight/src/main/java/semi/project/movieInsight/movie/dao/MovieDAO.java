package semi.project.movieInsight.movie.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
			movieList = sqlSession.selectList("movieMapper.orderByPopular");
			System.out.println("인기순");
		}else if (query.equals("최신순")) {
			movieList = sqlSession.selectList("movieMapper.orderByLatest");
			System.out.println("최신순");
		}else {
			movieList = null;
		}
	
		
		return movieList;
	}

	
	/** 장르별
	 * @param randomGenre
	 * @return
	 */
	public List<Movie> orderRandomGenre(String randomGenre) {
		return sqlSession.selectList("movieMapper.orderByGenre",randomGenre);
	}

	/** 유저 추천 순 
	 * @param memberNo
	 * @return
	 */
	public List<Movie> userPreMovies(int memberNo) {
		
		// 영화 리스트 가져가기
		List<Movie> userPreMovies = new ArrayList<Movie>();
		
		
		// 장르뽑기 
		List<String> genreList = sqlSession.selectList("movieMapper.genreList", memberNo);
		
		System.out.println(genreList);
		
		if(genreList != null) {
			
	        Random random = new Random();
	        int randomIndex = random.nextInt(genreList.size());
	
	        String randomGenre = genreList.get(randomIndex);
	
	        System.out.println("랜덤으로 선택된 장르: " + randomGenre);
	        
	        userPreMovies = sqlSession.selectList("movieMapper.genreUser", randomGenre);
			
		}else {
			// 아무것도 정보가 없는 경우
			// 랜덤하게 뽑기
			userPreMovies = sqlSession.selectList("movieMapper.random");
			
		}
		
		return userPreMovies;
	}


}
