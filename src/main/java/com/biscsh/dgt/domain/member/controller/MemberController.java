package com.biscsh.dgt.domain.member.controller;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biscsh.dgt.domain.member.domain.Member;
import com.biscsh.dgt.domain.member.dto.InfoUpdateRequest;
import com.biscsh.dgt.domain.member.dto.SignInRequest;
import com.biscsh.dgt.domain.member.dto.SignUpRequest;
import com.biscsh.dgt.domain.member.exception.MemberErrorCode;
import com.biscsh.dgt.domain.member.exception.MemberException;
import com.biscsh.dgt.domain.member.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@PostMapping("/signup")
	public ResponseEntity<Boolean> signUp(@RequestBody SignUpRequest signUpRequest){
		signUpRequest.setPassword(encoder.encode(signUpRequest.getPassword()));
		Boolean signupSuccess = memberService.signUp(signUpRequest);

		return ResponseEntity.status(CREATED)
			.body(signupSuccess);
	}

	@PostMapping("/signin")
	public ResponseEntity<Boolean> signIn(HttpSession session, @RequestBody SignInRequest signInRequest){
		Long signInMemberId = memberService.signIn(signInRequest);

		session.setAttribute("signIn", signInMemberId);
		session.setMaxInactiveInterval(1800);

		return ResponseEntity.status(ACCEPTED).body(true);
	}

	@PostMapping("/signout")
	public ResponseEntity<Object> signOut(HttpSession session){
		try {
			Long signMemberId = getSignInMemberId(session);
		}catch (Exception e){
			return ResponseEntity.status(UNAUTHORIZED).body(false);
		}
		session.invalidate();

		return ResponseEntity.status(OK).build();
	}

	@PatchMapping("/update")
	public ResponseEntity<Boolean> update(HttpSession session, @RequestBody InfoUpdateRequest infoUpdateRequest){

		 //FIXME getSignInMemberId와 service계층에서 터질 수 있는 에러는 다르다.별도의 처리가 추후 필요함.
		try {
			Long signInMemberId = getSignInMemberId(session);
			memberService.updateInfo(signInMemberId, infoUpdateRequest);
		}catch (Exception e){
			return ResponseEntity.status(UNAUTHORIZED).body(false);
		}

		return ResponseEntity.status(CREATED).body(true);
	}

	@GetMapping("/info")
	public ResponseEntity<Member> info(HttpSession session){
		Long signInMemberId = null;
		Member member = null;

		//FIXME getSignInMemberId와 service계층에서 터질 수 있는 에러는 다르다.별도의 처리가 추후 필요함.
		try{
			signInMemberId = getSignInMemberId(session);
			member = memberService.getMember(signInMemberId);
		}catch (Exception e){
			return ResponseEntity.status(UNAUTHORIZED).body(null);
		}

		return ResponseEntity.status(OK).body(member);
	}

	@DeleteMapping
	public ResponseEntity<Void> delete (HttpSession session) {
		Long signInMemberId = null;

		signInMemberId = getSignInMemberId(session);

		session.invalidate();
		memberService.delete(signInMemberId);

		return ResponseEntity.status(OK).build();
	}


	private Long getSignInMemberId(HttpSession session){
		Long memberId = (Long)session.getAttribute("signIn");

		if(memberId == null){
			throw new MemberException(MemberErrorCode.NOT_SIGN_IN);
		}

		return memberId;
	}
}
