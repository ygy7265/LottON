package kr.co.lottemarket.controller.my;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.lottemarket.config.RootConfig;
import kr.co.lottemarket.dto.product.OrderPageRequestDTO;
import kr.co.lottemarket.dto.product.OrderPageResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.lottemarket.dto.ArticleDTO;
import kr.co.lottemarket.dto.product.ProductOrderDTO;
import kr.co.lottemarket.dto.product.ProductPointDTO;
import kr.co.lottemarket.dto.product.ProductReviewDTO;
import kr.co.lottemarket.dto.product.ReviewPageRequestDTO;
import kr.co.lottemarket.dto.product.ReviewPageResponseDTO;
import kr.co.lottemarket.dto.user.UserDTO;
import kr.co.lottemarket.entity.product.ProductReviewEntity;
import kr.co.lottemarket.entity.user.UserEntity;
import kr.co.lottemarket.security.MyUserDetails;
import kr.co.lottemarket.service.mypage.MyPageQnaService;
import kr.co.lottemarket.service.mypage.MyPageOrderService;
import kr.co.lottemarket.service.mypage.MyPageOrderService.dataDTO;
import kr.co.lottemarket.service.mypage.MyPagePointService;
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
	private final MyPagePointService pointService;
	private final RootConfig user;
	private final MyPageOrderService service;
	private final MyPageQnaService articleSerivce;
	private final RootConfig rootConfig;
	private final UserService userService;
	
	int total=0;
	
	@GetMapping("/")
	public String index(Model model) {
		MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uid = userDetails.getUsername();
		UserDTO userDTO = new UserDTO();
		userDTO.setUid(uid);
		List<ProductOrderDTO> dto = service.lastOrder(userDTO);
		List<ProductReviewDTO> dtoReview = reviewService.reviewFindTop5();
		List<ProductPointDTO> dtoPoint = pointService.pointFindTop5();
		
		dataDTO datadto = service.countProduct();
		model.addAttribute("point",datadto.getPoint());
		model.addAttribute("count",datadto.getCount());
		model.addAttribute("dto",dto);
		model.addAttribute("dtoReview",dtoReview);
		model.addAttribute("dtoPoint",dtoPoint);
		String user = (String)rootConfig.Usersession();
		total = articleSerivce.selectMyCountTotal(user);
		model.addAttribute("total", total);
		
		List<ArticleDTO> qnaList = articleSerivce.selectMyQna(user, 0, 3);
		model.addAttribute("qnaList",qnaList);
		return "/my/home";
	}
	@GetMapping("/coupon")
	public String coupon(Model model) {

		dataDTO datadto = service.countProduct();
		model.addAttribute("point",datadto.getPoint());
		model.addAttribute("count",datadto.getCount());
		String user = (String)rootConfig.Usersession();
		total = articleSerivce.selectMyCountTotal(user);
		model.addAttribute("total", total);
    
		return "/my/coupon";
	}
	@GetMapping("/order")
	public String order(Model model,OrderPageRequestDTO pageRequestDTO,HttpServletRequest request) {
    
		dataDTO datadto = service.countProduct();
		String begin = request.getParameter("begin");
		String end = request.getParameter("end");
		OrderPageResponseDTO dto = null;
	
		dto =service.OrderList(pageRequestDTO, begin, end);
		
		MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uid = userDetails.getUsername();
		total = articleSerivce.selectMyCountTotal(uid);
    
		model.addAttribute("dtoList",dto);
		model.addAttribute("point",datadto.getPoint());
		model.addAttribute("count",datadto.getCount());
		model.addAttribute("total", total);
		
		return "/my/order";
	}
	
	// info
	@GetMapping("/info")
	public String info(Model model,HttpServletRequest request) {
		MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uid = userDetails.getUsername();
		dataDTO datadto = service.countProduct();

		Object obj = rootConfig.Usersession();		
		// Object 형변환 가능 여부 확인
		if(obj instanceof String) {
			uid = (String) obj;
			//log.info("/my/info uid : " + uid);
		}else {
			//log.info("error 1...");
		}
		
		UserDTO userDTO = userService.findByUid(uid);

		String user = (String)rootConfig.Usersession();
		total = articleSerivce.selectMyCountTotal(user);
    
		model.addAttribute("point",datadto.getPoint());
		model.addAttribute("count",datadto.getCount());
		model.addAttribute(userDTO);
		model.addAttribute("total", total);
		
		return "/my/info";
	}
	
	@GetMapping("/infoPassCheck")
	public String infoPassCheck(
		Model model, 
		@RequestParam String email,  // 나의설정에서 수정된데이터 수신하기
		@RequestParam String hp, 
		@RequestParam String zip, 
		@RequestParam String addr1, 
		@RequestParam String addr2)	{
		
		Object obj = rootConfig.Usersession();
		String uid = "null";
		
		// Object 형변환 가능 여부 확인
		if(obj instanceof String) {
			uid = (String) obj;
			//log.info("/my/info uid : " + uid);
		}else {
			//log.info("error 1...");
		}
		
		log.info("uid : " + uid);
		log.info("email : " + email);
		log.info("hp : " + hp);
		log.info("zip : " + zip);
		log.info("addr1 : " + addr1);
		log.info("addr2 : " + addr2);
		
		// 나의설정에서 수정된데이터 DTO객체 속성에 저장하기
		UserDTO userDTO = userService.findByUid(uid);
		userDTO.setEmail(email);
		userDTO.setHp(hp);
		userDTO.setZip(zip);
		userDTO.setAddr1(addr1);
		userDTO.setAddr2(addr2);
		
		model.addAttribute(userDTO);
		
		return "/my/infoPassCheck";
	}
	
	// 비밀번호 확인페이지에서 폼 전송으로 수정할 때
	@PostMapping("/infoPassCheck") // 비밀번호 일치하는지 안하는지 여기로 AJAX POST 전송해서 확인후 일치하면(성공값) 그때 다시 폼을 통해 여기로 POST 전송 해서 save한다!
	public String infoPassCheck(UserDTO user) { // 폼에서 파라미터 보내는 거 DTO로 수신
		
		// 우선 uid로 DB에 입력되어 정보 가져오기
		UserDTO userDTO = userService.findByUid(user.getUid());
		
		// 가져온 정보에 파라미터로 받은 데이터 설정하기
		userDTO.setEmail(user.getEmail());
		userDTO.setHp(user.getHp());
		userDTO.setZip(user.getZip());
		userDTO.setAddr1(user.getAddr1());
		userDTO.setAddr2(user.getAddr2());
		
		// 수정된 데이터로 save하기
		userService.modify(userDTO);
		
		return "redirect:/member/logout";
	}
	
	// 비밀번호 확인할 때 AJAX로 쏜 거 이 메서드로 확인
	@ResponseBody
	@PostMapping("/infoPassConfirm") // 비밀번호 일치하는지 안하는지 여기로 AJAX POST 전송해서 확인후 일치하면(성공값) 그때 다시 폼을 통해 여기로 POST 전송 해서 save한다!
	public int infoPassConfirm(String uid, String pass) { // 매개변수 DTO로도 받을 수 있다! 근데 AJAX로 쏠때랑 폼전송할 때랑 한번에 받을 수 있는 매개변수는 DTO겠네??
		
		log.info("uid : " + uid);
		log.info("pass : " + pass);

		int result = userService.findByUidAndPass(uid, pass);
		
		return result;
		/*
		
		
		// 비밀번호확인 페이지로부터 수정데이터 수신해서 DTO 객체의 속성에 저장
		userDTO.setEmail(email);
		userDTO.setHp(hp);
		userDTO.setZip(zip);
		userDTO.setAddr1(addr1);
		userDTO.setAddr2(addr2);
		
		if(pass.equals(userDTO.getPass1())) {
			
			log.info("here...1");
			
			userService.save(userDTO);
			
			return "redirect:/my/";
		}else {
			
			log.info("here...2");
			
			// 비밀번호가 일치하지 않을 때 알림을 추가
			redirectAttributes.addFlashAttribute("error", "비밀번호가 일치하지 않습니다.");
			
			return "redirect:/my/infoPassCheck";
		}
		*/
		
	}
	
	@GetMapping("/qna")
	public String qna(Model model, @RequestParam(defaultValue = "1")  int pg,HttpServletRequest request) {
		dataDTO datadto = service.countProduct();
		model.addAttribute("point",datadto.getPoint());
		model.addAttribute("count",datadto.getCount());
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
		
		if(pageGroupEnd < 1) {
			pageGroupEnd = 1;
		}
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
		

		dataDTO datadto = service.countProduct();
		String user = (String)rootConfig.Usersession();
		total = articleSerivce.selectMyCountTotal(user);
    
		model.addAttribute("total", total);
		model.addAttribute("list",list);
		model.addAttribute("point",datadto.getPoint());
		model.addAttribute("count",datadto.getCount());
		return "/my/review";
	}

	@PostMapping("/review")
	public String review(ProductReviewDTO dto,@Param("ordCompleteNo") int ordCompleteNo,Model model,HttpServletRequest request) {
		dataDTO datadto = service.countProduct();

		dto.setRegip(request.getRemoteAddr());
		reviewService.reviewSave(dto,ordCompleteNo);
		
		MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uid = userDetails.getUsername();
		total = articleSerivce.selectMyCountTotal(uid);
    
		model.addAttribute("point",datadto.getPoint());
		model.addAttribute("count",datadto.getCount());
		model.addAttribute("total", total);
		
		return "redirect:/my/";
	}
	

}
