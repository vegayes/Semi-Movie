package semi.project.movieInsight.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import semi.project.movieInsight.movie.dao.HomePageDAO;
import semi.project.movieInsight.movie.dto.Movie;

@Service
public class HomePageServiceImpl implements HomePageService {

    @Autowired
    private HomePageDAO homePageDAO;

    @Override
    public Movie selectMovie(int movieNo) {
        return homePageDAO.selectMovie(movieNo);
    }
}
