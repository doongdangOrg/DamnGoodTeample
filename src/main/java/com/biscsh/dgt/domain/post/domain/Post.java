package com.biscsh.dgt.domain.post.domain;

import com.biscsh.dgt.domain.post.domain.post.Article;
import com.biscsh.dgt.domain.post.domain.post.Title;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Getter
public class Post implements Viewable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @NotNull
    private Long memberId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "title", nullable = false, length = 100))
    @ToString.Include
    private Title title;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "article", length = 4000))
    private Article article;

    private int viewCnt;

    @Override
    public void viewPost() {
        viewCnt++;
    }
}
