package kr.co.lottemarket.cs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.lottemarket.dto.ArticleDTO;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class CS_MainController {


	@Autowired
	private CsSerivce articleSerivce;
	
	@GetMapping(value = { "/cs", "/cs/index"})
	public String index(Model model) {
		
		List<ArticleDTO> noticeArticles = articleSerivce.selectIndex(1);
		List<ArticleDTO> qnaArticles = articleSerivce.selectIndex(3);
		
		model.addAttribute("noticeArticles", noticeArticles);
		model.addAttribute("qnaArticles", qnaArticles);

		
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
		
		// 사용자 정보
		model.addAttribute("uid",uid);
		model.addAttribute("type",type);
		
		
		return "/cs/index";
	}
}
