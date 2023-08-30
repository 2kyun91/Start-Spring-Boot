package com.start.springboot.domain.board.controller;

import com.start.springboot.common.page.PageDto;
import com.start.springboot.common.page.Pagination;
import com.start.springboot.common.search.SearchDto;
import com.start.springboot.domain.post.dto.PostBoardDto;
import com.start.springboot.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final PostService postService;

    @RequestMapping("/{boardType}/list")
    @ResponseBody
    public ModelAndView getPostList(
            @PathVariable("boardType") String boardType,
            @ModelAttribute("search") SearchDto searchDto, PageDto pageDto,
            @Param("orderType") String orderType) {
        ModelAndView mv = new ModelAndView();

        if (!StringUtils.isEmpty(orderType)) {
            mv.setViewName("/board/" + boardType + "_list");
        } else {
            orderType = "postCreateDate";
            mv.setViewName("/board/" + boardType);
        }

        Pageable pageable = pageDto.makePageable(Sort.Direction.DESC, orderType);
        Page<PostBoardDto> postBoardDtos = postService.getPostList(searchDto, pageable);
        Pagination pagination = new Pagination(postBoardDtos);

        if (!postBoardDtos.isEmpty()) {
            mv.addObject("pagination", pagination);
            mv.addObject("totalElements", pagination.getResult().getTotalElements());
//            mv.addObject("totalElements", postBoardDtos.getTotalElements());
//            mv.addObject("totalPages", postBoardDtos.getTotalPages());
//            mv.addObject("pageSize", postBoardDtos.getSize());
        }

        return mv;
    }
}
