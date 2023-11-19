package kr.co.lottemarket.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// 변수 선언 및 초기화
		String uid = null;
		String type = null;
		
		if(authentication != null) {
			Object principal = authentication.getPrincipal();
			// uid는 현재 인증된 사용자의 아이디
			uid = ((UserDetails) principal).getUsername();
			List<GrantedAuthority> authorities = (List<GrantedAuthority>) ((UserDetails) principal).getAuthorities();
			
			GrantedAuthority authority = authorities.get(0);
			
			type = authority.getAuthority().substring(5);
		}
		
		return uid;

	}
	
	public String DateTime(String date) {
		

	        // 문자열을 LocalDateTime으로 파싱
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

	        // 년, 월, 일 추출
	        int year = dateTime.getYear();
	        int month = dateTime.getMonthValue();
	        int day = dateTime.getDayOfMonth();
	        String sDate = year +" - " + month + " - " + day;
	        return sDate;
		
	}
}
