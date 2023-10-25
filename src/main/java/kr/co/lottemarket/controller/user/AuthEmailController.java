package kr.co.lottemarket.controller.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.lottemarket.dto.user.EmailMessageDTO;
import kr.co.lottemarket.service.user.UserService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class AuthEmailController { 

	@Autowired
	private UserService userService;
	
	@GetMapping("/member/authEmail")
    public Map<String, Integer> authEmail(@RequestParam Map<String, Object> params) { 

        // authEmail.js에서 AJAX로 전송한 데이터 수신
        String type  = (String) params.get("type");
        String uid   = (String) params.get("uid");
        String name  = (String) params.get("name");
        String email = (String) params.get("email");
        log.info("AuthEmailController type ---- "+type);
        log.info("AuthEmailController uid ---- "+uid);
        log.info("AuthEmailController name ---- "+name);
        log.info("AuthEmailController email ---- "+email);

        EmailMessageDTO emailMessageDTO = EmailMessageDTO.builder()
                                                        .to(email)
                                                        .subject("[LotteON] 이메일 인증 코드입니다.")
                                                        .build();

        int result = 0;
        int status = 0;

        if (Objects.equals(type, "normal") || Objects.equals(type, "seller")) // NullPointerException 방지
        {
            result = userService.countEmail(email); // 중복여부(1 or 0)

            if (result == 0)
                status = userService.sendCodeByEmail(emailMessageDTO); // 전송결과(1 or 0)

            log.info("AuthEmailController result ====== "+result);
            log.info("AuthEmailController status ====== "+status);
        }
        else if (Objects.equals(type, "FIND_ID"))
        {
            result = userService.countNameAndEmail(name, email); // 중복여부(1 or 0)

            if (result == 1)
                status = userService.sendCodeByEmail(emailMessageDTO); // 전송결과(1 or 0)

            log.info("AuthEmailController result ====== "+result);
            log.info("AuthEmailController status ====== "+status);
        }
        else if (Objects.equals(type, "FIND_PASS"))
        {
            result = userService.countUidAndEmail(uid, email); // 중복여부(1 or 0)

            if (result == 1)
                status = userService.sendCodeByEmail(emailMessageDTO); // 전송결과(1 or 0)

            log.info("AuthEmailController result ====== "+result);
            log.info("AuthEmailController status ====== "+status);
        }

        Map<String, Integer> returnMap = new HashMap<>();
        returnMap.put("result", result);
        returnMap.put("status", status);

        return returnMap;
    }
	
	
	@PostMapping("/member/authEmail/{code}")
	public int authEmail(@PathVariable("code") String code) {
		
		return userService.confirmCodeByEmail(code);
	}
}
