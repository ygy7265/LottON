package kr.co.lottemarket.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.lottemarket.dto.user.TermsDTO;
import kr.co.lottemarket.dto.user.UserDTO;
import kr.co.lottemarket.security.MyUserDetails;
import kr.co.lottemarket.service.user.TermsService;
import kr.co.lottemarket.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Controller
public class UserConrtroller {
	
	private final TermsService termsService;
	private final UserService userService;
	private MyUserDetails myUserDetails;
	
	
	@GetMapping("/member/login")
	public String login() {
		return "/member/layout/login";
	}
	
	@GetMapping("/member/logout")
	public String logout() {
		return "redirect:/member/login";
	}
	
	
	
	@GetMapping("/member/register")
	public String register() {
		
		return "/member/layout/register";
	}
	
	@PostMapping("/member/register")
	public String register(UserDTO dto, HttpServletRequest request) { // 가입하는 그 순간, 즉 GET이 아니라 POST에서 ip 설정해준다! / register.html에서 작성한 객체가 UserDTO로 들어오고 DTO의 regip 속성에 ip값을 설정해준다.
		String ip = request.getRemoteAddr();
		dto.setRegip(ip);
		dto.setType(1);
		userService.save(dto);
		
		return "redirect:/member/login"; // redirect는 html을 보여주는 게 아니라 url mapping 으로 전송이다!
	}
	
	@GetMapping("/member/registerSeller")
	public String registerSeller() {
		return "/member/layout/registerSeller";
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
		return "/member/layout/join";
	}
	
	@GetMapping("/member/signup")
	public String signup(Model model, @RequestParam("type") String type) { // join.html 에서 보낸 파라미터 signup에서 수신하는 법!!!("type") 빠트리지말기
	
	TermsDTO termsDTO = termsService.findById();	
	model.addAttribute(termsDTO); //termsDTO 객체를 모델에 추가
	model.addAttribute("type",type); // (type) 이거는 하면 안됨, 파라미터 수신한 걸 모델에 추가해줘야 view 페이지에서 사용가능
	//System.out.println("type"+type);
	
		return "/member/layout/signup";
	}
	
	// 아이디 찾기
	@GetMapping("/member/findId")
	public String findId() {
		return "/member/layout/findId";
	}
	
	@GetMapping("/member/findIdResult") 
	public String findIdResult(Model model, @RequestParam String name, @RequestParam String email) {
		
		UserDTO userDTO = userService.findByNameAndEmail(name, email);
		model.addAttribute(userDTO); // 근데 여기 컨트롤러에서 추가했던 모델 속성들이 전부 프론트에서 출력할 수 있는건가?? ㄴㄴ : 모델속성은 GET메서드안에서 지정하고 해당 페이지에서 참조 가능 / 예를들면 userDTO 나 termsDTO나
		log.info("userDTO : " + userDTO);
		
		return "/member/layout/findIdResult";
	}
	
	// 비밀번호 찾기
	@GetMapping("/member/findPass")
	public String findPass() {
		return "/member/layout/findPass";
	}
	
	@GetMapping("/member/findPassChange")
	public String findPassChange(Model model, @RequestParam String uid, @RequestParam String email) {
		
		UserDTO userDTO = userService.findByUidAndEmail(uid, email);
		model.addAttribute(userDTO);
		return "/member/layout/findPassChange";
	}
	
	/*@PostMapping("/member/findPassChange")
	public String findPassChange(UserDTO dto) {
		
		
		
		userService.updatePass(dto);
		
	}
	*/
	
}