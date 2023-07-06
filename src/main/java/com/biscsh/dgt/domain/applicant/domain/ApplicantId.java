package com.biscsh.dgt.domain.applicant.domain;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ApplicantId implements Serializable {

	@EqualsAndHashCode.Include
	private Long memberId;

	@EqualsAndHashCode.Include
	private Long postId;
}
