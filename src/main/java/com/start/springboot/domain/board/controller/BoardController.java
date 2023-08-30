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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
            ModelAndView mv) {
        if (!StringUtils.isEmpty(searchDto.getOrderType())) {
            mv.setViewName("/post/list");
        } else {
            searchDto.setOrderType("postCreateDate");
            mv.setViewName("/board/" + boardType);
        }

        Pageable pageable = pageDto.makePageable(Sort.Direction.DESC, searchDto.getOrderType());
        Page<PostBoardDto> postBoardDtos = postService.getPostList(searchDto, pageable);
        Pagination pagination = new Pagination(postBoardDtos);

        if (!postBoardDtos.isEmpty()) {
            mv.addObject("pagination", pagination);
            mv.addObject("totalElements", pagination.getResult().getTotalElements());
        }

        return mv;
    }
}
