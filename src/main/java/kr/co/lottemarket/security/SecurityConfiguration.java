package kr.co.lottemarket.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfiguration implements WebMvcConfigurer {
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private SecurityUserService service;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		// 사이트 위변조 방지 해제
		.csrf(CsrfConfigurer::disable)
		.authorizeHttpRequests(
			authorizeHttpRequests -> 
			authorizeHttpRequests
			.requestMatchers("/","/member/**","/thumb1/**","/product/**").permitAll()
			.requestMatchers("/admin/**").hasAnyAuthority("3")
			.requestMatchers("/js/**", "/images/**", "/css/**").permitAll()
			.anyRequest().authenticated()
		)

		//토큰방식으로 로그인처리하기때문에 품방식 비활성 : login.html에서 post로 폼 전송을 할 필요없이 여기 formLogin 메서드 자체에서 로그인 처리함
		.formLogin(config -> config 
                .loginPage("/member/login") // 이 주소로 매핑된 메서드에서 formLogin 진행
                .defaultSuccessUrl("/",true) // GET 전송인가?? ㅇㅇ
                .failureUrl("/member/login?success=100")
                .usernameParameter("uid")
                .passwordParameter("pass")
        )

        .logout(config -> config
                        .logoutUrl("/member/logout") // GET 전송
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutSuccessUrl("/member/login?success=200")
        )

        .userDetailsService(service); // 사용자 인증처리 컴포넌트 등록
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}
}
