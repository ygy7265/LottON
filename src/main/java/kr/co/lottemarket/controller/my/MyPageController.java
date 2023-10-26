package kr.co.lottemarket.controller.my;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.lottemarket.config.RootConfig;
import kr.co.lottemarket.dto.product.OrderPageRequestDTO;
import kr.co.lottemarket.dto.product.OrderPageResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.lottemarket.dto.ArticleDTO;
import kr.co.lottemarket.dto.product.ProductOrderDTO;
import kr.co.lottemarket.dto.product.ProductReviewDTO;
import kr.co.lottemarket.dto.product.ReviewPageRequestDTO;
import kr.co.lottemarket.dto.product.ReviewPageResponseDTO;
import kr.co.lottemarket.dto.user.UserDTO;
import kr.co.lottemarket.entity.product.ProductReviewEntity;
import kr.co.lottemarket.entity.user.UserEntity;
import kr.co.lottemarket.service.mypage.MyPageQnaService;
import kr.co.lottemarket.service.mypage.MyPageOrderService;
import kr.co.lottemarket.service.mypage.MyPageReviewService;
import lombok.RequiredArgsConstructor;
import kr.co.lottemarket.service.user.UserService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/my")
@RequiredArgsConstructor
@Controller
public class MyPageController {
	
	private final MyPageReviewService reviewService;
	private final RootConfig user;
	private final MyPageOrderService service;
	private final MyPageQnaService articleSerivce;
	private final RootConfig rootConfig;
	private final UserService userService;
	
	int total=0;
	
	@GetMapping("/")
	public String index(Model model) {
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUid("seller1");
		List<ProductOrderDTO> dto = service.lastOrder(userDTO);
		model.addAttribute("dto",dto);
		
		String user = (String)rootConfig.Usersession();
		// 전체 상품 갯수
		total = articleSerivce.selectMyCountTotal(user);
		model.addAttribute("total", total);
		
		List<ArticleDTO> qnaList = articleSerivce.selectMyQna(user, 0, 3);
		model.addAttribute("qnaList",qnaList);
		
		return "/my/home";
	}
	@GetMapping("/coupon")
	public String coupon(Model model) {
		
		String user = (String)rootConfig.Usersession();
		// 전체 상품 갯수
		total = articleSerivce.selectMyCountTotal(user);
		model.addAttribute("total", total);
		return "/my/coupon";
	}
	@GetMapping("/order")
	public String order(Model model,OrderPageRequestDTO pageRequestDTO) {
		OrderPageResponseDTO dto =service.OrderList(pageRequestDTO);
		model.addAttribute("dtoList",dto);
		
		String user = (String)rootConfig.Usersession();
		// 전체 상품 갯수
		total = articleSerivce.selectMyCountTotal(user);
		model.addAttribute("total", total);
		
		return "/my/order";
	}
	
	@GetMapping("/info")
	public String info(Model model) {
		
		Object obj = rootConfig.Usersession();
		String uid = "seller1";
		
		// Object 형변환 가능 여부 확인
		if(obj instanceof String) {
			uid = (String) obj;
			//log.info("/my/info uid : " + uid);
		}else {
			//log.info("error 1...");
		}
		
		UserDTO userDTO = userService.findByUid(uid);
		model.addAttribute(userDTO);
		
		String user = (String)rootConfig.Usersession();
		// 전체 상품 갯수
		total = articleSerivce.selectMyCountTotal(user);
		model.addAttribute("total", total);
		
		return "/my/info";
	}
	@GetMapping("/qna")
	public String qna(Model model, @RequestParam(defaultValue = "1")  int pg) {
		
		String user = (String)rootConfig.Usersession();
		
		//게시글 출력
	
		
		//페이지 관련 변수
		int start=0;
		int currentPage =1;
		int lastPageNum=0;
		int pageGroupCurrent=1;
		int pageGroupStart=1;
		int pageGroupEnd=0;
		int pageStartNum=0;
		
		
		// 현재페이지계산
		currentPage = pg;
		
		// 전체 상품 갯수
		total = articleSerivce.selectMyCountTotal(user);
		
		log.info("total = " + total);
		
		//LIMIT 시작값계산
		start =(currentPage -1)*10;

		if(total%10 == 0){
			lastPageNum =(total/10);
		}else{
			lastPageNum =(total/10)+1;
		}
		
		//페이지 그룹계산
		// 페이지 그룹 계산 (5개 단위로 나누기)
		pageGroupCurrent = (int) Math.ceil(currentPage / 5.0); // 현재 페이지 그룹 계산
		pageGroupStart = (pageGroupCurrent - 1) * 5 + 1; // 페이지 그룹의 시작 페이지 계산
		pageGroupEnd = Math.min(pageGroupCurrent * 5, lastPageNum); // 페이지 그룹의 끝 페이지 계산
		
		if(pageGroupEnd > lastPageNum){
			pageGroupEnd=lastPageNum;
		}
		
		//페이지 시작번호 계산
		pageStartNum = total-start;
		
		List<ArticleDTO> qnaList = articleSerivce.selectMyQna(user,start, 10);
		
		model.addAttribute("qnaList",qnaList);
		model.addAttribute("start", start);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("total", total);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("pageGroupCurrent", pageGroupCurrent);
		model.addAttribute("pageGroupStart", pageGroupStart);
		model.addAttribute("pageGroupEnd", pageGroupEnd);
		model.addAttribute("pageStartNum", pageStartNum);
		
		
		log.info("start = "+ start);
		log.info("currentPage = " + currentPage);
		log.info("total = " +  total);
		log.info("lastPageNum = " + lastPageNum);
		log.info("pageGroupStart = " + pageGroupStart);
		log.info("pageGroupEnd = " + pageGroupEnd);
		log.info("pageStartNum = " + pageStartNum);
		log.info("qnaList = " + qnaList);
		
	
		
		return "/my/qna";
	}
	
	
	@GetMapping("/review")
	public String reviewGet(Model model,ReviewPageRequestDTO dto) {
		ReviewPageResponseDTO list = reviewService.reviewFind(dto);
		
		model.addAttribute("list",list);
		String user = (String)rootConfig.Usersession();
		// 전체 상품 갯수
		total = articleSerivce.selectMyCountTotal(user);
		model.addAttribute("total", total);
		return "/my/review";
		
	}
	
	@PostMapping("/review")
	public String review(ProductReviewDTO dto,@Param("ordCompleteNo") int ordCompleteNo,HttpServletRequest request, Model model) {
	
		dto.setRegip(request.getRemoteAddr());
		reviewService.reviewSave(dto,ordCompleteNo);
		
		String user = (String)rootConfig.Usersession();
		// 전체 상품 갯수
		total = articleSerivce.selectMyCountTotal(user);
		model.addAttribute("total", total);
		
		return "redirect:/my/";
	}


}
