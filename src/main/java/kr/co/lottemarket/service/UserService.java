package kr.co.lottemarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.lottemarket.dto.UserDTO;
import kr.co.lottemarket.entity.UserEntity;
import kr.co.lottemarket.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	
	public void save(UserDTO dto) {
		
		// 비밀번호 암호화
		dto.setPass1(encoder.encode(dto.getPass1()));
		
		// userRepository의 메서드 매개변수는 entity가 들어가야하니까 dto를 entity로 변환
		UserEntity entity = dto.toEntity();
		
		
		
		userRepository.save(entity); 
	}
	
	public int countUid(String uid) {
		return userRepository.countByUid(uid);
	}
	
	public int countEmail(String email) {
		return userRepository.countByEmail(email);
	}
	
	public int countHp(String hp) {
		return userRepository.countByHp(hp);
	}
}
