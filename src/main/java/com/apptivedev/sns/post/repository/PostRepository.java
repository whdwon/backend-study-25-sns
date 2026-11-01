package com.apptivedev.sns.post.repository;

import com.apptivedev.sns.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository는 <엔티티 타입, 기본 키 타입>를 받는다.
public interface PostRepository extends JpaRepository<Post, Long> {
    // save(), findAll(), findById(), deleteById() 등이 자동으로 생성.
}
