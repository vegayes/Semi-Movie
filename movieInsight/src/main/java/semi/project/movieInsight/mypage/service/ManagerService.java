package semi.project.movieInsight.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.dto.Menu;
import semi.project.movieInsight.movie.dto.Movie;

public interface ManagerService {
	
	/** 1) 모든 홍보정보 조회
	 * @return
	 */
	Map<String, Object> selectPromotion();

	/**2) 모든 회원정보 조회 
	 * @return
	 */
	Map<String, Object> selectMember();

	
	/** 모든 메뉴 조회
	 * @return
	 */
	Map<String, List<Menu>> selectMenu();

	
	
	/** 영화관 삭제
	 * @param cinemaNo
	 * @return
	 */
	int deleteCinema(int cinemaNo);

	
	/** 영화 삭제
	 * @param movieNo
	 * @return
	 */
	int deleteMovie(int movieNo);

	
	/** 영화관 업데이트
	 * @param cinemaImg
	 * @param webPath
	 * @param filePath
	 * @param cinemaInfo
	 * @return
	 * @throws Exception 
	 */
	int updateCinema(MultipartFile cinemaImg, String filePath, Cinema cinemaInfo) throws Exception;


	int insertCinema(MultipartFile cinemaImage, String filePath, Cinema cinemaInfo) throws Exception;

	int insertMovie(Movie movieInfo, MultipartFile movieImage, String filePath, List<String> actorNamesList, List<String> directorNamesList) throws Exception ;

	int selectMovieNo(String movieTitle);

	
	
	
}
