package com.biscsh.dgt.domain.post.domain;


import org.springframework.stereotype.Component;

@Component
public class PostContext {

    private Viewable post;

    public void viewPost() {
        post.viewPost();
    }

}
