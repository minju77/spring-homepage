package com.example.demo.board;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public List<Board> getAllBoards(){
		return boardRepository.findAll();
	}
	
	public Board getBoard(Long id) {
		return boardRepository.findById(id).orElse(null);
	}
	
	public void save(Board board) {
		boardRepository.save(board);
	}
	
	public void delete(Long id){
	    boardRepository.deleteById(id);
	}
}