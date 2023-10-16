package kr.co.lottemarket.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Admin_MainController {
	
	@GetMapping("/admin/layout/index/layoutindex")
	public String index() {
		return "/admin/layout/index/layoutindex";
	}
	
}
