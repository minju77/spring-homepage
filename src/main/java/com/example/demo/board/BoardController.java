package com.example.demo.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.comment.Comment;
import com.example.demo.comment.CommentRepository;
import com.example.demo.user.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final CommentRepository commentRepository;

    public BoardController(BoardService boardService, CommentRepository commentRepository) {
        this.boardService = boardService;
        this.commentRepository = commentRepository;
    }

    // 게시글 목록
    @GetMapping("/list")
    public String list(Model model){

        List<Board> boards = boardService.getAllBoards();

        model.addAttribute("boards", boards);

        return "board/boardList";
    }

    // 글쓰기 페이지
    @GetMapping("/write")
    public String writeForm(HttpSession session, Model model){
        
    		User user = (User) session.getAttribute("loginUser");
    		
    		if(user == null) {
    			model.addAttribute("msg", "로그인 후 이용 가능합니다.");
    			return "/user/login";
    		}
    	
    		return "board/boardWrite";
    }

    // 글쓰기 실행
    @PostMapping("/write")
    public String write(Board board, HttpSession session){

        User user = (User) session.getAttribute("loginUser");

        if(user == null) {
        		return "redirect:/user/login";
        }
        
        board.setUser(user);

        boardService.save(board);

        return "redirect:/board/list";
    }

    // 상세
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model){

        Board board = boardService.getBoard(id);

        List<Comment> comments = commentRepository.findByBoard(board);

        model.addAttribute("board", board);
        model.addAttribute("comments", comments);

        return "board/boardDetail";
    }

    // 수정 페이지
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model, HttpSession session){

        Board board = boardService.getBoard(id);

        User user = (User) session.getAttribute("loginUser");

        // 로그인 안했으면
        if(user == null){
            return "redirect:/login";
        }

        // 작성자가 아니면 수정 불가
        if(!board.getUser().getId().equals(user.getId())){
            return "redirect:/board/list";
        }

        model.addAttribute("board", board);

        return "board/edit";
    }

    // 수정 실행
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Board board, HttpSession session){

        Board origin = boardService.getBoard(id);

        User user = (User) session.getAttribute("loginUser");

        if(user == null){
            return "redirect:/login";
        }

        if(!origin.getUser().getId().equals(user.getId())){
            return "redirect:/board/list";
        }

        origin.setTitle(board.getTitle());
        origin.setContent(board.getContent());

        boardService.save(origin);

        return "redirect:/board/" + id;
    }
    
    // 게시글 삭제
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, HttpSession session){

        Board board = boardService.getBoard(id);

        User user = (User) session.getAttribute("loginUser");

        // 로그인 안했으면
        if(user == null){
            return "redirect:/login";
        }

        // 작성자가 아니면 삭제 불가
        if(!board.getUser().getId().equals(user.getId())){
            return "redirect:/board/list";
        }

        boardService.delete(id);

        return "redirect:/board/list";
    }
}