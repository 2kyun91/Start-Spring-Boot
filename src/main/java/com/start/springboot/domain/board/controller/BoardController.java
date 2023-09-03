package com.start.springboot.domain.board.controller;

import com.start.springboot.common.page.PageDto;
import com.start.springboot.common.page.Pagination;
import com.start.springboot.common.search.SearchDto;
import com.start.springboot.common.util.PostUpload;
import com.start.springboot.domain.attach.dto.AttachDto;
import com.start.springboot.domain.attach.service.AttachService;
import com.start.springboot.domain.board.dto.BoardDto;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final PostService postService;
    private final AttachService attachService;

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

        String boardName = boardService.getBoardName(boardId);

        Pageable pageable = pageDto.makePageable(Sort.Direction.DESC, searchDto.getOrderType());
        Page<PostBoardDto> postBoardDtos = postService.getPostList(searchDto, pageable);
        Pagination pagination = new Pagination(postBoardDtos);

        mv.addObject("pagination", pagination);
        mv.addObject("boardId", boardId);
        mv.addObject("boardName", boardName);
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

    @PostMapping("/{boardId}/write")
    @ResponseBody
    public Map<String, Object> saveWriteForm(
            @PathVariable("boardId") Long boardId,
            PostBoardDto postBoardDto, PostUpload postUpload) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            String boardName = boardService.getBoardName(boardId);
            if (!boardName.equals("전체보기")) {
                BoardDto boardDto1 = new BoardDto();
                boardDto1.setBoardId(boardId);

                PostDto postDto = new PostDto();
                postDto.setPostTitle(postBoardDto.getPostTitle());
                postDto.setPostContent(postBoardDto.getPostContent());
                postDto.setPostWriter(postBoardDto.getPostWriter());
                postDto.setBoard(boardDto1.toEntity());
                postDto = postService.createPost(postDto);

                List<MultipartFile> files = postBoardDto.getFiles();
                if (!files.isEmpty()) {
                    postUpload.saveFiles(files);
                    List<AttachDto> attachDtos = postUpload.getAttachDtos();
                    for (AttachDto attachDto : attachDtos) {
                        attachDto.setPost(postDto.toEntity());
                        attachService.createAttach(attachDto);
                    }
                }
                returnMap.put("postId", postDto.getPostId());
            } else {
                returnMap.put("msg", "잘못된 접근입니다.");
            }
        } catch (Exception e) {
            // exception 핸들러 구현하기(@ControllerAdvice, @ExceptionHandler)
            e.printStackTrace();
            returnMap.put("msg", "게시글 작성중 오류가 발생했습니다.");
        }

        return returnMap;
    }

    @GetMapping("/{boardId}/view/{postId}")
    public ModelAndView getPost(
            @PathVariable("boardId") String boardId,
            @PathVariable("postId") Long postId,
            ModelAndView mv) {
        return mv;
    }
}
