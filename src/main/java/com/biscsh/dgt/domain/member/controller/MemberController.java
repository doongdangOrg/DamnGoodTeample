package com.biscsh.dgt.domain.member.controller;

import static com.biscsh.dgt.domain.member.exception.MemberErrorCode.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biscsh.dgt.domain.member.dto.InfoUpdateRequest;
import com.biscsh.dgt.domain.member.dto.SignInRequest;
import com.biscsh.dgt.domain.member.dto.SignUpRequest;
import com.biscsh.dgt.domain.member.exception.MemberException;
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
	public ResponseEntity<Boolean> signUp(@RequestBody SignUpRequest signUpRequest){
		signUpRequest.setPassword(encoder.encode(signUpRequest.getPassword()));
		Boolean signupSuccess = memberService.signUp(signUpRequest);

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(signupSuccess);
	}

	@PostMapping("/signin")
	public ResponseEntity<Boolean> signIn(HttpSession session, @RequestBody SignInRequest signInRequest){
		Long signInMemberId = memberService.signIn(signInRequest);

		session.setAttribute("signIn", signInMemberId);
		session.setMaxInactiveInterval(1800);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);
	}

	@PatchMapping("/update")
	public ResponseEntity<Boolean> update(HttpSession session, @RequestBody InfoUpdateRequest infoUpdateRequest){
		Long memberId = (Long)session.getAttribute("signIn");

		if(memberId == null){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}

		memberService.updateInfo(memberId, infoUpdateRequest);

		return ResponseEntity.status(HttpStatus.CREATED).body(true);
	}
}
