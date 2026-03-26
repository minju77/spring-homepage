package com.example.demo.comment;

import com.example.demo.board.Board;
import com.example.demo.user.User;

import jakarta.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    private Board board;

    @ManyToOne
    private User user;

    public Long getId(){ return id; }

    public String getContent(){ return content; }
    public void setContent(String content){ this.content = content; }

    public Board getBoard(){ return board; }
    public void setBoard(Board board){ this.board = board; }

    public User getUser(){ return user; }
    public void setUser(User user){ this.user = user; }
}