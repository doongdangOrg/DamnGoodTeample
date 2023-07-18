package com.biscsh.dgt.domain.post.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString(onlyExplicitlyIncluded = true)
public class ActivatePeriod {
    @NotNull
    @ToString.Include
//    @FutureOrPresent
    private LocalDateTime start;

    @NotNull
    @ToString.Include
//    @Future
    private LocalDateTime end;

    @ToString.Include
    private Duration duration;

    public static ActivatePeriod of(LocalDateTime start, LocalDateTime end) {
        ActivatePeriod activatePeriod = builder()
                .start(start)
                .end(end)
                .build();
        activatePeriod.calcDuration();
        return activatePeriod;
    }

    private void calcDuration() {
        duration = Duration.between(start, end);

    }
}
