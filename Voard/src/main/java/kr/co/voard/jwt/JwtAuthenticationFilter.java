package kr.co.voard.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import kr.co.voard.security.MyUserDetails;
import kr.co.voard.security.SecurityUserService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private SecurityUserService securityUserService;
	@Autowired
	private JwtUtill jwtUtill;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = jwtUtill.resolveToken(request);
		
		if(token != null && jwtUtill.validateToken(token)) {
			
			String uid = jwtUtill.getUsernameFromToken(token);
			
			// Security 인증처리
			MyUserDetails myUserDetails = securityUserService.loadUserByUsername(uid);
			Authentication authentication = new UsernamePasswordAuthenticationToken(myUserDetails, null, myUserDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		// 다음필터 이동
		filterChain.doFilter(request, response);
	}

}
