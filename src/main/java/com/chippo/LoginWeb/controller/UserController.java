package com.chippo.LoginWeb.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chippo.LoginWeb.entity.CustomUserDetails;
import com.chippo.LoginWeb.model.LoginResponse;
import com.chippo.LoginWeb.model.UserDTO;
import com.chippo.LoginWeb.service.UserService;
import com.chippo.LoginWeb.utils.JwtTokenProvider;

@RestController
public class UserController {

	@Autowired
	UserService userService;
//ádsada
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;


	@PostMapping("/login")
	public LoginResponse authenticateUser(@Valid @RequestBody UserDTO userDTO, HttpServletResponse response) {

		System.out.println(userDTO.getUsername()+"\n"+userDTO.getPassword());
		// Xác thực thông tin người dùng Request lên
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));

		// Nếu không xảy ra exception tức là thông tin hợp lệ
		// Set thông tin authentication vào Security Context
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Trả về jwt cho người dùng.
		String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
		System.out.println("Đây nhé: "+jwt);
		LoginResponse loginResponse = new LoginResponse(jwt);
		Cookie cookie = new Cookie("cookieToken", jwt);
		if (userDTO.isCheckRemember()==true) {		
			cookie.setMaxAge(30*24*60*60);		
		}
		response.addCookie(cookie);
		return loginResponse;
	}
	@GetMapping("/dang-nhap")
	public ModelAndView init() {

		ModelAndView login = new ModelAndView("user/login");
		return login;
	}

	@GetMapping("/dang-ky")
	public ModelAndView dangKy() {
		ModelAndView signup = new ModelAndView("signup");
		return signup;
	}

	@GetMapping("/dashboard")
	public ModelAndView dashboard() {
		ModelAndView dashboard = new ModelAndView("dashboard");
		
//		CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
		return dashboard;
	}

}
