package kr.co.lottemarket.controller.admin;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Admin_MainController {
	
	@GetMapping("/admin/layout/index/layoutindex")
	public String index(Model model) {
		
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

		// 사용자 정보
		model.addAttribute("uid",uid);
		model.addAttribute("type",type);
		
		return "/admin/layout/index/layoutindex";
	}
	
}
