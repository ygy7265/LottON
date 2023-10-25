package kr.co.lottemarket.service.user;

import java.util.concurrent.ThreadLocalRandom;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import kr.co.lottemarket.dto.user.EmailMessageDTO;
import kr.co.lottemarket.dto.user.UserDTO;
import kr.co.lottemarket.entity.user.UserEntity;
import kr.co.lottemarket.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor // @Autowired 생략시 private final로 선언
@Service
public class UserService {

	
	private final UserRepository userRepository;
	
	private ModelMapper modelmapper;
	
	private final PasswordEncoder encoder;
	
	//private final JavaMailSender javaMailSender; // 이메일 인증
	
	private static String generatedCode; // 인증코드 생성 시 사용할 변수
	
	public UserDTO findByUesr(String uid) {
		UserEntity entity = userRepository.findByUid(uid);
		UserDTO dto = modelmapper.map(entity, UserDTO.class);
		return dto;

	}
	
	public UserDTO findByUid(String uid) {
		UserEntity entity = userRepository.findByUid(uid);
		return entity.toDTO();
	}
	
	public void save(UserDTO dto) { // Service에서는 Entity가 아닌 DTO가 매개변수로!!
		
		// 비밀번호 암호화
		dto.setPass1(encoder.encode(dto.getPass1()));
		
		// userRepository의 메서드 매개변수는 entity가 들어가야하니까 dto를 entity로 변환
		UserEntity entity = dto.toEntity();
		
		
		
		userRepository.save(entity); 
	}
	
	// 중복체크
	public int countUid(String uid) {
		return userRepository.countByUid(uid);
	}
	
	public int countEmail(String email) {
		return userRepository.countByEmail(email);
	}
	
	public int countHp(String hp) {
		return userRepository.countByHp(hp);
	}
	
	
	// 이메일 인증
	public int countNameAndEmail(String name, String email) {
		return userRepository.countByNameAndEmail(name, email);
	}
	
	public int countUidAndEmail(String uid, String email) {
		return userRepository.countByUidAndEmail(uid, email);
	}
	
	/*
	public int sendCodeByEmail(EmailMessageDTO emailMessage) {

        // 인증코드 생성
        int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
        generatedCode = String.valueOf(code);

        // 메일 발송
        int status = 0;
        MimeMessage  mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setFrom(new InternetAddress("LotteON@lotteOn.com", "LotteON", "UTF-8"));
            mimeMessageHelper.setTo(emailMessage.getTo()); // 메일 수신자
            mimeMessageHelper.setSubject(emailMessage.getSubject()); // 메일 제목
            mimeMessageHelper.setText("<h1>인증코드는 "+code+" 입니다.</h1>", true); // 메일 내용
            javaMailSender.send(mimeMessage);

            status = 1; // 메일 발송 시 status 1
            log.info("발송한 인증코드 : " + generatedCode);
            log.info("==================== 메일 전송 완료 ====================");

        }catch (Exception e) {
            log.info("******************** 메일 전송 실패 ********************");
            throw new RuntimeException(e);
        }

        return status;
    }*/
	
	public int confirmCodeByEmail(String code) {
		
		if(code.equals(generatedCode)) { // generatedCode 타입 String이므로, code도 String으로 받아서 문자열로 비교한다
			log.info("return 1...");
			return 1;
		}else {
			log.info("return 0...");
			return 0;
		}
	}
	
	// 아이디 찾기
	public UserDTO findByNameAndEmail(String name, String email) {
		UserEntity entity = userRepository.findByNameAndEmail(name, email);
		return entity.toDTO();
	}
	
	
	// 비밀번호 찾기
	public UserDTO findByUidAndEmail(String uid, String email) {
		UserEntity entity = userRepository.findByUidAndEmail(uid, email); // 왜 get() 안되지?? 
		return entity.toDTO(); // return값이 DTO로 나오도록 하는 건 Controller에서 service로 메서드 호출할 때 DTO로 나오기 위함!
	}
	
	// 비밀번호 변경
	public void updatePass(UserDTO dto) {
		
		// 비밀번호 암호화
		dto.setPass1(encoder.encode(dto.getPass1()));
		
		UserEntity entity = dto.toEntity(); //findPassChange에서 name=pass1에 입력한 값이 위에서 암호화되고 entity의 pass값으로 설정한다
		
		userRepository.save(entity);
	}
	
}
