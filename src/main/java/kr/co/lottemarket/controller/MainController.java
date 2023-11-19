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
import kr.co.lottemarket.entity.user.UserEntity;
import kr.co.lottemarket.security.MyUserDetails;
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
		 String uid = null;
	    String type = null;
		if (principal instanceof MyUserDetails) {
		    MyUserDetails userDetails = (MyUserDetails) principal;
		     uid = userDetails.getUsername();
		     type = "1";
		    // 이후 로직...
		} else {
		    // 로그인되지 않은 사용자일 때의 처리
		}

		
		
		List<ProductDTO> hit = prodService.selectsProductHit();
		List<ProductDTO> score = prodService.selectsProductScore();
		List<ProductDTO> newprod = prodService.selectsProductNew();
		List<ProductDTO> discount = prodService.selectsProductDiscount();
		List<ProductDTO> best = prodService.selectsProductSold();
		

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
