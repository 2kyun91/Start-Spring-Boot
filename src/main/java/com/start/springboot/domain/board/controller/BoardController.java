package com.start.springboot.domain.board.controller;

import com.start.springboot.common.page.PageDto;
import com.start.springboot.common.page.Pagination;
import com.start.springboot.common.search.SearchDto;
import com.start.springboot.domain.board.entity.Board;
import com.start.springboot.domain.board.service.BoardService;
import com.start.springboot.domain.post.dto.PostBoardDto;
import com.start.springboot.domain.post.dto.PostDto;
import com.start.springboot.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final PostService postService;
    private final BoardService boardService;

    @RequestMapping("/{boardId}/list")
    @ResponseBody
    public ModelAndView getPostList(
            @PathVariable("boardId") Long boardId,
            @ModelAttribute("search") SearchDto searchDto, PageDto pageDto,
            ModelAndView mv) {
        if (!StringUtils.isEmpty(searchDto.getOrderType())) {
            mv.setViewName("/post/list");
        } else {
            searchDto.setOrderType("postCreateDate");
            mv.setViewName("/board/main");
        }

        Pageable pageable = pageDto.makePageable(Sort.Direction.DESC, searchDto.getOrderType());
        Page<PostBoardDto> postBoardDtos = postService.getPostList(searchDto, pageable);
        Pagination pagination = new Pagination(postBoardDtos);

        mv.addObject("pagination", pagination);
        mv.addObject("boardId", boardId);
        mv.addObject("totalElements", pagination.getResult().getTotalElements());
        return mv;
    }

    @GetMapping("/{boardId}/write")
    public ModelAndView getWriteForm(
            @PathVariable("boardId") Long boardId,
            ModelAndView mv) {
        mv.setViewName("/board/main");
        String boardName = boardService.getBoardName(boardId);
        if (boardName.equals("전체보기")) {
            List<PostBoardDto> postBoardDtos = boardService.getAllBoard();
            mv.addObject("boarList", postBoardDtos);
        }

        mv.addObject("boardId", boardId);
        mv.addObject("boardName", boardName);
        return mv;
    }

    @PostMapping("/{boardType}/write")
    public String saveWriteForm(
            @PathVariable("boardType") String boardType,
            @ModelAttribute("postDto")PostDto postDto) {
        Long postId = postDto.getPostId();
        return "redirect:/board/" + boardType + "/view/" + postId;
    }
}
