package com.chippo.LoginWeb.utils;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.chippo.LoginWeb.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenProvider tokenProvider;
	@Autowired
	private UserService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = getJwtFromRequest(request);
//			System.out.println("Authorization " + request.getHeader("Authorization"));
			System.out.println("jwt: "+jwt);
			System.out.println(request.getRequestURI());
			
			System.out.println("cookieToken: "+getCookie(request, "cookieToken"));
//			Cookie[] cookies = request.getCookies();
//			for (Cookie cookie : cookies) {
//				System.out.println(cookie.getName());
//
//			}
//			Cookie[] cookies = request.getCookies();
//			for (Cookie cookie : cookies) {
//				cookie.getName();
//				System.out.println(cookie.getName());
//				
//				if (request.getRequestURI().contains("dang-nhap") && cookie.getName().equals("cookieToken")) {
//					response.sendRedirect("http://localhost:8080/dashboard");
//				}
//
//			}

			if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
				Long userId = tokenProvider.getUserIdFromJWT(jwt);

				UserDetails userDetails = customUserDetailsService.loadUserById(userId);
				if (userDetails != null) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(authentication);
					
				}
				
			}
			
		} catch (Exception ex) {
			System.out.println("failed on set user authentication");
		}
		filterChain.doFilter(request, response);
		
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		
		String bearerToken = getCookie(request, "cookieToken");
		if (bearerToken != null) {
			return bearerToken;
		}
//		String bearerToken = request.getHeader("Authorization");
//		// Kiểm tra xem header Authorization có chứa thông tin jwt không
//		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//			return bearerToken.substring(7);
//		}
		return null;
	}
	
	private String getCookie(HttpServletRequest request, String name) {
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals(name)) {
	                return cookie.getValue();
	            }
	        }
	    }
	    return null;
	}
}
