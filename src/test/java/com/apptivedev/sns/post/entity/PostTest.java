package com.apptivedev.sns.post.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PostTest {
    @Test
    @DisplayName("정상적인 제목과 내용으로 게시글을 생성한다")
    void createPost_success() {
        // 여러분들 방식(생성자 이용)
//        Post post = new Post(
//                "게시글 제목",
//                "게시글 내용"
//        );
        // 정적 팩토리 메서드
        Post post = Post.create(
                "게시글 제목",
                "게시글 내용"
        );

        // 생성된 Post가 전달받은 값을 올바르게 가지고 있는지 확인한다.
        assertThat(post.getTitle()).isEqualTo("게시글 제목");
        assertThat(post.getContent()).isEqualTo("게시글 내용");
    }

    @Test
    @DisplayName("빈 제목으로 게시글을 생성할 수 없다")
    void createPost_blankTitle_throwsException() {
        // 잘못된 제목으로 Post 생성을 시도했을 때 예외가 발생하는지 확인한다.
        assertThatThrownBy(() ->
                Post.create(" ", "게시글 내용")
        )
                // 예상한 종류의 예외인지 확인한다.
                .isInstanceOf(IllegalArgumentException.class)

                // 예외 메시지가 의도한 내용과 같은지 확인한다.
                .hasMessage("제목은 필수입니다.");
    }

    @Test
    @DisplayName("null로 게시글을 생성할 수 없다")
    void createPost_blankContent_throwsException() {
        // 공백만 있는 내용으로 생성하면 예외가 발생해야 한다.
        assertThatThrownBy(() ->
                Post.create("게시글 제목", null)
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("내용은 null일 수 없습니다.");
    }
}
