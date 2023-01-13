package kr.co.ch08.security;

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
		http.authorizeHttpRequests().antMatchers("/admin/**").hasRole("ADMIN"); // admin 하위 모든 링크에 대해서 admin에게만 허용
		http.authorizeHttpRequests().antMatchers("/member/**").hasAnyRole("ADMIN", "MEMBER"); // member 하위 모든 링크에 대해서 ADMIN, MEMBER 에게만 허용
		http.authorizeHttpRequests().antMatchers("/user2/loginSuccess").hasAnyRole("3", "4", "5"); // /user2/loginSuccess는 ADMIN만 접근 허용
		
		// 사이트 위변조 요청 방지
		http.csrf().disable();
		
		// 로그인 설정
		http.formLogin()
		.loginPage("/user2/login")
		.defaultSuccessUrl("/user2/loginSuccess") // 로그인 성공 시 이동할 페이지
		.failureUrl("/user2/login?success=100)")  // 로그인 실패 시 이동할 페이지
		.usernameParameter("uid")
		.passwordParameter("pass");
		
		// 로그아웃 설정
		http.logout() // 로그아웃 할 경우
		.invalidateHttpSession(true) // 세션 삭제
		.logoutRequestMatcher(new AntPathRequestMatcher("/user2/logout")) // 로그아웃 처리할 URL
		.logoutSuccessUrl("/user2/login?success=200"); // 로그아웃 성공 시 이동 할 페이지
		
		// 사용자 인증 처리 컴포넌트 서비스 등록
		http.userDetailsService(service);
		
		return http.build();
	}
	
	@Bean
    public PasswordEncoder PasswordEncoder () {
        //return new MessageDigestPasswordEncoder("SHA-256");
		return new BCryptPasswordEncoder();
    }
}
