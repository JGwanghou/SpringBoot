package kr.co.farmstory.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private SecurityUserService service;
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// 인가(접근권한) 설정
		http.authorizeHttpRequests().antMatchers("/").permitAll(); // 모든 자원에 대해서 모든 사용자 접근 허용
		
		
		// 사이트 위변조 요청 방지
		http.csrf().disable();

		/*
		// 로그인 설정
		http.formLogin()
		.loginPage("/user/login")
		.defaultSuccessUrl("/list") // 로그인 성공 시 이동할 페이지
		.failureUrl("/user/login?success=100")  // 로그인 실패 시 이동할 페이지
		.usernameParameter("uid")
		.passwordParameter("pass");

		// 로그아웃 설정
		http.logout() // 로그아웃 할 경우
		.invalidateHttpSession(true) // 세션 삭제
		.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) // 로그아웃 처리할 URL
		.logoutSuccessUrl("/user/login?success=200"); // 로그아웃 성공 시 이동 할 페이지
		*/
		return http.build();
	}


	
	@Bean
    public PasswordEncoder PasswordEncoder () {
        //return new MessageDigestPasswordEncoder("SHA-256");
		return new BCryptPasswordEncoder();
    }
}
