package com.biscsh.dgt.domain.submitted.domain;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SubmittedSubjectiveId implements Serializable {

	@EqualsAndHashCode.Include
	private Long memberId;

	@EqualsAndHashCode.Include
	private Long questionId;
}
