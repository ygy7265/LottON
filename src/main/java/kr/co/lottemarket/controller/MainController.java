package kr.co.lottemarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.lottemarket.dto.product.ProductDTO;
import kr.co.lottemarket.service.product.ProductService;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Controller
public class MainController {

	
	@Autowired
	private ProductService prodService;
	@GetMapping(value= {"/","/index"})
	public String index(Model model) {
		
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
		
		
		return "/index";
	}
}
