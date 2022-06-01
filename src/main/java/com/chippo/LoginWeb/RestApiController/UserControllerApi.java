
package com.chippo.LoginWeb.RestApiController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.chippo.LoginWeb.model.UserDTO;
import com.chippo.LoginWeb.service.RecaptchaService;
import com.chippo.LoginWeb.service.UserRepositoryService;

@RestController
public class UserControllerApi {

	@Autowired
	UserRepositoryService userService;

	@Autowired
	RecaptchaService captchaService;

	@GetMapping("/api/list-user")
	public List<UserDTO> listUser() {
		List<UserDTO> list = userService.getAll();
		return list;
	}

	@GetMapping("/api/chi-tiet-nguoi-dung/{id}")
	public UserDTO getUser(@PathVariable(name = "id") int id) {
		return userService.getUserById(id);
	}

	@DeleteMapping("/api/xoa-nguoi-dung/{id}")
	public void deleteUser(@PathVariable(name = "id") int id) {
		userService.delete(id);
	}

	@PutMapping(value = "/api/add-user")

	@ResponseStatus(code = HttpStatus.CREATED)
	public UserDTO addUser(@RequestBody UserDTO userDTO) {

		userService.add(userDTO);
		return userDTO;
	}

	@PutMapping(value = "/api/edit-user")

	@ResponseStatus(code = HttpStatus.CREATED)
	public UserDTO editUser(@RequestBody UserDTO userDTO) {
		userService.update(userDTO);
		return userDTO;
	}

	@GetMapping("/api/sucsess")
	public String login() {
		return "success";
	}

	@PutMapping("/sign-up")
	public ResponseEntity<?> signup(@RequestBody UserDTO user, HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		String captchaVerifyMessage = captchaService.verifyRecaptcha(ip, user.getCaptcha());

		if (StringUtils.isNotEmpty(captchaVerifyMessage)) {
			Map<String, Object> response = new HashMap<>();
			response.put("message", captchaVerifyMessage);
			return ResponseEntity.badRequest().body(response);
		}
		userService.add(user);
		return ResponseEntity.ok().build();
	}

}
