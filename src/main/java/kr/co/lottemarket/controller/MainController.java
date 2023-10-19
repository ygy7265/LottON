package kr.co.lottemarket.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.lottemarket.dto.product.ProductDTO;
import kr.co.lottemarket.entity.UserEntity;
import kr.co.lottemarket.service.product.ProductService;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Controller
public class MainController {

	
	@Autowired
	private ProductService prodService;
	@GetMapping(value= {"/","/index"})
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
		
		log.info("uid : " + uid);
		log.info("type : " + type);
		
		
		List<ProductDTO> hit = prodService.selectsProductHit();
		List<ProductDTO> score = prodService.selectsProductScore();
		List<ProductDTO> newprod = prodService.selectsProductNew();
		List<ProductDTO> discount = prodService.selectsProductDiscount();
		List<ProductDTO> best = prodService.selectsProductSold();
		
		log.info("hit hit..." + hit.toString());
		model.addAttribute("hit",hit);
		model.addAttribute("score",score);
		model.addAttribute("newprod",newprod);
		model.addAttribute("discount",discount);
		model.addAttribute("best",best);
		
		// 사용자 정보
		model.addAttribute("uid",uid);
		model.addAttribute("type",type);
		
		
		return "/index";
	}
}
