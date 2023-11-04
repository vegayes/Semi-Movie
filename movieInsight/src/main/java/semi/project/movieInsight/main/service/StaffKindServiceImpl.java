package semi.project.movieInsight.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.main.dao.StaffKindDAO;

@Service
public class StaffKindServiceImpl implements StaffKindService{
	
	@Autowired
	private StaffKindDAO dao;
	
	// 영화관 테이블 이름 조회
	@Override
	public List<Cinema> cinemaStaff() {
		return dao.cinemaStaff();
	}

	// 영화관 평가 테이블 조회
	@Override
	public List<Cinema> selectcinemaGrade() {
		// TODO Auto-generated method stub
		return dao.selectcinemaGrade();
	}

}
