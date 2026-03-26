package com.example.demo.comment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.board.Board;
import com.example.demo.board.BoardService;
import com.example.demo.user.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final CommentRepository commentRepository;
    private final BoardService boardService;

    public CommentController(CommentRepository commentRepository, BoardService boardService) {
        this.commentRepository = commentRepository;
        this.boardService = boardService;
    }

    // 댓글 작성
    @PostMapping("/write")
    public String writeComment(
            @RequestParam Long boardId,
            @RequestParam String content,
            HttpSession session
    ) {

        // 로그인 사용자
        User user = (User) session.getAttribute("loginUser");

        if(user == null){
            return "redirect:/user/login";
        }

        // 게시글 가져오기
        Board board = boardService.getBoard(boardId);

        // 댓글 생성
        Comment comment = new Comment();
        comment.setBoard(board);
        comment.setUser(user);
        comment.setContent(content);

        // 저장
        commentRepository.save(comment);

        // 다시 게시글 상세로
        return "redirect:/board/" + boardId;
    }
    
    // 댓글 삭제
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){

        Comment comment = commentRepository.findById(id).orElse(null);

        Long boardId = comment.getBoard().getId();

        commentRepository.delete(comment);

        return "redirect:/board/" + boardId;
    }
}