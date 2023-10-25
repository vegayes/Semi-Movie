package semi.project.movieInsight.mypage.service;

import java.util.List;
import java.util.Map;

import semi.project.movieInsight.cinema.dto.Menu;

public interface MypageService {

	Map<String, List<Menu>> selectMenu();

}
