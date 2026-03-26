package com.example.demo.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.board.Board;

public interface CommentRepository extends JpaRepository<Comment, Long>{

    List<Comment> findByBoard(Board board);

}