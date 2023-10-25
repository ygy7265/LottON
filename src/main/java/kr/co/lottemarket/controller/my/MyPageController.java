package kr.co.lottemarket.controller.my;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.lottemarket.config.RootConfig;
import kr.co.lottemarket.dto.ArticleDTO;
import kr.co.lottemarket.dto.product.ProductOrderDTO;
import kr.co.lottemarket.dto.user.UserDTO;
import kr.co.lottemarket.entity.user.UserEntity;
import kr.co.lottemarket.service.mypage.MyPageQnaService;
import kr.co.lottemarket.service.mypage.myPageOrderService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/my")
@Controller
public class MyPageController {
	
	@Autowired
	private RootConfig user;
	@Autowired
	private myPageOrderService service;
	@Autowired
	private MyPageQnaService articleSerivce;
	@Autowired
	private RootConfig rootConfig;
	
	
	@GetMapping("/")
	public String index() {
		String uid = (String)user.Usersession();
		log.info("uid" + uid);
		return "/my/home";
	}
	@GetMapping("/coupon")
	public String coupon() {
		return "/my/coupon";
	}
	@GetMapping("/order")
	public String order() {
		UserDTO dto = UserDTO.builder().uid("seller1").build();

		List<Object[]> list  = service.OrderList(dto);
		log.info("list"+ list.get(0));
		return "/my/order";
	}
	@GetMapping("/info")
	public String info() {
		return "/my/info";
	}
	@GetMapping("/point")
	public String point() {
		return "/my/point";
	}
	
	@GetMapping("/qna")
	public String qna(Model model, @RequestParam(defaultValue = "1")  int pg) {
		
		String user = (String)rootConfig.Usersession();
		
		//게시글 출력
	
		
		//페이지 관련 변수
		int start=0;
		int currentPage =1;
		int total=0;
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
		
		List<ArticleDTO> qnaList = articleSerivce.selectMyQna(user,start);
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
		
		
		model.addAttribute("qnaList",qnaList);
		
		return "/my/qna";
	}
	
	
	@GetMapping("/review")
	public String review() {
		return "/my/review";
	}


}
