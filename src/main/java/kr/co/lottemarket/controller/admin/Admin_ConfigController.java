package kr.co.lottemarket.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.transaction.Transactional;
import kr.co.lottemarket.dto.user.TermsDTO;
import kr.co.lottemarket.service.admin.AdminService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class Admin_ConfigController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/admin/layout/config/info")
	public String info(Model model) {
		
		List<TermsDTO> termslist = adminService.selectPolicy();
		
		model.addAttribute("termslist", termslist);
		
		return "/admin/layout/config/info";
	}
	
	@GetMapping("/admin/layout/config/banner")
	public String banner() {
		
		return "/admin/layout/config/banner";
	}
	
}
