package com.biscsh.dgt.config.signin;

import static jakarta.servlet.http.HttpServletResponse.*;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.biscsh.dgt.domain.member.exception.MemberErrorCode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignInInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod)handler;
		SignInRequired signInRequired = handlerMethod.getMethodAnnotation(SignInRequired.class);
		System.out.println("ereeefojdpsaopjfpj");
		if (signInRequired != null) {
			HttpSession session = request.getSession();

			if (session.getAttribute("signIn") == null) {
				response.setStatus(SC_UNAUTHORIZED);
				response.getWriter().write(MemberErrorCode.NOT_SIGN_IN.getDescription());

				return false;
			}

		}

		return true;
	}
}
