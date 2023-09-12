package com.biscsh.dgt.domain.post.dao;

import com.biscsh.dgt.domain.post.domain.Post;
import com.biscsh.dgt.domain.post.domain.RecruitPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
