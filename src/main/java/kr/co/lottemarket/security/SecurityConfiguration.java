package kr.co.lottemarket.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
	
	
	@Autowired
	private SecurityUserService service;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		.authorizeHttpRequests(
			authorizeHttpRequests -> 
			authorizeHttpRequests
			.requestMatchers("/**").permitAll()
		//	.requestMatchers("/admin/**").hasAuthority("ADMIN")
		//	.requestMatchers("/manager/**").hasAnyAuthority("ADMIN", "MANAGER")
            

			.anyRequest().authenticated()
		)

		//토큰방식으로 로그인처리하기때문에 품방식 비활성
		.formLogin(config -> config
                .loginPage("/member/login")
                .defaultSuccessUrl("/",true)
                .failureUrl("/member/login?success=100")
                .usernameParameter("uid")
                .passwordParameter("pass")
        )

		// 사이트 위변조 방지 해제
		.csrf(CsrfConfigurer::disable)
        .logout(config -> config
                        .logoutUrl("/member/logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutSuccessUrl("/member/login?success=200")
        )

        .userDetailsService(service);// 사용자 인증처리 컴포넌트 등록
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
