package kr.co.lottemarket.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.lottemarket.dto.TermsDTO;
import kr.co.lottemarket.dto.UserDTO;
import kr.co.lottemarket.security.MyUserDetails;
import kr.co.lottemarket.service.TermsService;
import kr.co.lottemarket.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@Controller
public class UserConrtroller {
	
	private final TermsService termsService;
	private final UserService userService;
	private MyUserDetails myUserDetails;
	
	
	@GetMapping("/member/login")
	public String login() {
		return "/member/login";
	}
	
	@GetMapping("/member/register")
	public String register() {
		
		return "/member/register";
	}
	
	@PostMapping("/member/register")
	public String register(UserDTO dto, HttpServletRequest request) { // 가입하는 그 순간, 즉 GET이 아니라 POST에서 ip 설정해준다! / register.html에서 작성한 객체가 UserDTO로 들어오고 DTO의 regip 속성에 ip값을 설정해준다.
		String ip = request.getRemoteAddr();
		dto.setRegip(ip);
		dto.setType(1);
		userService.save(dto);
		
		return "redirect:/member/login";
	}
	
	@GetMapping("/member/registerSeller")
	public String registerSeller() {
		return "/member/registerSeller";
	}
	
	@PostMapping("/member/registerSeller")
	public String registerSeller(UserDTO dto, HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		dto.setRegip(ip);
		dto.setType(2);
		userService.save(dto);
		
		return "redirect:/member/login";
	}
	
	@GetMapping("/member/join")
	public String join() {
		return "/member/join";
	}
	
	@GetMapping("/member/signup")
	public String signup(Model model, @RequestParam("type") String type) { // join.html 에서 보낸 파라미터 signup에서 수신하는 법!!!("type") 빠트리지말기
	
	TermsDTO termsDTO = termsService.findById();	
	model.addAttribute(termsDTO); //termsDTO 객체를 모델에 추가
	model.addAttribute("type",type); // (type) 이거는 하면 안됨, 파라미터 수신한 걸 모델에 추가해줘야 view 페이지에서 사용가능
	//System.out.println("type"+type);
	
		return "/member/signup";
	}
	
	// 추가
	@GetMapping("/member/findId")
	public String findId() {
		return "/member/findId";
	}
	
	
	
}