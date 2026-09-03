package com.study.spring.board;

import java.time.LocalDateTime;

import org.jspecify.annotations.Nullable;

public class Board {
    
    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;

    // Form 입력값을 받을 수 있는 기본 생성자
    public Board() {

    }

    // 조회 결과를 객체로 만들 때 사용할 생성자
    public Board(Long id, String title, String content, String writer, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
