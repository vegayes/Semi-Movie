package semi.project.movieInsight.cinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import semi.project.movieInsight.cinema.dao.CinemaDAO;
import semi.project.movieInsight.cinema.dto.Cinema;

@Service
public class CinemaServiceimpl implements CinemaService {

	@Autowired
	private CinemaDAO dao;
	
	
	/**
	 *	영화관 검색결과 조회
	 */
	@Override
	public List<Cinema> searchCinemaList(String cinemaQuery) {

		return dao.searchCinemaList(cinemaQuery);
	}


	/**
	 * 즐겨찾기 영화관 조회
	 */
	@Override
	public List<Cinema> selectLikeCinema() {
		return null;
	}

}
