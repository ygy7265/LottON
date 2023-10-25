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

import kr.co.lottemarket.config.RootConfig;
import kr.co.lottemarket.dto.ArticleDTO;
import kr.co.lottemarket.dto.product.ProductOrderDTO;
import kr.co.lottemarket.dto.user.UserDTO;
import kr.co.lottemarket.entity.user.UserEntity;
import kr.co.lottemarket.service.mypage.MyPageQnaService;
import kr.co.lottemarket.service.mypage.myPageOrderService;
import kr.co.lottemarket.service.user.UserService;
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
	@Autowired
	private UserService userService;
	
	
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
	public String info(Model model) {
		
		Object obj = rootConfig.Usersession();
		String uid = null;
		
		// Object 형변환 가능 여부 확인
		if(obj instanceof String) {
			uid = (String) obj;
			//log.info("/my/info uid : " + uid);
		}else {
			//log.info("error 1...");
		}
		
		UserDTO userDTO = userService.findByUid(uid);
		model.addAttribute(userDTO);
		return "/my/info";
	}
	
	@GetMapping("/point")
	public String point() {
		return "/my/point";
	}
	
	@GetMapping("/qna")
	public String qna(Model model, String uid) {
		
		Object user = rootConfig.Usersession();
		
		List<ArticleDTO> qnaList = articleSerivce.selectMyQna("seller1");
		
		log.info("qnaList = " + qnaList);
		
		model.addAttribute("qnaList",qnaList);
		
		return "/my/qna";
	}
	
	
	@GetMapping("/review")
	public String review() {
		return "/my/review";
	}


}
