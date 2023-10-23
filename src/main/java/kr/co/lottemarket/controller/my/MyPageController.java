package kr.co.lottemarket.controller.my;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/my")
@Controller
public class MyPageController {
	
	@GetMapping("/")
	public String index() {
		log.info(userInfo());
		return "/my/home";
	}
	@GetMapping("/coupon")
	public String coupon() {
		log.info(userInfo());
		return "/my/coupon";
	}
	@GetMapping("/order")
	public String order() {
		log.info(userInfo());
		return "/my/order";
	}
	@GetMapping("/info")
	public String info() {
		log.info(userInfo());
		return "/my/info";
	}
	@GetMapping("/point")
	public String point() {
		log.info(userInfo());
		return "/my/point";
	}
	@GetMapping("/qna")
	public String qna() {
		log.info(userInfo());
		return "/my/qna";
	}
	@GetMapping("/review")
	public String review() {
		log.info(userInfo());
		return "/my/review";
	}
	
	public Object userInfo() {
		// 현재 사용자의 정보 가져오기 
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				
				// 변수 선언 및 초기화
				String uid = null;
				String type = null;
				
				if(principal instanceof UserDetails) {
					
					// uid는 현재 인증된 사용자의 아이디
					uid = ((UserDetails) principal).getUsername();
					List<GrantedAuthority> authorities = (List<GrantedAuthority>) ((UserDetails) principal).getAuthorities();
					
					GrantedAuthority authority = authorities.get(0);
					
					type = authority.getAuthority().substring(5);
				}
				
				log.info("uid : " + uid);
				log.info("type : " + type);
				
				return uid;
	}

}
