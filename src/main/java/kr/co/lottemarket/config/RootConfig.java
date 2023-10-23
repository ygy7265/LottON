package kr.co.lottemarket.config;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class RootConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	public Object Usersession() {
		// 현재 사용자의 정보 가져오기 
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		// 변수 선언 및 초기화
		String uid = null;
		String type = null;
		
		if(principal instanceof UserDetails) {
			
			// uid는 현재 인증된 사용자의 아이디
			uid = ((UserDetails) principal).getUsername();
			List<GrantedAuthority> authorities = (List<GrantedAuthority>) ((UserDetails) principal).getAuthorities();
			
			GrantedAuthority authority = authorities.get(0);
			
			type = authority.getAuthority().substring(5);
		}
		
		return uid;

	}
}
