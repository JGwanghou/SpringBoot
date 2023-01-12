package kr.co.ch08.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// 인가(접근권한) 설정
		http.authorizeHttpRequests().antMatchers("/").permitAll();
		http.authorizeHttpRequests().antMatchers("/admin/**").hasRole("ADMIN");
		http.authorizeHttpRequests().antMatchers("/member/**").hasAnyRole("ADMIN", "MEMBER");
		
		// 사이트 위변조 요청 방지
		http.csrf().disable();
		
		// 로그인 페이지 설정
		http.formLogin() // form 기반의 로그인인 경우
		.loginPage("/user2/login") // 인증이 필요한 URL에 접근하면 로그인폼으로 이동
		.usernameParameter("uid") // 로그인 시 form에서 가져올 값
		.passwordParameter("pass")// 로그인 시 form에서 가져올 값
		.defaultSuccessUrl("/user2/loginSuccess") // 로그인 성공 시 이동할 페이지
		.failureUrl("/user2/login?success=100"); // 로그인 실패 시 로그인페이지 이동
		
		
		// 로그아웃 설정
		http.logout() // 로그아웃 할 경우
		.invalidateHttpSession(true) // 세션 삭제
		.logoutRequestMatcher(new AntPathRequestMatcher("/user2/logout")) // 로그아웃을 처리할 URL
		.logoutSuccessUrl("/user2/login?success=200"); // 로그아웃 성공 시 이동할 페이지
	}
	
	@Autowired
	private SecurityUserService service;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// 로그인 인증처리 서비스 등록
		auth.userDetailsService(service).passwordEncoder(new MessageDigestPasswordEncoder("SHA-256"));
		
		
		
	}
	
}
