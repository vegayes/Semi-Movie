package semi.project.movieInsight.cinema.service;

import java.util.List;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.movie.dto.Movie;


public interface CinemaDetailService {

	
	Cinema selectCinemaInfo(String cinemaName);

	List<Movie> selectMovieList(int cinemaNo);
<<<<<<< HEAD
	
	
	
=======

>>>>>>> 6f135ff8201beb7313378095197b39692c747cb5
}
