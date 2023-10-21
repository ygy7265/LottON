package kr.co.lottemarket.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.lottemarket.service.user.UserService;
import lombok.extern.log4j.Log4j2;

@Log4j2  
@RestController // @ResponseBody(JSON 데이터 반환 시 필요한 어노테이션) 메서드마다 써주는 대신 이 어노테이션 하나만 있으면 됨
@RequestMapping("/member/check") // 해당 클래스의 모든  메서드에 적용되는 기본 URL 매핑 정의
public class UserCheckController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("/uid/{uid}") // URL은 "/member/check/uid" 이렇게 된다
	public int checkUid(@PathVariable("uid") String uid) {
		log.info("uid : " + uid);
		return userService.countUid(uid); // jsp에서처럼 result를 JSON 속성으로 설정해서 보내지 않고 JSON data 그대로 반환하는건가??
	}
	
	@GetMapping("/email/{email}")
	public int checkEmail(@PathVariable("email") String email) {
		
		log.info("email : " + email);
		
		return userService.countEmail(email);
	}
	
	@GetMapping("/hp/{hp}")
	public int checkHp(@PathVariable("hp") String hp) {
		
		log.info("hp : " + hp);
		
		return userService.countHp(hp);
	}
	
}
