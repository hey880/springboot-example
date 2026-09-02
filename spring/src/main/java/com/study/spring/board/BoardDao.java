package com.study.spring.board;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository // Repository 애노테이션은 DB 접근 클래스임을 의미
public class BoardDao {
    // SQL 실행을 담당하는 JdbcTemplate을 필드로 선언
    private final JdbcTemplate jdbcTemplate;
    // DI(생성자 주입)로 JdbcTemplate을 주입 받음
    public BoardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    // 쿼리 실행 결과인 ResultSet을 Board 객체로 변환
    private final RowMapper<Board> boardRowMapper = (rs, rowNum) -> 
            new Board(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getString("writer"),
                    rs.getTimestamp("created_at").toLocalDateTime()
            );
    // 게시글 전체 목록을 최신 글 순서로 조회
    public List<Board> findAll() {
        String sql = "SELECT * FROM board ORDER By id DESC";
        return jdbcTemplate.query(sql, boardRowMapper);
    }

    // 게시글 번호에 해당하는 글 하나를 조회
    public Board findById(Long id) {
        String sql = "SELECT * FROM board WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, boardRowMapper, id);
    }

    // 새 게시글을 board 테이블에 저장
    public void save(Board board) {
        // id와 created_at은 데이터베이스에서 자동 생성
        String sql = "INSERT INTO board(title, content, writer) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, board.getTitle(), board.getContent(), board.getWriter());
    }

    public void update(Long id, Board board) {
        String sql = "UPDATE board SET title = ?, content = ?, writer = ? WHERE id = ?";
        jdbcTemplate.update(sql, board.getTitle(), board.getContent(), board.getWriter(), id);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM board WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
