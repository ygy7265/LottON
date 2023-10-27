package kr.co.lottemarket.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.co.lottemarket.entity.user.UserEntity;
import kr.co.lottemarket.repository.user.UserRepository;

@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	
	@Test
	public void findByUidAndPass() {
		
		String pass = encoder.encode("lotteon1!");
		
		UserEntity user = userRepository.findByUid("phs9611");
		
		System.out.println(user);
		
	}
	
}
