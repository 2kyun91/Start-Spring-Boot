package com.start.springboot.domain.board.controller;

import com.start.springboot.common.errors.errorcode.CommonErrorCode;
import com.start.springboot.common.errors.exception.CustomException;
import com.start.springboot.common.page.PageDto;
import com.start.springboot.common.page.Pagination;
import com.start.springboot.common.search.SearchDto;
import com.start.springboot.common.util.FileStorageUtil;
import com.start.springboot.domain.attach.dto.AttachDto;
import com.start.springboot.domain.attach.entity.Attach;
import com.start.springboot.domain.attach.service.AttachService;
import com.start.springboot.domain.board.dto.BoardDto;
import com.start.springboot.domain.board.service.BoardService;
import com.start.springboot.domain.post.dto.PostBoardDto;
import com.start.springboot.domain.post.dto.PostDto;
import com.start.springboot.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
    private final FileStorageUtil fileStorageUtil;

    @RequestMapping("/list/{boardId}")
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

    @GetMapping("/writeForm/{boardId}")
    public ModelAndView getWriteForm(
            @PathVariable("boardId") Long boardId,
            ModelAndView mv) {
        mv.setViewName("/board/main");
        String boardName = boardService.getBoardName(boardId);
        if (boardName.equals("전체보기")) {
            List<BoardDto> boardDtos = boardService.getAllBoard();
            mv.addObject("boarList", boardDtos);
        }

        mv.addObject("boardId", boardId);
        mv.addObject("boardName", boardName);
        return mv;
    }

    @PostMapping("/writeForm/{boardId}")
    @ResponseBody
    public Map<String, Object> saveWriteForm(
            @PathVariable("boardId") Long boardId,
            PostBoardDto postBoardDto) {
        Map<String, Object> returnMap = new HashMap<>();
        String boardName = boardService.getBoardName(boardId);

        if (!boardName.equals("전체보기")) {
            BoardDto boardDto = new BoardDto();
            boardDto.setBoardId(boardId);

            PostDto postDto = new PostDto();
            postDto.setPostTitle(postBoardDto.getPostTitle());
            postDto.setPostContent(postBoardDto.getPostContent());
            postDto.setPostWriter(postBoardDto.getPostWriter());
            postDto.setBoard(boardDto.toEntity());
            postDto = postService.createPost(postDto); // 주소값 변경

            saveAttachFiles(postBoardDto, postDto);
            returnMap.put("postId", postDto.getPostId());
        } else {
            throw new CustomException(CommonErrorCode.INVALID_PARAMETER);
        }

        return returnMap;
    }

    @GetMapping("/detail/{boardId}/{postId}")
    public ModelAndView getPost(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId,
            ModelAndView mv) {
        return getModelAndView(boardId, postId, mv);
    }

    @GetMapping("/download/{boardId}/{postId}/{attachId}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId,
            @PathVariable("attachId") Long attachId) {
        AttachDto attachDto = attachService.getAttach(attachId);

        Long testPostId = attachDto.getPost().getPostId();
        System.out.println("testPostId : " + testPostId);
        String testPostTitle = attachDto.getPost().getPostTitle();
        System.out.println("testPostTitle : " + testPostTitle);

        if (!attachDto.getPost().getPostId().equals(postId)) {
            throw new CustomException(CommonErrorCode.INVALID_PARAMETER);
        }
        String filename = attachDto.getAttachPhysicalName();
        Resource file = fileStorageUtil.downloadFile(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + attachDto.getAttachOriginalName() + "\"").body(file);
    }

    @GetMapping("/modifyForm/{boardId}/{postId}")
    public ModelAndView getModifyForm(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId,
            ModelAndView mv) {
        mv.addObject("isModify", true);
        mv = getModelAndView(boardId, postId, mv);

        List<BoardDto> boardDtos = boardService.getAllBoard();
        mv.addObject("boarList", boardDtos);
        return mv;
    }

    @PostMapping("/modifyForm/{boardId}/{postId}")
    @ResponseBody
    public Map<String, Object> saveModifyForm(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId,
            PostBoardDto postBoardDto) {
        Map<String, Object> returnMap = new HashMap<>();
        String boardName = boardService.getBoardName(boardId);

        if (!boardName.equals("전체보기")) {
            PostDto postDto = new PostDto();
            postDto.setPostId(postId);
            postDto.setPostTitle(postBoardDto.getPostTitle());
            postDto.setPostContent(postBoardDto.getPostContent());

            BoardDto boardDto = new BoardDto();
            boardDto.setBoardId(postBoardDto.getBoardId());
            postDto.setBoard(boardDto.toEntity());

            // 사용자와 게시글 작성자 검증이 되었다는 가정
            if (true) {
                List<Attach> originalAttachList = attachService.getAttachList(postId);
                if (ObjectUtils.isEmpty(postBoardDto.getOriginalAttachId())) {
                    originalAttachList.forEach(originalAttach -> {
                        String filename = originalAttach.getAttachPhysicalName();
                        fileStorageUtil.deleteFile(filename);
                    });
                } else {
                    postBoardDto.getOriginalAttachId().forEach(attachId -> {
                        originalAttachList.forEach(originalAttach -> {
                            if (!originalAttach.getAttachId().equals(attachId)) {
                                String filename = originalAttach.getAttachPhysicalName();
                                fileStorageUtil.deleteFile(filename);
                            }
                        });
                    });
                }

                attachService.deleteAttachList(postBoardDto);
                postService.updatePost(postDto);
                saveAttachFiles(postBoardDto, postDto);
            }

            returnMap.put("postId", postDto.getPostId());
        } else {
            throw new CustomException(CommonErrorCode.INVALID_PARAMETER);
        }
        return  returnMap;
    }

    private ModelAndView getModelAndView(Long boardId, Long postId, ModelAndView mv) {
        mv.setViewName("/board/main");
        PostBoardDto postBoardDto = new PostBoardDto();
        String boardName = boardService.getBoardName(boardId);

        if (!ObjectUtils.isEmpty(mv.getModel().get("isModify"))) {
            postBoardDto = postService.getPost(postId, true);
        } else {
            postBoardDto = postService.getPost(postId, false);
        }

        mv.addObject("boardId", boardId);
        mv.addObject("boardName", boardName);
        mv.addObject("postId", postId);
        mv.addObject("postDetail", postBoardDto);
        return mv;
    }

    private void saveAttachFiles(PostBoardDto postBoardDto, PostDto postDto) {
        List<MultipartFile> files = postBoardDto.getAttachFiles();
        fileStorageUtil.uploadFiles(files);

        List<AttachDto> attachDtos = fileStorageUtil.getAttachDtos();
        for (AttachDto attachDto : attachDtos) {
            attachDto.setPost(postDto.toEntity());
            attachService.createAttach(attachDto);
        }
    }
}
