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

	@Override
	public List<Cinema> cinemaStaff() {
		// TODO Auto-generated method stub
		return dao.cinemaStaff();
	}

}
