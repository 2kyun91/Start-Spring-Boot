package com.start.springboot.domain.board.controller;

import com.start.springboot.domain.board.service.BoardService;
import com.start.springboot.domain.navigation.service.NavBarService;
import com.start.springboot.domain.post.dto.PostDto;
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

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final NavBarService navBarService;
    private final BoardService boardService;
    private final PostService postService;

    @GetMapping("/main")
    public String getAllPostByBoardMain(Model model) {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "postCreateDate");
        Page<PostDto> postDtos = postService.getAllPostByBoardMain(pageable);
        if (!postDtos.isEmpty()) {
            model.addAttribute("postList", postDtos.getContent());
            model.addAttribute("totalElements", postDtos.getTotalElements());
            model.addAttribute("totalPages", postDtos.getTotalPages());
            model.addAttribute("pageSize", postDtos.getSize());
        }
        return "/board/main";
    }
}

