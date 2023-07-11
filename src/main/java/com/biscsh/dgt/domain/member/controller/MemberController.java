package com.biscsh.dgt.domain.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.biscsh.dgt.domain.member.domain.dto.LogInRequest;
import com.biscsh.dgt.domain.member.domain.dto.SignUpRequest;
import com.biscsh.dgt.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/signup")
	public String signUp(Model model){
		model.addAttribute("signUpRequest", new SignUpRequest());

		return "signup";
	}

	@PostMapping("/signup")
	public String signUp(@ModelAttribute SignUpRequest signUpRequest){
		memberService.signUp(signUpRequest);

		return "redirect:/login";
	}

	@GetMapping("/login")
	public String logIn(Model model){
		model.addAttribute("logInRequest", new LogInRequest());

		return "login";
	}


}
