package kr.co.lottemarket.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/company")
@Controller
public class CompanyController {

	@GetMapping("/")
	public String index() {
		return "/company/index";
	}
	
	
	@GetMapping("/promote")
	public String promote() {
		return "/company/promote";
	}
	
	
	@GetMapping("/notice")
	public String notice() {
		return "/company/notice";
	}
	
	
	@GetMapping("/manage")
	public String manage() {
		return "/company/manage";
	}
	
	
}
