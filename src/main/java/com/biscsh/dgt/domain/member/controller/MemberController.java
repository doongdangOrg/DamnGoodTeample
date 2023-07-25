package com.biscsh.dgt.domain.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@PostMapping("/signup")
	public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest signUpRequest){
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(memberService.signUp(signUpRequest));
	}

	@PostMapping("/login")
	public void logIn(@RequestBody LogInRequest logInRequest, HttpServletRequest request){
		Long loginMember = memberService.logIn(logInRequest);

		if(loginMember == null){

		}

		HttpSession session = request.getSession();
		session.setAttribute("memberId", loginMember);
		session.setMaxInactiveInterval(1800);
	}

	@PostMapping("/logout")
	public void logOut(HttpSession session){
		session.invalidate();
	}

}
