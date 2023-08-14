package com.biscsh.dgt.domain.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biscsh.dgt.domain.member.dto.LogInRequest;
import com.biscsh.dgt.domain.member.dto.SignUpRequest;
import com.biscsh.dgt.domain.member.dto.SignUpResponse;
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
	public ResponseEntity<SignUpResponse> signup (@RequestBody SignUpRequest signUpRequest){
		signUpRequest.setPassword(encoder.encode(signUpRequest.getPassword()));
		SignUpResponse signup = memberService.signup(signUpRequest);

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(signup);
	}

	@PostMapping("/login")
	public ResponseEntity<Long> login(HttpServletRequest servletRequest, @RequestBody LogInRequest logInRequest){
		Long loginId = memberService.login(logInRequest);

		HttpSession session = servletRequest.getSession();
		session.setAttribute("login", loginId);
		session.setMaxInactiveInterval(1800);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(loginId);
	}
}
