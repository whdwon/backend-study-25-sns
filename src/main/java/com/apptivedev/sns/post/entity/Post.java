package com.apptivedev.sns.post.entity;

import jakarta.persistence.*; // JPA 관련 어노테이션과 클래스 가져오기 위한 import
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter // 게시물 조회를 위한 getter 함수 자동 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA에서 사용하는 기본생성자 자동 생성
@Table(name = "posts") // 데이터베이스에 저장될 테이블 이름
public class Post {
    @Id // PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 유일한 식별자로 서버에서 자동 생성(ex. 1,2,3....)
    @Column(name = "post_id") // 테이블에 저장될 열 이름
    private Long id;

    @Column(nullable = false) // 제약조건 설정(null 금지)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT") // 제약조건 설정(null 금지, 열 타입을 TEXT로 지정해 수만 글자 이상 저장 가능)
    private String content;

    // private로 외부에서 직접 new를 통한 생성 방지(Post 내부에서만 생성 가능)
    // 보통 객체의 생성 경로를 통일해 생성 조건을 거치도록 하려고 사용
    private Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 게시물 생성을 위한 메서드 (생성 검증 포함)
    public static Post create(String title, String content) {
        // 생성 조건 검증
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("제목은 필수입니다.");
        }
        if (content == null) {
            throw new IllegalArgumentException("내용은 null일 수 없습니다.");
        }
        return new Post(title, content);
    }

    // 게시물 수정을 위한 메서드
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
