package kr.co.lottemarket.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserConrtroller {
	
	@GetMapping("/member/login")
	public String login() {
		return "/member/login";
	}
	
	@GetMapping("/member/register")
	public String register() {
		return "/member/register";
	}
	
}
