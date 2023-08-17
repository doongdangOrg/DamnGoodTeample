package com.biscsh.dgt.domain.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biscsh.dgt.domain.member.dto.LogInRequest;
import com.biscsh.dgt.domain.member.dto.SignUpRequest;
import com.biscsh.dgt.domain.member.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@PostMapping("/signup")
	public ResponseEntity<Boolean> signup (@RequestBody SignUpRequest signUpRequest){
		signUpRequest.setPassword(encoder.encode(signUpRequest.getPassword()));
		Boolean signupSuccess = memberService.signup(signUpRequest);

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(signupSuccess);
	}

	@PostMapping("/login")
	public ResponseEntity<Boolean> login(HttpServletRequest servletRequest, @RequestBody LogInRequest logInRequest){
		Long loginMemberId = memberService.login(logInRequest);

		HttpSession session = servletRequest.getSession();
		session.setAttribute("login", loginMemberId);
		session.setMaxInactiveInterval(1800);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);
	}
}
