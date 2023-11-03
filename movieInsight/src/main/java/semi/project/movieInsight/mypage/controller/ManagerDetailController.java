package semi.project.movieInsight.mypage.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.dto.Event;
import semi.project.movieInsight.cinema.dto.Menu;
import semi.project.movieInsight.cinema.dto.Promotion;
import semi.project.movieInsight.movie.dto.Movie;
import semi.project.movieInsight.mypage.service.ManagerService;

@Controller
@RequestMapping("/managerDetail")
public class ManagerDetailController {

// **** 관리자 페이지에서 추가,수정,삭제를 담당하는 컨트롤러 **********//
	
	@Autowired
	private ManagerService service;

	
	/* ********************************영화관****************************************** */
	/** 영화관 삭제 메서드
	 * @param cinemaNo
	 * @param model
	 * @return
	 */
	@GetMapping("deleteCinema/{cinemaNo:^[^0]\\d*}") // 정수 숫자만
	public String deleteCinema(@PathVariable("cinemaNo") int cinemaNo, Model model,
			RedirectAttributes ra
	) {
		
		int result = service.deleteCinema(cinemaNo);
		
		if(result == 1) {
			ra.addFlashAttribute("message","삭제에 성공했습니다");
		}else {
			ra.addFlashAttribute("message","삭제 실패");
		}
		
		return "redirect:/manager/cinema";
	}

	
	
	
	/** 관리자 페이지에서 영화관 정보를 업데이트하거나 새로 등록할 때 사용
	 * 
	 * @param cinemaInfo
	 * @param ra
	 * @param cinemaNo
	 * @param model
	 * @param cinemaImage
	 * @param session
	 * @param updateButton  버튼이 update인지, new 인지에 따라 다르게 작동
	 * @param newButton
	 * @return
	 * @throws Exception
	 */
	@PostMapping("updateCinema/{cinemaNo}")
	public String updateCinema(Cinema cinemaInfo,RedirectAttributes ra,
			@PathVariable("cinemaNo") int cinemaNo, Model model,
			@RequestParam(value = "cinemaImage", required = false) MultipartFile cinemaImage, HttpSession session,
			@RequestParam(name = "update", required = false) String updateButton,
            @RequestParam(name = "new", required = false) String  newButton
			) throws Exception {
		
		
		
		String cinemaName = cinemaInfo.getCinemaName();
		
		
		String webPath = "/resources/images/cinema/";
		String filePath = session.getServletContext().getRealPath(webPath);
		
		if(updateButton != null) {
			
			int result = service.updateCinema(cinemaImage,filePath,cinemaInfo);
			
			if(result > 0) {
				ra.addFlashAttribute("message","업데이트 성공");
			}else {
				ra.addFlashAttribute("message","업데이트 실패");
			}
			
		}else if(newButton != null){
			System.out.println("영화관등록 실행");
			int result = service.insertCinema(cinemaImage,filePath,cinemaInfo);
			
			if(result > 0) {
				ra.addFlashAttribute("message","등록 성공");
			}else {
				ra.addFlashAttribute("message","등록 실패");
			}
		}
		
		
		String encodedCinemaName = URLEncoder.encode(cinemaName, StandardCharsets.UTF_8.toString()).replace("+", " ");
		
		System.out.println("encodedCinemaName : " + encodedCinemaName);

		
		return "redirect:/cinemaDetail/" + encodedCinemaName;
	}	
	
	
	
	
	
	
/* ********************************영화****************************************** */
	
	/** 영화 삭제 메서드
	 * @param cinemaNo
	 * @param model
	 * @return
	 */
	@GetMapping("deleteMovie/{movieNo:^[^0]\\d*}") // 정수 숫자만
	public String deleteMovie(@PathVariable("movieNo") int movieNo, Model model,
			RedirectAttributes ra
	) {
		int result = service.deleteMovie(movieNo);
		if(result == 1) {
			
			ra.addFlashAttribute("message","삭제에 성공했습니다");
		}
		
		return "redirect:/manager/movie";
	}
	
	
	
	/** 영화 새로 등록하는 매서드
	 * @param movieInfo
	 * @param ra
	 * @param cinemaImage
	 * @param session
	 * @return
	 */
	@PostMapping("insertMovie")
	public String insertMovie(Movie movieInfo,RedirectAttributes ra,
			@RequestParam(value = "movieImage", required = false) MultipartFile movieImage, HttpSession session
			) throws Exception{
		
		System.out.println("insert 컨트롤러로 전달된 movieInfo : " + movieInfo);
		String[] actorNamesArray = movieInfo.getActorNames().split("/");
		String[] directorNamesArray = movieInfo.getDirectorNames().split("/");
		List<String> actorNamesList = new ArrayList<>(Arrays.asList(actorNamesArray));
		List<String> directorNamesList = new ArrayList<>(Arrays.asList(directorNamesArray));
		
		int movieNo = 0;
		String webPath = "/resources/images/movie/";
		String filePath = session.getServletContext().getRealPath(webPath);
		
		int result = service.insertMovie(movieInfo,movieImage,filePath,actorNamesList,directorNamesList);
		
		if(result > 0) {
			ra.addFlashAttribute("message","등록 성공");
			movieNo = service.selectMovieNo(movieInfo.getMovieTitle());
			return "redirect:/manager/movie";
		}else {
			ra.addFlashAttribute("message","등록 실패");
			return "redirect:/movie/0";
		}
	
	
	}
	
	
	@PostMapping("updateMovie/{movieNo:^[^0]\\d*}")
	public String updateMovie(Movie movieInfo,RedirectAttributes ra,
			@RequestParam(value = "movieImage", required = false) MultipartFile movieImage, HttpSession session,
			@PathVariable("movieNo") int movieNo
			) throws Exception{
		
		movieInfo.setMovieNo(movieNo);
		System.out.println("수정하려는 정보 : " + movieInfo);
		
		
		int result = service.updateMovie(movieInfo);
		
		if(result > 0) {
			ra.addFlashAttribute("message","등록 성공");
		}else {
			ra.addFlashAttribute("message","등록 실패");
		}
		
		return "redirect:/manager/movie";
	}
	
	
	
	/* ********************************이벤트****************************************** */
	
	@PostMapping("insertPromotion")
	public String insertPromotion(RedirectAttributes ra, HttpSession session,
			@RequestParam(value = "img", required = false) MultipartFile Image,
			@RequestParam("title") String title,
			@RequestParam("url") String url,
			@RequestParam("content") String content,
			@RequestParam("cinemaNoList") List<Integer> cinemaNoList
			
			) throws Exception{
		
		Promotion promotion  = new Promotion();
		promotion.setPromotionType(title);
		promotion.setPromotionURL(url);
		promotion.setPromotionContent(content);
		promotion.setPromotionImg(Image.getOriginalFilename());
		
		Map<String,Object> promotionMap = new HashMap<String, Object>();
		promotionMap.put("promotion", promotion);
		promotionMap.put("cinemaNoList", cinemaNoList);
		
		String webPath = "/resources/images/cinema/";
		String filePath = session.getServletContext().getRealPath(webPath);
		
		
		int result = service.insertPromotion(Image,filePath,promotionMap);
		
		if(result > 0) {
			ra.addFlashAttribute("message","등록 성공");
		}else {
			ra.addFlashAttribute("message","등록 실패");
		}

		return "redirect:/manager/promotion";
	}
	
		
	@PostMapping("insertEvent")
	public String insertEvent(RedirectAttributes ra, HttpSession session,
			@RequestParam(value = "img", required = false) MultipartFile Image,
			@RequestParam("title") String title,
			@RequestParam("url") String url,
			@RequestParam("content") String content,
			@RequestParam("cinemaNoList") List<Integer> cinemaNoList
			) throws Exception{
		
		Event event  = new Event();
		event.setEventTitle(title);
		event.setEventURL(url);
		event.setEventContent(content);
		event.setEventImg(Image.getOriginalFilename());
		
		System.out.println("cinemaNoList이벤트 : " + cinemaNoList);
		Map<String,Object> eventMap = new HashMap<String, Object>();
		eventMap.put("event", event);
		eventMap.put("cinemaNoList", cinemaNoList);
		
		String webPath = "/resources/images/cinema/";
		String filePath = session.getServletContext().getRealPath(webPath);
		
		int result = service.insertEvent(eventMap,Image,filePath);
		
		if(result > 0) {
			ra.addFlashAttribute("message","등록 성공");
		}else {
			ra.addFlashAttribute("message","등록 실패");
		}

		return "redirect:/manager/promotion";
	}
	
	
	@GetMapping("deleteEvent/{eventPRNo:^[^0]\\d*}") // 정수 숫자만
	public String deleteEvent (@PathVariable("eventPRNo") int eventPRNo, Model model,
			RedirectAttributes ra) {
		
		int result = service.deleteEvent(eventPRNo);
		if(result > 0) {
			ra.addFlashAttribute("message","삭제에 성공했습니다");
		}else {
			ra.addFlashAttribute("message","삭제에 실패했습니다");
		}
		
		return "redirect:/manager/promotion";
		
	}
	
	
	@GetMapping("deletePromotion/{promotionNo:^[^0]\\d*}") // 정수 숫자만
	public String deletePromotion (@PathVariable("promotionNo") int promotionNo, Model model,
			RedirectAttributes ra) {
		
		int result = service.deletePromotion(promotionNo);
		if(result > 0) {
			ra.addFlashAttribute("message","삭제에 성공했습니다");
		}else {
			ra.addFlashAttribute("message","삭제에 실패했습니다");
		}
		
		return "redirect:/manager/promotion";
		
	}
	
	
	
	
	/* ********************************메뉴****************************************** */
	
	@PostMapping("insertMenu")
	public String insertMenu(RedirectAttributes ra, HttpSession session,
			@RequestParam(value = "img", required = false) MultipartFile Image,
			@RequestParam("cinemaNoList") List<Integer> cinemaNoList,
			Menu menu 
			) throws Exception{
		
			menu.setMenuImg(Image.getOriginalFilename());

			Map<String,Object> menuMap = new HashMap<String, Object>();
			menuMap.put("menu", menu);
			menuMap.put("cinemaNoList", cinemaNoList);
			
			
			String webPath = "/resources/images/menu/"+menu.getMenuCategory();
			String filePath = session.getServletContext().getRealPath(webPath);
			
			System.out.println("webPath : " + webPath);
			System.out.println("filePath : " + filePath);
			
			int result = service.insertMenu(menuMap,Image,filePath);
			
			if(result > 0) {
				ra.addFlashAttribute("message","등록 성공");
			}else {
				ra.addFlashAttribute("message","등록 실패");
			}
		
			
		
			return "redirect:/manager/menu";
	}
	
	
	
	@GetMapping("deleteMenu/{menuCategory}/{menuNo}")
	public String deleteMenu(@PathVariable String menuCategory, @PathVariable int menuNo,
			RedirectAttributes ra ) {
	   
		
		int result = 1;
		
		if(result > 0) {
			ra.addFlashAttribute("message","등록 성공");
		}else {
			ra.addFlashAttribute("message","등록 실패");
		}
		
		
		return "redirect:/manager/menu";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
