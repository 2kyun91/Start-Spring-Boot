package com.start.springboot.domain.board.controller;

import com.start.springboot.domain.board.service.BoardService;
import com.start.springboot.domain.navigation.service.NavBarService;
import com.start.springboot.domain.post.dto.PostAllBoardDto;
import com.start.springboot.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
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
    public ModelAndView getPostByAllBoard(@Param("orderType") String orderType, Model model) {
        ModelAndView mv = new ModelAndView();
        if (!StringUtils.isEmpty(orderType)) {
            mv.setViewName("/board/all_list");
        } else {
            orderType = "postCreateDate";
            mv.setViewName("/board/all");
        }

        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.desc(orderType)));
        Page<PostAllBoardDto> postAllBoardDtos = postService.getPostByAllBoard(pageable);
        if (!postAllBoardDtos.isEmpty()) {
            mv.addObject("postList", postAllBoardDtos.getContent());
            mv.addObject("totalElements", postAllBoardDtos.getTotalElements());
            mv.addObject("totalPages", postAllBoardDtos.getTotalPages());
            mv.addObject("pageSize", postAllBoardDtos.getSize());
        }

        return mv;
    }
}

