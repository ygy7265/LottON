package kr.co.lottemarket.controller.policy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.lottemarket.dto.user.TermsDTO;
import kr.co.lottemarket.service.admin.AdminService;

@Controller
public class PolicyController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/policy/sellerpolicy")
	public String seller(Model model) {
		
		List<TermsDTO> sellerPolicy = adminService.selectPolicy();
		
		model.addAttribute("sellerPolicy", sellerPolicy);
		
		return "/policy/sellerpolicy";
	}
	
	@GetMapping("/policy/buyer")
	public String buyer(Model model) {
		
		List<TermsDTO> buyerPolicy = adminService.selectPolicy();
		
		model.addAttribute("buyerPolicy", buyerPolicy);
		
		return "/policy/buyer";
	}
	
	@GetMapping("/policy/location")
	public String location(Model model) {
		
		List<TermsDTO> locationPolicy = adminService.selectPolicy();
		
		model.addAttribute("locationPolicy", locationPolicy);
		
		return "/policy/location";
	}
	
	@GetMapping("/policy/finance")
	public String finance(Model model) {
		
		List<TermsDTO> financePolicy = adminService.selectPolicy();
		
		model.addAttribute("financePolicy", financePolicy);
		
		return "/policy/finance";
	}
	
	@GetMapping("/policy/privacy")
	public String privacy(Model model) {
		
		List<TermsDTO> privacyPolicy = adminService.selectPolicy();
		
		model.addAttribute("privacyPolicy", privacyPolicy);
		
		return "/policy/privacy";
	}
	
}
