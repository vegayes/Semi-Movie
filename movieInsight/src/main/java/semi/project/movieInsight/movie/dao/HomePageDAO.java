package semi.project.movieInsight.movie.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import semi.project.movieInsight.movie.dto.Movie;

@Repository
public class HomePageDAO {

    @Autowired
    private SqlSessionTemplate sqlSession;

    public Movie selectMovie(int movieNo) {
        return sqlSession.selectOne("movieMapper.selectMovie", movieNo);
    }
}
