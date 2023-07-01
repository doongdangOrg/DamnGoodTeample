package com.biscsh.dgt.domain.post.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    private String file;

    private String title;

    @NotNull
    private Long postId;
}
