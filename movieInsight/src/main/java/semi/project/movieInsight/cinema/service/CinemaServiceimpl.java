package semi.project.movieInsight.cinema.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import semi.project.movieInsight.cinema.dao.CinemaDAO;
import semi.project.movieInsight.cinema.dao.CinemaDetailDAO;
import semi.project.movieInsight.cinema.dto.Cinema;

@Service
public class CinemaServiceimpl implements CinemaService {

	@Autowired
	private CinemaDAO dao;
	
	@Autowired
	private CinemaDetailDAO gradeDao;
	
	/**
	 *	영화관 검색결과 조회
	 */
	@Override
	public List<Cinema> searchCinemaList(String cinemaQuery) {

		return dao.searchCinemaList(cinemaQuery);
	}

	/**
	 * 관리자 페이지에서 영화관 검색결과 가져오기
	 */
	@Override
	public List<Cinema> selectManagerCinemaList() {
		
		return dao.selectManagerCinemaList();
	}

	/**
	 * 평점 가져오기
	 */
	@Override
	public float cinemaGrade(String cinemaName) {
		return gradeDao.sumCinemaGrade(cinemaName);
	}

	/**
	 * 영화관 정렬 검색
	 */
	@Override
	public List<Cinema> orderByCinemaList(String query) {
		return gradeDao.orderByCinemaList(query);
	}

}
