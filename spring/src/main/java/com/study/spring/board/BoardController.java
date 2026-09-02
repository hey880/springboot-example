package com.study.spring.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RequestMapping("/boards") // /boards로 시작하는 요청을 처리한다.
public class BoardController {
    
    private final BoardDao boardDao;

    public BoardController(BoardDao boardDao) { // BoardDao를 생성자 주입으로 받는다.
        this.boardDao = boardDao;
    }

    @GetMapping
    public String list(Model model) {
        // 게시글 목록을 조회하여 boards라는 이름으로 화면에 전달한다.
        model.addAttribute("boards", boardDao.findAll());
        return "board/list";
    }
    
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        // 주소의 {id} 값을 받아 게시글 상세 내용을 조회한다.
        model.addAttribute("board", boardDao.findById(id));
        return "board/detail";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("board", new Board());
        return "board/form";
    }

    @PostMapping
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardDao.findById(id));
        return "board/form";
    }
    
    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Board board) {
        boardDao.update(id, board);
        return "redirect:/boards/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        boardDao.delete(id);
        return "redirect:/boards";
    }
}
