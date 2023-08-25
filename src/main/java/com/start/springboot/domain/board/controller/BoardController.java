package com.start.springboot.domain.board.controller;

import com.start.springboot.domain.post.dto.PostAllBoardDto;
import com.start.springboot.domain.board.service.BoardService;
import com.start.springboot.domain.navigation.service.NavBarService;
import com.start.springboot.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final NavBarService navBarService;
    private final BoardService boardService;
    private final PostService postService;

    @GetMapping("/all")
    @ResponseBody
    public ModelAndView getPostByAllBoard(Model model) {
        ModelAndView mv = new ModelAndView("/board/all");
        // 동적정렬 구현해보기...
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "postCreateDate");
        Page<PostAllBoardDto> postAllBoardDtos = postService.getPostByAllBoard(pageable);
        if (!postAllBoardDtos.isEmpty()) {
//            model.addAttribute("postList", postAllBoardDtos.getContent());
//            model.addAttribute("totalElements", postAllBoardDtos.getTotalElements());
//            model.addAttribute("totalPages", postAllBoardDtos.getTotalPages());
//            model.addAttribute("pageSize", postAllBoardDtos.getSize());
            mv.addObject("postList", postAllBoardDtos.getContent());
            mv.addObject("totalElements", postAllBoardDtos.getTotalElements());
            mv.addObject("totalPages", postAllBoardDtos.getTotalPages());
            mv.addObject("pageSize", postAllBoardDtos.getSize());
        }
        return mv;
    }
}

