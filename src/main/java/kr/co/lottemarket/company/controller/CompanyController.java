package kr.co.lottemarket.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.lottemarket.company.service.CompanyService;
import kr.co.lottemarket.cs.mapper.ArticleMapper;
import kr.co.lottemarket.dto.ArticleDTO;
import lombok.RequiredArgsConstructor;

@RequestMapping("/company")
@RequiredArgsConstructor
@Controller
public class CompanyController {

	@Autowired
	private CompanyService articleSerivce;
	
	@GetMapping("/")
	public String index() {
		
		return "/company/index";
	}
	
	
	@GetMapping("/promote")
	public String promote() {
		return "/company/promote";
	}
	
	
	@GetMapping("/notice")
	public String notice(Model model) {
		
		List<ArticleDTO> articles = articleSerivce.selectNoticeArticles();
		
		model.addAttribute("articles", articles);
		
		return "/company/notice";
	}
	
	
	@GetMapping("/manage")
	public String manage() {
		return "/company/manage";
	}
	
	
}
